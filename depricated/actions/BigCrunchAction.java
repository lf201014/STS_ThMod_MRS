package ThMod.action.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod.powers.Marisa.ChargeUpPower;

@Deprecated
public class BigCrunchAction extends AbstractGameAction {

  public BigCrunchAction(AbstractCreature source, boolean upgraded) {
    setValues(AbstractDungeon.player, source, -1);
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
  }

  public void update() {
    if (this.duration == 0.5F) {
      AbstractDungeon.handCardSelectScreen.open("Choose", 99, true, true);

      AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
      tickDuration();
      return;
    }
    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
        int cnt = AbstractDungeon.handCardSelectScreen.selectedCards.group.size();
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(cnt));
        //if (this.upgraded) cnt *= 2;
        AbstractDungeon.actionManager.addToTop(
            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new ChargeUpPower(AbstractDungeon.player, cnt), cnt));
        for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
          AbstractDungeon.player.hand.moveToDiscardPile(c);
          GameActionManager.incrementDiscard(false);
          c.triggerOnManualDiscard();
        }
      }
      AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
    }
    tickDuration();
  }
}
