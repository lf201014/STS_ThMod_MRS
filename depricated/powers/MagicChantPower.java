package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

@Deprecated
public class MagicChantPower
    extends AbstractPower {

  public static final String POWER_ID = "MagicChantPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public boolean isAttacked;
  private AbstractCard card;
  private static int offset;

  public MagicChantPower(AbstractCreature owner, AbstractCard card) {
    this.name = NAME;
    this.ID = POWER_ID + offset;
    offset++;
    this.owner = owner;
    this.card = card;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/drawCard.png");
    this.isAttacked = false;
  }

  public void atStartOfTurn() {
    flash();
    if (card.costForTurn > 0) {
      card.costForTurn -= 1;
    }
    AbstractDungeon.actionManager.addToBottom(
        new RemoveSpecificPowerAction(owner, owner, this)
    );
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.card.name + DESCRIPTIONS[1]);
  }
}