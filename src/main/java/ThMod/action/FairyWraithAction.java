package ThMod.action;

import ThMod.monsters.ZombieFairy;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

public class FairyWraithAction extends AbstractGameAction {

  public FairyWraithAction() {
    this.duration = Settings.ACTION_DUR_XFAST;
  }

  @Override
  public void update() {
    if (AbstractDungeon.getCurrRoom().phase != RoomPhase.COMBAT) {
      this.isDone = true;
      return;
    }
    this.isDone = false;

    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
      if ((m instanceof ZombieFairy)) {
        AbstractDungeon.actionManager.addToTop(
            new ApplyPowerAction(m, m, new LimboContactPower(m))
        );
        AbstractDungeon.actionManager.addToTop(
            new ApplyPowerAction(m, m, new FlightPower(m, 99))
        );
      }
    }

    this.isDone = true;
  }
}
