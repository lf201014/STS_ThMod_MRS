package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConsumeChargeUpAction extends AbstractGameAction {

  private int amt;

  public ConsumeChargeUpAction(int amount) {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
    this.amt = amount;
  }

  public void update() {
    this.isDone = false;
    AbstractPlayer p = AbstractDungeon.player;

    if (!p.hasPower("ChargeUpPower")) {
      this.isDone = true;
      return;
    }

    AbstractPower c = p.getPower("ChargeUpPower");
    c.stackPower(-this.amt);

    if (p.hasPower("OrrerysSunPower")) {
      p.getPower("OrrerysSunPower").onSpecificTrigger();
    }
    this.isDone = true;
  }
}