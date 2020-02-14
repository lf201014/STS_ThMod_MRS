package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;

import ThMod.ThMod;

@Deprecated
public class AsteroidBeltPower
    extends AbstractPower {

  public static final String POWER_ID = "AsteroidBeltPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public boolean isAttacked;
  private AbstractPower pow;

  public AsteroidBeltPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.type = AbstractPower.PowerType.DEBUFF;
    updateDescription();
    this.img = new Texture("img/powers/dodgeRoll.png");
    this.isAttacked = false;
    this.pow = new BarricadePower(this.owner);
    AbstractDungeon.actionManager.addToTop(
        new ApplyPowerAction(
            this.owner,
            this.owner,
            pow
        )
    );
  }

  public int onAttacked(DamageInfo info, int damageAmount) {
    if (info.type == DamageInfo.DamageType.NORMAL) {
      this.isAttacked = true;
      flash();
      ThMod.logger.info("AsteroidBeltPower : Attacked");
      AbstractDungeon.actionManager.addToTop(
          new RemoveSpecificPowerAction(owner, owner, pow)
      );
      AbstractDungeon.actionManager.addToTop(
          new RemoveSpecificPowerAction(owner, owner, this)
      );
    }

    return damageAmount;
  }

  @Override
  public void stackPower(int stackAmount) {
  }

  public void atStartOfTurnPostDraw() {
    flash();
    AbstractDungeon.actionManager.addToTop(
        new RemoveSpecificPowerAction(owner, owner, pow)
    );
    AbstractDungeon.actionManager.addToBottom(
        new RemoveSpecificPowerAction(owner, owner, this)
    );
  }

  /*
  public void atEndOfRound() {
    flash();
    this.amount = AbstractDungeon.player.currentBlock;
    updateDescription();
  }
  */
  public void updateDescription() {
    this.description = (DESCRIPTIONS[0]);
  }
}