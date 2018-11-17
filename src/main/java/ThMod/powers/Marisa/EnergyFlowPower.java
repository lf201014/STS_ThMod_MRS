package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class EnergyFlowPower
    extends AbstractPower {

  public static final String POWER_ID = "EnergyFlowPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public EnergyFlowPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.type = AbstractPower.PowerType.BUFF;
    this.amount = amount;
    updateDescription();
    this.img = new Texture("img/powers/electricField.png");
  }

  public void atEndOfTurn(boolean isPlayer) {
    flash();
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            owner,
            owner,
            new ChargeUpPower(owner, this.amount),
            this.amount
        )
    );
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}