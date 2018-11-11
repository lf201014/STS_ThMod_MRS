package ThMod.action;

import ThMod.ThMod;
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
    ThMod.logger.info(
        "ConsumeChargeUpAction :"
        + " Consume amount : "
        + this.amt
        + " ; Charge-Up stacks : "
        + c.amount
    );
    if ((this.amt<=0)||(c.amount<=0)){
      this.isDone = true;
      return;
    }
    c.stackPower(-this.amt);
    if (p.hasPower("OrrerysSunPower")) {
      p.getPower("OrrerysSunPower").onSpecificTrigger();
    }
    this.isDone = true;
  }
}