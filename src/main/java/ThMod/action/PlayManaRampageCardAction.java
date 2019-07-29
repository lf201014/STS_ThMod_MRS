package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayManaRampageCardAction  extends AbstractGameAction {

  private boolean upgraded;

  PlayManaRampageCardAction(boolean upgraded){
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
  }

  public void update() {

    target = AbstractDungeon.getMonsters().getRandomMonster(true);
    AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();

    if (upgraded){
      card.upgrade();
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
    card.lighten(false);
    ThMod.logger.info(
        "PlayManaRampageCardAction : card : " +
            card.cardID +
            " ; target : " +
            target.id
    );
    card.applyPowers();
    AbstractDungeon.actionManager.currentAction = null;
    AbstractDungeon.actionManager.addToTop(this);
    AbstractDungeon.actionManager.cardQueue.add(
        new CardQueueItem(card, (AbstractMonster) target)
    );
    if (!Settings.FAST_MODE) {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
    } else {
      AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
    }
    this.isDone = true;
  }
}
