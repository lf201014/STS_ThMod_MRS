package ThMod.action;

//public class BlazeAwayAction {

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlazeAwayAction
    extends AbstractGameAction {

  private AbstractCard card;
  AbstractPlayer p;

  public BlazeAwayAction(int amount, AbstractCard card) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.amount = amount;
    this.p = AbstractDungeon.player;
    if (card.type == CardType.ATTACK) {
      this.card = card;
    } else {
      this.isDone = true;
    }
    ThMod.logger.info(
        "BlazeAwayAction : Initialize complete ; number :" +
            amount +
            " ; card : " +
            card.name
    );
  }

  public void update() {

    AbstractMonster target;

    target = AbstractDungeon.getMonsters().getRandomMonster(true);
/*
    if (target == null) {
      this.isDone = true;
      ThMod.logger.info("ManaRampageAction : done");
      return;
    }
    */
    AbstractDungeon.player.limbo.group.add(card);
    card.current_x = (Settings.WIDTH / 2.0F);
    card.current_y = (Settings.HEIGHT / 2.0F);
    card.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
    card.target_y = (Settings.HEIGHT / 2.0F);
    card.freeToPlayOnce = true;
    card.purgeOnUse = true;
    card.targetAngle = 0.0F;
    card.drawScale = 0.12F;
    ThMod.logger.info(
        "BlazeAwayAction : card : " +
            card.cardID +
            " ; target : " +
            target.id
    );
    /*
    if (!card.canUse(AbstractDungeon.player, this.target)) {
      AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));
    } else */

    card.applyPowers();
    AbstractDungeon.actionManager.currentAction = null;
    AbstractDungeon.actionManager.addToTop(this);
    AbstractDungeon.actionManager.cardQueue.add(
        new CardQueueItem(card, target)
    );

    if (!Settings.FAST_MODE) {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
    } else {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
    }

    if (this.amount>1){
      AbstractDungeon.actionManager.addToBottom(
          new BlazeAwayAction(amount--,this.card)
      );
    }
  }

}