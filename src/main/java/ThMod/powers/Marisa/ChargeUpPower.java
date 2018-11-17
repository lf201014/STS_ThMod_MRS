package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;
import ThMod.action.ConsumeChargeUpAction;

public class ChargeUpPower
    extends AbstractPower {

  public static final String POWER_ID = "ChargeUpPower";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS =
      powerStrings.DESCRIPTIONS;
  private int cnt;
  private int stc;
  private int ACT_STACK = 8;
  private int IMPR_STACK = 6;

  public ChargeUpPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    if (ThMod.ExhaustionCheck()) {
      this.amount = 0;
    } else {
      this.amount = amount;
    }
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/generator.png");

    getDivider();
    this.cnt = (int) Math.floor(this.amount / this.stc);
  }

  @Override
  public void stackPower(int stackAmount) {
    if (ThMod.ExhaustionCheck()) {
      return;
    }
    this.fontScale = 8.0F;
    this.amount += stackAmount;
    if (this.amount <= 0) {
      this.amount = 0;
    }

    getDivider();

    ThMod.logger.info(
        "ChargeUpPower : Checking stack divider :"
            + this.stc
            + " ; Checking stack number :"
            + this.amount
    );

    this.cnt = (int) Math.floor(this.amount / this.stc);
  }

  public void updateDescription() {
    if (this.cnt > 0) {
      this.description =
          (
              DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]
                  + "," + DESCRIPTIONS[2] + (int) Math.pow(2, this.cnt) + DESCRIPTIONS[3]
          );
    } else {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ".");
    }
  }

  @Override
  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    if ((this.owner.hasPower("MoraleDepletionPlusPower")) || (ThMod.ExhaustionCheck())) {
      return;
    }
    if ((this.cnt > 0) && (card.type == CardType.ATTACK)) {
      ThMod.logger.info("ChargeUpPower : onPlayCard : consuming stacks for :" + card.cardID);

      flash();

      getDivider();

      ThMod.logger.info("ChargeUpPower : onPlayCard :"
          + " Checking stack number : "
          + this.stc
          + " ; Checking square counter : "
          + this.cnt
      );
      AbstractDungeon.actionManager.addToTop(
          new ConsumeChargeUpAction(cnt * this.stc)
      );
    }
  }

  @Override
  public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
    if ((this.owner.hasPower("MoraleDepletionPlusPower")) || (ThMod.ExhaustionCheck())) {
      return damage;
    }
    if (cnt > 0) {
      if ((type == DamageType.NORMAL) && (this.amount >= 1)) {
        return (float) (damage * Math.pow(2, this.cnt));
      }
    }
    return damage;
  }

  private void getDivider() {
    if (AbstractDungeon.player.hasRelic("SimpleLauncher")) {
      this.stc = IMPR_STACK;
    } else {
      this.stc = ACT_STACK;
    }
  }
}