package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawDrawPileAction extends AbstractGameAction
{
  public DrawDrawPileAction(){
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  @Override
  public void update() {
    this.isDone = true;
    if (AbstractDungeon.player.drawPile.isEmpty()){
      return;
    }
    addToTop(new DrawCardAction(1));
  }
}
