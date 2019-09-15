package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShootingEchoAction extends AbstractGameAction {

  private static final UIStrings uiStrings = CardCrawlGame.languagePack
      .getUIString("ExhaustAction");
  public static final String[] TEXT = uiStrings.TEXT;
  private int dmg;
  AbstractMonster target;
  AbstractPlayer player;
  private DamageType damageTypeForTurn;

  public ShootingEchoAction(int damage, AbstractMonster m) {
    this.dmg = damage;
    this.target = m;
    this.player = AbstractDungeon.player;
    this.damageTypeForTurn = DamageType.NORMAL;
    this.duration = Settings.ACTION_DUR_FAST;
    this.actionType = ActionType.EXHAUST;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {

      if (this.player.hand.isEmpty()) {
        this.isDone = true;
        return;
      }

      if (this.player.hand.size() == 1) {
        ThMod.logger.info("ShootingEchoAction : player hand size is 1");
        AbstractCard c = player.hand.getTopCard();
        if (c instanceof Burn) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  target,
                  new DamageInfo(this.player, this.dmg, this.damageTypeForTurn),
                  AttackEffect.FIRE
              )
          );
        }
        this.player.hand.moveToExhaustPile(c);
        this.isDone = true;
        return;
      } else {
        ThMod.logger.info("ShootingEchoAction : opening hand card select");
        AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
      }

      tickDuration();
      return;
    }

    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group){

        if (c instanceof Burn) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  target,
                  new DamageInfo(this.player, this.dmg, this.damageTypeForTurn),
                  AttackEffect.FIRE
              )
          );
        }
        this.player.hand.moveToExhaustPile(c);
      }

      AbstractDungeon.gridSelectScreen.selectedCards.clear();
      AbstractDungeon.player.hand.refreshHandLayout();
      this.isDone = true;
      return;
    }
    tickDuration();
  }
}
