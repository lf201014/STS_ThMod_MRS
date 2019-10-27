package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RefreshHandAction extends AbstractGameAction {

  public RefreshHandAction(){
  }

  @Override
  public void update() {
    AbstractDungeon.player.hand.refreshHandLayout();
    this.isDone = true;
  }
}
