package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class MillisecondPulsarsPower extends AbstractPower {

  public static final String POWER_ID = "MilliPulsaPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public MillisecondPulsarsPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/steadyPulse.png");
  }

  @Override
  public void stackPower(int stackAmount) {

  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0]);
  }
}