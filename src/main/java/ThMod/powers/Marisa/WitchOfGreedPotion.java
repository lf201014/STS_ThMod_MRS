package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WitchOfGreedPotion
    extends AbstractPower {

  public static final String POWER_ID = "WitchOfGreedPotion";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public WitchOfGreedPotion(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/potion.png");
  }
/*
  public void onVictory() {
    for (int i = 0; i < this.amount; i++) {
      if (AbstractDungeon.player.hasRelic("Sozu")) {
        AbstractDungeon.player.getRelic("Sozu").flash();
      } else {
        AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion(true));
      }
    }
  }
*/
  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}