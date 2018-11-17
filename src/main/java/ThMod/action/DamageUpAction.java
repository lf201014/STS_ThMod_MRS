package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod.ThMod;

public class DamageUpAction
    extends AbstractGameAction {

  public DamageUpAction(int amount) {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
    this.amount = amount;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if (c.type == CardType.ATTACK) {
          ThMod.logger.info(("Milky Way Action : add " + this.amount + " damage to " + c.cardID));
          c.baseDamage += this.amount;
          c.applyPowers();
        }
      }
    }
    tickDuration();
    return;
  }
}