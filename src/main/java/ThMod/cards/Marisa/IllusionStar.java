package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class IllusionStar extends CustomCard {

  public static final String ID = "IllusionStar";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/IllusionStar_V2.png";
  private static final int COST = 0;
  private static final int CARD_PRINT = 2;
  //private static final int UPG_CARD_PRINT = 1;

  public IllusionStar() {
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
    this.magicNumber = this.baseMagicNumber = CARD_PRINT;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    for (int i = 0; i < this.magicNumber; i++) {

      AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();

      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(c, 1)
      );
    }
    AbstractDungeon.actionManager.addToBottom(
        new PutOnDeckAction(p, p, 1, false)
    );
  }

  public AbstractCard makeCopy() {
    return new IllusionStar();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      //upgradeMagicNumber(UPG_CARD_PRINT);
      this.exhaust = false;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}