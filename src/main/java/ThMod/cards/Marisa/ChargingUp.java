package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomCard;

public class ChargingUp extends CustomCard {

  public static final String ID = "ChargingUp";
  public static final String IMG_PATH = "img/cards/ChargingUp.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int STC = 5;
  //private static final int AMP = 1;
  private static final int UPG_STC = 2;

  public ChargingUp() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.baseMagicNumber = this.magicNumber = STC;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int stack = this.magicNumber;
		/*
			if (ThMod.Amplified(this.costForTurn+AMP, AMP)) {
				stack = stack * 2 - 1;
			}
			*/
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new ChargeUpPower(p, stack),
            stack
        )
    );
  }

  public AbstractCard makeCopy() {
    return new ChargingUp();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_STC);
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
    }
  }
}