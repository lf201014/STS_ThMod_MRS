package ThMod.action.deprecated;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class ManaRampAction
    extends AbstractGameAction {

  private boolean upgraded;
  private int number;
  private AbstractMonster target;
  private AbstractCard card;
  private boolean f2p;
  AbstractPlayer p;

  public ManaRampAction(int number, boolean upgraded, boolean freeToPlay) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
    this.number = number;
    this.p = AbstractDungeon.player;
    this.f2p = freeToPlay;
    card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
    if (upgraded) {
      card.upgrade();
    }
    ThMod.logger.info(
        "ManaRampAction : Initialization complete ; card number :" +
            number +
            " ; card : " +
            card.name +
            " ; upgraded : " +
            upgraded
    );
  }

  public void update() {
    number--;
    if (number < 0) {
      this.isDone = true;
      ThMod.logger.info("ManaRampAction : done : all out");
      return;
    }

    target = AbstractDungeon.getMonsters().getRandomMonster(true);

    if (target == null) {
      this.isDone = true;
      ThMod.logger.info("ManaRampAction : done : no target");
      return;
    }
    AbstractDungeon.player.limbo.group.add(card);
    card.current_x = (Settings.WIDTH / 2.0F);
    card.current_y = (Settings.HEIGHT / 2.0F);
    card.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
    card.target_y = (Settings.HEIGHT / 2.0F);
    card.freeToPlayOnce = true;
    card.purgeOnUse = true;
    card.targetAngle = 0.0F;
    card.drawScale = 0.12F;
    //card.dontTriggerOnUseCard = true;
    ThMod.logger.info(
        "ManaRampAction : card : " +
            card.cardID +
            " ; target : " +
            target.id
    );
    card.applyPowers();
    AbstractDungeon.actionManager.currentAction = null;
    AbstractDungeon.actionManager.addToTop(this);
    AbstractDungeon.actionManager.cardQueue.add(
        new CardQueueItem(card, this.target)
    );
    if (number > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new ManaRampAction(number, upgraded, f2p)
      );
    }
    if (!this.f2p) {
      p.energy.use(1);
    }
    /*
    if (!Settings.FAST_MODE) {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
    } else {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
    }
    */
    this.isDone = true;
  }
}
