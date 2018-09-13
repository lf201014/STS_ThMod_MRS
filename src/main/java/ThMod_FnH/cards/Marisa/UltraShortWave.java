package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomCard;

public class UltraShortWave extends CustomCard {

  public static final String ID = "UltraShortWave";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int UPG_CHG = 1;
  private static final int GAIN = 1;

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
    this.block = this.baseBlock = GAIN;
  }

  @Override
  public void applyPowers() {

  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {

  }

  /*
  @Override
  public void applyPowers(){
    if (AbstractDungeon.player.hasPower("UltraShortWavePower")) {
      ThMod.logger.info(
          "UltraShortWave : applyPowers : UltraShortWavePower detected: baseMagicNumber"+this.baseMagicNumber+
          " ; baseBlock : "+this.baseBlock+
          " ; MagicNumber : "+this.magicNumber+
          " ; Block : "+this.block
          );
      int cnt = AbstractDungeon.player.getPower("UltraShortWavePower").amount;
      this.magicNumber = cnt + this.baseMagicNumber;
      this.block = cnt + this.baseBlock;
      this.isMagicNumberModified =true;
      this.isBlockModified = true;
      ThMod.logger.info(
          "UltraShortWave : applyPowers finished baseMagicNumber"+this.baseMagicNumber+
          " ; baseBlock : "+this.baseBlock+
          " ; MagicNumber : "+this.magicNumber+
          " ; Block : "+this.block
          );
    } else {
      this.magicNumber = this.baseMagicNumber;
      this.block = this.baseBlock;
    }
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo){
    if (AbstractDungeon.player.hasPower("UltraShortWavePower")) {
      ThMod.logger.info(
          "UltraShortWave : calculateCardDamage : UltraShortWavePower detected: baseMagicNumber"+this.baseMagicNumber+
          " ; baseBlock : "+this.baseBlock+
          " ; MagicNumber : "+this.magicNumber+
          " ; Block : "+this.block
          );
      int cnt = AbstractDungeon.player.getPower("UltraShortWavePower").amount;
      this.magicNumber = cnt + this.baseMagicNumber;
      this.block = cnt + this.baseBlock;
      this.isMagicNumberModified =true;
      this.isBlockModified = true;
      ThMod.logger.info(
          "UltraShortWave : calculateCardDamage finished baseMagicNumber"+this.baseMagicNumber+
          " ; baseBlock : "+this.baseBlock+
          " ; MagicNumber : "+this.magicNumber+
          " ; Block : "+this.block
          );
    } else {
      this.magicNumber = this.baseMagicNumber;
      this.block = this.baseBlock;
    }

  }
  */
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
    this.upgradeMagicNumber(1);
    this.upgradeBlock(1);
		
		/*
		AbstractDungeon.actionManager.addToBottom(
				new ApplyPowerAction(p,p,new UltraShortWavePower(p,1),1)
				);
				*/
    this.applyPowers();
  }

  public AbstractCard makeCopy() {
    return new UltraShortWave();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_CHG);
    }
  }
}