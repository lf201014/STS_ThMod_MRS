package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

@Deprecated
public class GiftOfLifePower
    extends AbstractPower {

  public static final String POWER_ID = "GiftOfLife";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public GiftOfLifePower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/malleable.png");
    this.amount = amount;
  }

  public void atEndOfTurn(boolean isPlayer) {
    flash();
    AbstractDungeon.actionManager.addToBottom(
        new HealAction(
            owner,
            owner,
            this.amount
        )
    );
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}