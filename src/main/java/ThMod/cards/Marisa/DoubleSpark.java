package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action.SparkCostAction;
import ThMod.cards.derivations.Spark;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class DoubleSpark
    extends CustomCard {

  public static final String ID = "DoubleSpark";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/DoubleSpark.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 5;
  private static final int UPG_DMG = 2;

  public DoubleSpark() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = ATK_DMG;

  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(
                p,
                this.damage,
                this.damageTypeForTurn
            ),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );

    AbstractCard c = new Spark();
    if (this.upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(c, 1)
    );
    AbstractDungeon.actionManager.addToBottom(
        new SparkCostAction()
    );
  }

  public AbstractCard makeCopy() {
    return new DoubleSpark();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}