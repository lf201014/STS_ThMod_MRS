package ThMod.action;

//public class ReviveFairyAction {

import static ThMod.ThMod.logger;

import ThMod.monsters.ZombieFairy;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.TintEffect;

public class ReviveFairyAction extends AbstractGameAction {

  public ReviveFairyAction(AbstractMonster target, AbstractCreature source) {
    this.setValues(target, source, 0);
    this.actionType = ActionType.SPECIAL;
    /*
    target.addPower(new LimboContactPower(target));
    target.addPower(new FlightPower(target, 99));
    */
    if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
      target.addPower(new StrengthPower(target, 1));
      AbstractDungeon.onModifyPower();
    }
  }

  public void update() {
    if (this.target instanceof ZombieFairy) {
      logger.info("ReviveFairyAction : setting values;");
      ZombieFairy fairy = (ZombieFairy) this.target;
      this.target.isDying = false;
      this.target.heal(this.target.maxHealth, false);
      this.target.healthBarRevivedEvent();
      fairy.deathTimer = 0.0F;
      fairy.tint = new TintEffect();
      fairy.tintFadeOutCalled = false;
      fairy.isDead = false;
      //this.target.powers.clear();
      fairy.revive();
      fairy.turnNum = 0;

      logger.info("ReviveFairyAction : applying powers;");

      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new MinionPower(target))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new LimboContactPower(target))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new FlightPower(fairy, 99))
      );
      //fairy.usePreBattleAction();
      if (ModHelper.isModEnabled("Lethality")) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(target, target, new StrengthPower(target, 3), 3)
        );
      }
      if (ModHelper.isModEnabled("Time Dilation")) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(target, target, new SlowPower(target, 0))
        );
      }

      fairy.intent = Intent.NONE;
      fairy.rollMove();

      logger.info("ReviveFairyAction : done applying power;");
    } else {
      logger.info("ReviveFairyAction : error : target is not ZombieFairy : " + target.name);
    }
    this.isDone = true;
  }
}