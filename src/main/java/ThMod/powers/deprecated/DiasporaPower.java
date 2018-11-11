package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

@Deprecated
public class DiasporaPower extends AbstractPower {

  public static final String POWER_ID = "Diaspora";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public DiasporaPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.DEBUFF;
    updateDescription();
    this.img = new Texture("img/powers/guardianCloseUp.png");
  }

  public void atEndOfRound() {
    if (this.owner.isPlayer) {
      ThMod.logger.info("DiasporaPower : AtEndOfRound : Why this applied to player?!");
      return;
    }
    if (this.owner.currentHealth <= this.amount) {
      if (this.owner.hasPower("Intangible")) {
        AbstractDungeon.actionManager.addToBottom(
            new RemoveSpecificPowerAction(this.owner, this.owner, "Intangible")
        );

      }
      if (this.owner.hasPower("IntangiblePlayer")) {
        AbstractDungeon.actionManager.addToBottom(
            new RemoveSpecificPowerAction(this.owner, this.owner, "IntangiblePlayer")
        );

      }
      AbstractMonster m = (AbstractMonster) this.owner;
      m.damage(
          new DamageInfo(owner, Integer.MAX_VALUE, DamageType.HP_LOSS)
      );
      ;
    } else {
      if (this.amount == 0) {
        AbstractDungeon.actionManager.addToBottom(
            new RemoveSpecificPowerAction(this.owner, this.owner, this)
        );
      } else {
        AbstractDungeon.actionManager.addToBottom(
            new ReducePowerAction(this.owner, this.owner, this, 1)
        );
      }
    }
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}