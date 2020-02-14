package ThMod.action.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ThMod.cards.Marisa.UltraShortWave;

@Deprecated
public class UltraShortWaveAction
    extends AbstractGameAction {

  public UltraShortWaveAction() {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  public void update() {
    this.isDone = false;

    for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
      if ((c instanceof UltraShortWave)) {
        c.baseMagicNumber += 1;
        c.magicNumber += 1;
      }
    }
    for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
      if ((c instanceof UltraShortWave)) {
        c.baseMagicNumber += 1;
        c.magicNumber += 1;
      }
    }
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      if ((c instanceof UltraShortWave)) {
        c.baseMagicNumber += 1;
        c.magicNumber += 1;
      }
    }

    this.isDone = true;
  }
}