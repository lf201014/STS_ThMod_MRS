package ThMod.abstracts;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;

public abstract class AmplifiedAttack
    extends CustomCard {

  private int ampDamage = -1;
  protected int ampNumber = 0;
  protected int[] multiAmpDamage;
  protected boolean isException = false;

  public AmplifiedAttack(
      String id,
      String name,
      String img,
      int cost,
      String rawDescription,
      AbstractCard.CardType type,
      AbstractCard.CardColor color,
      AbstractCard.CardRarity rarity,
      AbstractCard.CardTarget target
  ) {
    super(
        id,
        name,
        img,
        cost,
        rawDescription,
        type,
        color,
        rarity,
        target
    );
  }

  public void applyPowers() {
    if (!this.isException) {
      this.damage = this.baseDamage;
      this.ampDamage = (this.baseDamage + this.ampNumber);
      this.block = (this.baseBlock = this.ampDamage);
    }
    this.isDamageModified = false;
    this.isBlockModified = false;
    float tmp = this.damage;
    float amp = this.block;
    tmp = calculate(tmp, null);
    amp = calculate(amp, null);
    if (this.baseDamage != (int) tmp) {
      this.isDamageModified = true;
    }
    if (this.ampDamage != (int) amp) {
      this.isBlockModified = true;
    }
    this.damage = MathUtils.floor(tmp);
    this.block = MathUtils.floor(amp);
    if (this.isMultiDamage) {
      ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
      this.multiDamage = new int[m.size()];
      for (int i = 0; i < m.size(); i++) {
        this.multiDamage[i] = MathUtils.floor(tmp);
      }
      this.multiAmpDamage = new int[m.size()];
      for (int i = 0; i < m.size(); i++) {
        this.multiAmpDamage[i] = MathUtils.floor(amp);
      }
    }
  }

  private float calculate(float base, AbstractMonster target) {
    float temp = base;
    AbstractPlayer player = AbstractDungeon.player;
    if ((AbstractDungeon.player.hasRelic("WristBlade")) && (this.costForTurn == 0)) {
      temp += 3.0f;
    }
    for (AbstractPower p : player.powers) {
      temp = p.atDamageGive(temp, this.damageTypeForTurn);
    }
    if (target != null) {
      for (AbstractPower p : target.powers) {
        temp = p.atDamageReceive(temp, this.damageTypeForTurn);
      }
    }
    for (AbstractPower p : player.powers) {
      temp = p.atDamageFinalGive(temp, this.damageTypeForTurn);
    }
    if (target != null) {
      for (AbstractPower p : target.powers) {
        temp = p.atDamageFinalReceive(temp, this.damageTypeForTurn);
      }
    }
    if (temp < 0) {
      temp = 0;
    }
    return temp;
  }

  public void calculateDamageDisplay(AbstractMonster mo) {
    calculateCardDamage(mo);
  }

  public void calculateCardDamage(AbstractMonster mo) {
    if (!this.isException) {
      this.damage = this.baseDamage;
      this.ampDamage = (this.baseDamage + this.ampNumber);
      this.block = (this.baseBlock = this.ampDamage);
    }
    this.isDamageModified = false;
    this.isBlockModified = false;
    if ((!this.isMultiDamage) && (mo != null)) {
      float tmp = this.damage;
      float amp = this.block;
      tmp = calculate(tmp, mo);
      amp = calculate(amp, mo);
      if (this.baseDamage != (int) tmp) {
        this.isDamageModified = true;
      }
      if (this.ampDamage != (int) amp) {
        this.isBlockModified = true;
      }
      this.damage = MathUtils.floor(tmp);
      this.block = MathUtils.floor(amp);
    } else {
      ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
      float[] tmp = new float[m.size()];
      float[] amp = new float[m.size()];
      for (int i = 0; i < m.size(); i++) {
        tmp[i] = calculate(damage, m.get(i));
        amp[i] = calculate(block, m.get(i));
        if (this.baseDamage != (int) tmp[i]) {
          this.isDamageModified = true;
        }
        if (this.ampDamage != (int) amp[i]) {
          this.isBlockModified = true;
        }
      }
      this.multiDamage = new int[m.size()];
      for (int i = 0; i < tmp.length; i++) {
        this.multiDamage[i] = MathUtils.floor(tmp[i]);
      }
      this.multiAmpDamage = new int[m.size()];
      for (int i = 0; i < amp.length; i++) {
        this.multiAmpDamage[i] = MathUtils.floor(amp[i]);
      }
      this.damage = this.multiDamage[0];
      this.block = this.multiAmpDamage[0];
    }
  }
}
