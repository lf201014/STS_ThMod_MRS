package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.ThMod;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class ManaRampageAction
    extends AbstractGameAction {

  private AbstractPlayer p;
  private boolean upgraded = false;
  private int amount;

  public ManaRampageAction(int amount,boolean upgraded) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
    this.p = AbstractDungeon.player;
    this.amount = amount;
  }

  public void update() {
    this.isDone = false;
    ThMod.logger.info("ManaRampageAction : Start");
    if (amount<=0){
      this.isDone = true;
      return;
    }

    for (int i = 0; i < amount; i++) {
      AbstractCard tmp = GetAttack();
      if (upgraded){
        tmp.upgrade();
      }
      AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(true);
      if (mon == null){
        this.isDone = true;
        return;
      }
      tmp.current_x = (-300.0F * Settings.scale);
      tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
      tmp.target_y = (Settings.HEIGHT / 2.0F);
      tmp.freeToPlayOnce = true;
      tmp.applyPowers();
      tmp.purgeOnUse = true;
      ThMod.logger.info(
          "ManaRampageAction : card : "+
              tmp.cardID +
              " ; target : "+
              mon.id+
              " ; Upgraded :"+
              upgraded
      );
      AbstractDungeon.actionManager.cardQueue.add(
          new CardQueueItem(tmp, mon)
      );
    }

    ThMod.logger.info("ManaRampageAction : done");

    this.isDone = true;
  }

  private AbstractCard GetAttack(){
    AbstractCard temp = AbstractDungeon.returnTrulyRandomCard();
    while (temp.type != CardType.ATTACK){
      temp = AbstractDungeon.returnTrulyRandomCard();
    }
    return temp;
  }
}