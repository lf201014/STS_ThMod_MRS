package ThMod.action.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@Deprecated
public class GrandCrossAction
    extends AbstractGameAction {

  private AbstractPlayer p;

  public GrandCrossAction() {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.p = AbstractDungeon.player;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      for (AbstractCard c : this.p.hand.group) {
        if (c.cardID.equals("GrandCross")) {
          c.setCostForTurn(0);
        }
      }
      this.isDone = true;
      return;
    }

    tickDuration();
  }
}
