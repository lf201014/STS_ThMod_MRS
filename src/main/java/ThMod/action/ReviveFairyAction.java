package ThMod.action;

//public class ReviveFairyAction {

import ThMod.monsters.ZombieFairy;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.TintEffect;

public class ReviveFairyAction extends AbstractGameAction {

  public ReviveFairyAction(AbstractMonster target, AbstractCreature source) {
    this.setValues(target, source, 0);
    this.actionType = ActionType.SPECIAL;
    if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
      target.addPower(new StrengthPower(target, 1));
    }
  }

  public void update() {
    if (this.duration == 0.5F && this.target instanceof AbstractMonster) {
      this.target.isDying = false;
      this.target.heal(this.target.maxHealth, false);
      this.target.healthBarRevivedEvent();
      ((AbstractMonster) this.target).deathTimer = 0.0F;
      ((AbstractMonster) this.target).tint = new TintEffect();
      ((AbstractMonster) this.target).tintFadeOutCalled = false;
      ((AbstractMonster) this.target).isDead = false;
      this.target.powers.clear();
      if (this.target instanceof ZombieFairy) {
        ((ZombieFairy) this.target).revive();
      }

      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new MinionPower(target))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new LimboContactPower(target))
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(target, target, new StrengthPower(target, 1))
      );
    }
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

    ((AbstractMonster) this.target).intent = Intent.NONE;
    ((AbstractMonster) this.target).rollMove();

    this.tickDuration();
  }
}