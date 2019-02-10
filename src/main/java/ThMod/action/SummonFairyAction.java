package ThMod.action;


import static ThMod.ThMod.logger;

import ThMod.monsters.ZombieFairy;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SummonFairyAction
    extends AbstractGameAction {

  private static final float pos0X = 210.0F;
  private static final float pos0Y = 10.0F;
  private static final float pos1X = -220.0F;
  private static final float pos1Y = 50.0F;
  private static final float pos2X = 180.0F;
  private static final float pos2Y = 320.0F;
  private static final float pos3X = -250.0F;
  private static final float pos3Y = 310.0F;
  private static final float COORDINATE[][] = {
      {pos0X, pos0Y},
      {pos1X, pos1Y},
      {pos2X, pos2Y},
      {pos3X, pos3Y}
  };

  public SummonFairyAction(AbstractMonster monster) {
    this.source = monster;
    this.duration = Settings.ACTION_DUR_XFAST;
  }

  public void update() {
    int count = 0;
    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
      if ((m != this.source) && (m instanceof ZombieFairy)) {
        if (m.isDying) {
          logger.info("SummonFairyAction : reviving Fairy;");
          ((ZombieFairy) m).turnNum = 0;
          AbstractDungeon.actionManager.addToTop(
              new ReviveFairyAction(m, this.source)
          );
          AbstractDungeon.actionManager.addToTop(
              new ApplyPowerAction(m, m, new MinionPower(m))
          );
          AbstractDungeon.actionManager.addToTop(
              new ApplyPowerAction(m, m, new LimboContactPower(m))
          );
          AbstractDungeon.actionManager.addToTop(
              new ApplyPowerAction(m, m, new FlightPower(m, 99))
          );
          if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
            m.addPower(new StrengthPower(m, 1));
          }
          if (ModHelper.isModEnabled("Lethality")) {
            AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, m, new StrengthPower(m, 3), 3)
            );
          }
          if (ModHelper.isModEnabled("Time Dilation")) {
            AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, m, new SlowPower(m, 0))
            );
          }
          logger.info("SummonFairyAction : done reviving Fairy;");
          this.isDone = true;
          return;
        }
        logger.info("SummonFairyAction : Alive Fairy found,increasing counter;");
        count++;
        logger.info("SummonFairyAction : counter increased: " + count);
      }
    }
    if (count < 4) {
      logger.info("SummonFairyAction : spawning Fairy;");
      AbstractDungeon.actionManager.addToTop(
          new SpawnFairyAction(COORDINATE[count][0], COORDINATE[count][1])
      );
    }
    //AbstractDungeon.actionManager.addToTop(new FairyWraithAction());
    this.isDone = true;
  }
}
