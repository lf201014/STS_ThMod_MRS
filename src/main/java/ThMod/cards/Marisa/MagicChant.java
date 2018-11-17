package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action.MagicChantAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class MagicChant extends CustomCard {

  public static final String ID = "MagicChant";
  public static final String IMG_PATH = "img/cards/Chant.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int RTN = 2;
  //private static final int UPG_RTN = 1;

  public MagicChant() {
    super(
        ID, NAME, IMG_PATH,
        COST, DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.magicNumber = this.baseMagicNumber = RTN;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new MagicChantAction(p, this.magicNumber)
    );
  }

  public AbstractCard makeCopy() {
    return new MagicChant();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(0);
      //upgradeMagicNumber(UPG_RTN);
    }
  }
}