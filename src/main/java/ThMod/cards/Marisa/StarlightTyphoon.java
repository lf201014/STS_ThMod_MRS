package ThMod.cards.Marisa;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.cards.derivations.Spark;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;

public class StarlightTyphoon extends AmplifiedAttack {

  public static final String ID = "StarlightTyphoon";
  public static final String IMG_PATH = "img/cards/typhoon.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  private static final int COST = 1;
  private static final int MULT = 2;
  private static final int UPG_MULT = 1;
  public int counter = 0;

  public StarlightTyphoon() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.RARE,
        CardTarget.NONE
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = 0;
    ThMod.logger.info("StarlightTyphoon : onUse");
    for (AbstractCard c : p.hand.group) {
      if ((c.type != CardType.ATTACK) && (c != this)) {
        ThMod.logger.info("StarlightTyphoon : exahsting : " + c.name);
        AbstractDungeon.actionManager.addToTop(
            new ExhaustSpecificCardAction(c, p.hand, true)
        );
        cnt++;
        ThMod.logger.info("StarlightTyphoon : counter : " + cnt);
      }
    }

    ThMod.logger.info("StarlightTyphoon : adding Spark : counter : " + cnt);
    AbstractCard c = new Spark();
    if (this.upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(c, cnt)
    );
  }

  public AbstractCard makeCopy() {
    return new StarlightTyphoon();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MULT);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}