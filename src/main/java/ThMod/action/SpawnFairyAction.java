package ThMod.action;

//public class SpawnFairyAction

import ThMod.monsters.ZombieFairy;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SpawnFairyAction extends AbstractGameAction {

  private boolean used;
  private static final float DURATION = 0.1F;
  private AbstractMonster m;
  private int targetSlot;

  public SpawnFairyAction(float x, float y) {
    this(x, y, -99);
  }

  public SpawnFairyAction(float x, float y, int slot) {
    this.used = false;
    this.actionType = ActionType.SPECIAL;
    this.duration = DURATION;
    this.m = new ZombieFairy(x, y);
    this.targetSlot = slot;
    if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
      m.addPower(new StrengthPower(m, 1));
      AbstractDungeon.onModifyPower();
    }

  }

  public void update() {
    if (!this.used) {
      this.m.init();
      this.m.applyPowers();
      if (this.targetSlot < 0) {
        AbstractDungeon.getCurrRoom().monsters.addSpawnedMonster(this.m);
      } else {
        AbstractDungeon.getCurrRoom().monsters.addMonster(this.targetSlot, this.m);
      }

      this.m.showHealthBar();
      if (ModHelper.isModEnabled("Lethality")) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(this.m, this.m, new StrengthPower(this.m, 3), 3)
        );
      }

      if (ModHelper.isModEnabled("Time Dilation")) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(this.m, this.m, new SlowPower(this.m, 0))
        );
      }

      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(this.m, this.m, new MinionPower(this.m))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(this.m, this.m, new LimboContactPower(this.m))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(this.m, this.m, new FlightPower(this.m, 99))
      );

      this.used = true;
    }

    this.tickDuration();
  }
}
