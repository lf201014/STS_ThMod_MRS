package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomCard;

public class GalacticHalo extends CustomCard {

  public static final String ID = "GalacticHalo";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final int COST = 2;
  private static final int STC = 2;
  private static final int UPG_STC = 1;
  private static final int BLC = 12;
  private static final int UPG_BLC = 2;

  public GalacticHalo() {
    super(
        ID, NAME, IMG_PATH,
        COST, DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.magicNumber = this.baseMagicNumber = STC;
    this.block = this.baseBlock = BLC;

    ThMod.logger.info(
        "GalacticHalo : init :"
            + " magicNumber : " + this.magicNumber
            + " baseMagicNumber : " + this.baseMagicNumber
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    	/*
    	AbstractDungeon.actionManager.addToBottom(
    		new ApplyPowerAction(p , p , 
    				new GalacticHaloPower(p,this.magicNumber), this.magicNumber)
    		);
    		*/

    ThMod.logger.info(
        "GalacticHalo : use :"
            + " magicNumber : " + this.magicNumber
            + " baseMagicNumber : " + this.baseMagicNumber
    );

    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p, p,
            new ChargeUpPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new GalacticHalo();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_STC);
      upgradeBlock(UPG_BLC);
    }
  }
}