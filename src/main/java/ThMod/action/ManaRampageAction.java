package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;

public class ManaRampageAction
    extends AbstractGameAction {

  private boolean upgraded;
  private int amount;
  AbstractPlayer p;

  public ManaRampageAction(int amount, boolean upgraded) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
    this.amount = amount;
    this.p = AbstractDungeon.player;
  }

  public void update() {
    this.isDone = false;
    ThMod.logger.info("ManaRampageAction : Start");
    if (amount <= 0) {
      this.isDone = true;
      return;
    }

    for (int i = 0; i < amount; i++) {
      AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
      if (upgraded) {
        tmp.upgrade();
      }
      AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(true);

      if (mon == null) {
        this.isDone = true;
        return;
      }
      AbstractDungeon.player.limbo.group.add(tmp);
      tmp.current_x = (Settings.WIDTH / 2.0F);
      tmp.current_y = (Settings.HEIGHT / 2.0F);
      tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
      tmp.target_y = (Settings.HEIGHT / 2.0F);
      tmp.freeToPlayOnce = true;
      tmp.applyPowers();
      tmp.purgeOnUse = true;
      ThMod.logger.info(
          "ManaRampageAction : card : " +
              tmp.cardID +
              " ; target : " +
              mon.id +
              " ; Upgraded :" +
              upgraded
      );
      AbstractDungeon.actionManager.cardQueue.add(
          new CardQueueItem(tmp, mon)
      );

    }

    ThMod.logger.info("ManaRampageAction : done");

    this.isDone = true;
  }
}