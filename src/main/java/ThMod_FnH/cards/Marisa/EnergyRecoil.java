package ThMod_FnH.cards.Marisa;

import ThMod_FnH.ThMod;
import ThMod_FnH.action.ConsumeChargeUpAction;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomCard;
import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.ChargeUpPower;

public class EnergyRecoil extends CustomCard {

  public static final String ID = "EnergyRecoil";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 7;
  private static final int UPG_PLUS_BLC = 3;
  private static final int DIVIDER = 8;
  private boolean hasLauncher = false;

  public EnergyRecoil() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.SELF
    );

    this.baseBlock = BLOCK_AMT;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    if (ThMod.ExhaustionCheck()){
      return;
    }
    AbstractPlayer p = AbstractDungeon.player;
    hasLauncher = p.hasRelic("SimpleLauncher");
    if (p.hasPower("ChargeUpPower")) {
      this.block = (int) p.getPower("ChargeUpPower")
          .atDamageGive((float) this.block, this.damageTypeForTurn);
      this.block = (int) p.getPower("ChargeUpPower")
          .atDamageFinalGive((float) this.block, this.damageTypeForTurn);
    }
  }

  @Override
  public void calculateDamageDisplay(AbstractMonster mo) {
    calculateCardDamage(mo);
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    this.applyPowers();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );

    if (ThMod.ExhaustionCheck()){
      return;
    }

    if (p.hasPower("ChargeUpPower")) {
      int div = 8;
      if (hasLauncher){
        div -= 2;
      }
      if (p.getPower("ChargeUpPower").amount >= div) {
        int cnt = p.getPower("ChargeUpPower").amount / div;
        AbstractDungeon.actionManager.addToBottom(
            new ConsumeChargeUpAction(cnt * div)
        );
      }
    }

  }

  public AbstractCard makeCopy() {
    return new EnergyRecoil();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPG_PLUS_BLC);
    }
  }
}