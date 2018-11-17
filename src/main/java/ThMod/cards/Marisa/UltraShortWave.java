package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomCard;

public class UltraShortWave extends CustomCard {

  public static final String ID = "UltimateShortwave";
  public static final String IMG_PATH = "img/cards/ShortWave.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int GAIN = 1;
  private static final int GROW = 1;
  private static final int GAIN_GROW = 1;
  private static final int ENERGY = 1;

  public UltraShortWave() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = GAIN;
    this.block = this.baseBlock = ENERGY;
    this.damage = this.baseDamage = GROW;
  }

  @Override
  public void applyPowers() {

  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {

  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainEnergyAction(this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new ChargeUpPower(p, this.magicNumber),
            this.magicNumber
        )
    );
    this.upgradeMagicNumber(this.damage);
    this.upgradeBlock(1);
  }

  public AbstractCard makeCopy() {
    return new UltraShortWave();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(GAIN_GROW);
    }
  }
}