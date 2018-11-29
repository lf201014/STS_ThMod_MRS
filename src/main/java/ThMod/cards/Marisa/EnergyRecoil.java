package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;

public class EnergyRecoil extends CustomCard {

  public static final String ID = "EnergyRecoil";
  public static final String IMG_PATH = "img/cards/temp/Recoil.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  private static final int COST = 1;

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
    this.baseBlock = this.block = 0;
  }

  @Override
  public void applyPowers() {
    AbstractPlayer p = AbstractDungeon.player;
    if (this.upgraded) {
      this.baseBlock = 3;
    } else {
      this.baseBlock = 0;
    }
    if (p.hasPower("ChargeUpPower")) {
      this.baseBlock += p.getPower("ChargeUpPower").amount;
      super.applyPowers();
    }

    if (this.block > 0) {
      String extendString = EXTENDED_DESCRIPTION[0] + this.block + EXTENDED_DESCRIPTION[1];
      if (!this.upgraded) {
        this.rawDescription = DESCRIPTION + extendString;
      } else {
        this.rawDescription = DESCRIPTION_UPG + extendString;
      }
      initializeDescription();
    }
  }

  public void onMoveToDiscard() {
    if (this.upgraded) {
      this.rawDescription = DESCRIPTION_UPG;
    } else {
      this.rawDescription = DESCRIPTION;
    }
    initializeDescription();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (this.block > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(p, p, this.block)
      );
    }
  }

  public AbstractCard makeCopy() {
    return new EnergyRecoil();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}