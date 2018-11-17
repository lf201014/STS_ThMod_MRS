package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.CasketOfStarPlusPower;
import ThMod.powers.Marisa.CasketOfStarPower;
import basemod.abstracts.CustomCard;

public class CasketOfStar extends CustomCard {

  public static final String ID = "CasketOfStar";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/CasketOfStar.png";
  private static final int COST = 2;

  public CasketOfStar() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.RARE,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              p,
              p,
              new CasketOfStarPlusPower(p, 1),
              1
          )
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              p,
              p,
              new CasketOfStarPower(p, 1),
              1
          )
      );
    }

  }

  public AbstractCard makeCopy() {
    return new CasketOfStar();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}