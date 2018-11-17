package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class TempStrengthLoss
    extends AbstractPower {

  public static final String POWER_ID = "TempStrengthLoss";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public TempStrengthLoss(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.DEBUFF;
    updateDescription();
    this.img = new Texture("img/powers/dance.png");
  }


  public float atDamageGive(float damage, DamageInfo.DamageType type) {
    if (type == DamageInfo.DamageType.NORMAL) {
      return damage - this.amount;
    }
    return damage;
  }

  public void atEndOfTurn(boolean isPlayer) {
    if (!isPlayer) {
      AbstractDungeon.actionManager
          .addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TempStrengthLoss"));
    }
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}