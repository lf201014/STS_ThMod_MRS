package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import com.megacrit.cardcrawl.localization.PowerStrings;

@Deprecated
public class TalismanPower
    extends AbstractPower {

  public static final String POWER_ID = "TalismanPower";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public TalismanPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/burst.png");
  }

  @Override
  public void atStartOfTurnPostDraw() {
    for (int i = 0; i < this.amount; i++) {
      AbstractCard c =
          AbstractDungeon.returnTrulyRandomCard();
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(c, 1)
      );
    }

  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

}