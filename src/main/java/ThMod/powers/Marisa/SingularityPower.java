package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

import com.megacrit.cardcrawl.localization.PowerStrings;
import java.util.ArrayList;

public class SingularityPower
    extends AbstractPower {

  public static final String POWER_ID = "SingularityPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public SingularityPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/darkness.png");
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

  public void onAfterUseCard(AbstractCard card, UseCardAction action) {
    if ((card.costForTurn == 0) || (card.costForTurn <= -2)) {
      ThMod.logger.info("SingularityPower : applying upgrade :");
      this.flash();
      ArrayList<AbstractCard> pool = new ArrayList<>();
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if (c.type == CardType.ATTACK) {
          pool.add(c);
        }
      }
      if (!pool.isEmpty()) {
        int rand = AbstractDungeon.miscRng.random(0, pool.size() - 1);
        AbstractCard c = pool.get(rand);
        ThMod.logger.info(
            "SingularityPower : adding "
                + this.amount
                + " base damage to "
                + c.cardID
                + " random res :"
                + rand
        );
        c.baseDamage += this.amount;
        c.applyPowers();
        c.flash();
      }
    }
  }
}