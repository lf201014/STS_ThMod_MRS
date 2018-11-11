package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.PulseMagicPower;
import basemod.abstracts.CustomCard;

public class PulseMagic
    extends CustomCard {

  public static final String ID = "PulseMagic";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/pulseMagic.png";
  private static final int COST = 0;
  private static final int ENE = 1;
  private static final int UPG_ENE = 1;
  private static final int AMP = 1;

  public PulseMagic() {
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

    this.magicNumber = this.baseMagicNumber = ENE;
  }

  /*
    @Override
    public void applyPowers() {
      if (this.upgraded) {
        this.retain = true;
      }
    }
  */
  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(p, p, new PulseMagicPower(p)
          )
      );
    }
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new EnergizedBluePower(p, this.magicNumber),
            this.magicNumber
        )
    );

  }

  public AbstractCard makeCopy() {
    return new PulseMagic();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_ENE);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}