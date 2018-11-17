package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

@Deprecated
public class MoraleDepletionPower extends AbstractPower {

  public static final String POWER_ID = "MoraleDepletionPower";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public MoraleDepletionPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/darkEmbrace.png");
  }

  @Override
  public void stackPower(int stackAmount) {

  }

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0]);
  }
}