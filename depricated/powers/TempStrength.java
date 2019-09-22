package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

@Deprecated
public class TempStrength
    extends AbstractPower {

  public static final String POWER_ID = "TempStrength";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public TempStrength(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/doubleDamage.png");
  }


  public float atDamageGive(float damage, DamageInfo.DamageType type) {
    if (type == DamageInfo.DamageType.NORMAL) {
      return damage + this.amount;
    }
    return damage;
  }

  @Override
  public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
    AbstractDungeon.actionManager
        .addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TempStrength"));
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}