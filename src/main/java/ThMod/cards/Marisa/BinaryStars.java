package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.action.BinaryStarsAction;
import ThMod.cards.derivations.BlackFlareStar;
import ThMod.cards.derivations.WhiteDwarf;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class BinaryStars extends CustomCard {

  public static final String ID = "BinaryStars";
  public static final String IMG_PATH = "img/cards/temp/Twin.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int AMP = 1;

  public BinaryStars() {
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
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      AbstractCard c = new BlackFlareStar();
      if (this.upgraded) {
        c.upgrade();
      }
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(c, 1)
      );
      c = new WhiteDwarf();
      if (this.upgraded) {
        c.upgrade();
      }
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(c, 1)
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new BinaryStarsAction(this.upgraded)
      );
    }

  }

  public AbstractCard makeCopy() {
    return new BinaryStars();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}