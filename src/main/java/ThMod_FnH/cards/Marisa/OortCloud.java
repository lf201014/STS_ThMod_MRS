package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class OortCloud extends CustomCard {

  public static final String ID = "OortCloud";
  public static final String IMG_PATH = "img/cards/feelNoPain.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int ARMOR_GAIN = 4;
  private static final int UPG_ARMOR = 2;
  private static final int AMP_ARMOR = 1;
  private static final int UPG_AMP = 1;
  private static final int AMP = 1;


  public OortCloud() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.magicNumber = this.baseMagicNumber = ARMOR_GAIN;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    if (ThMod.Amplified(this, AMP)) {
      if (this.upgraded) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                p,
                p,
                new PlatedArmorPower(p, UPG_AMP),
                UPG_AMP
            )
        );
      } else {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                p,
                p,
                new PlatedArmorPower(p, AMP_ARMOR),
                AMP_ARMOR
            )
        );
      }
    }

    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new PlatedArmorPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new OortCloud();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_ARMOR);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}