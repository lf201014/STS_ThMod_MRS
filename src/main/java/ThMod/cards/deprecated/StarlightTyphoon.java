package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.ThMod;
import ThMod.cards.derivations.Spark;
import ThMod.patches.AbstractCardEnum;

@Deprecated
public class StarlightTyphoon extends CustomCard {

  public static final String ID = "StarlightTyphoon";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;

  public StarlightTyphoon() {
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
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = 0;
    ThMod.logger.info("StarlightTyphoon : onUse");
    for (AbstractCard c : p.hand.group) {
      if ((c.type != CardType.ATTACK) && (c != this)) {
        ThMod.logger.info("StarlightTyphoon : exahsting : " + c.cardID);
        AbstractDungeon.actionManager.addToTop(
            new ExhaustSpecificCardAction(c, p.hand, true)
        );
        cnt++;
        ThMod.logger.info("StarlightTyphoon : counter : " + cnt);
      }
    }
    for (int i = 0; i < cnt; i++) {
      AbstractCard tmp = new Spark();
      if (this.upgraded) {
        tmp.upgrade();
      }
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(tmp, 1)
      );
      ThMod.logger.info("StarlightTyphoon : adding Spark : counter : " + cnt);
    }
  }

  public AbstractCard makeCopy() {
    return new StarlightTyphoon();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}