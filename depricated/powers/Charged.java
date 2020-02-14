package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class Charged
    extends AbstractPower {

  public static final String POWER_ID = "Charged";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public Charged(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/burst.png");
  }

  @Override
  public void stackPower(int stackAmount) {
    this.fontScale = 8.0F;
    this.amount += stackAmount;
    if (this.amount <= 0) {
      this.amount = 0;
      return;
    }
  }

  @Override
  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    if (card.type == CardType.ATTACK) {
      AbstractDungeon.actionManager
          .addToBottom(new RemoveSpecificPowerAction(owner, owner, "Charged"));
    }
  }

  @Override
  public float atDamageGive(float damage, DamageInfo.DamageType type) {
    if ((type == DamageType.NORMAL) && (this.amount >= 1)) {
      return (float) (damage * Math.pow(2, this.amount));
    }
    return damage;
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + (int) Math.pow(2, this.amount) + DESCRIPTIONS[1]);
  }
}