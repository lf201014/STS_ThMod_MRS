package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action.SparkCostAction;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.deprecated.DarkSparkPower;
import basemod.abstracts.CustomCard;

@Deprecated
public class DarkSpark
    extends CustomCard {

  public static final String ID = "DarkSpark";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 4;
  private static final int UPG_DMG = 2;
  private static final int DRAW = 2;
  private static final int UPG_DRAW = 1;

  public DarkSpark() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = ATK_DMG;
    this.baseMagicNumber = this.magicNumber = DRAW;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL)
    );

    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(p, this.magicNumber)
    );

    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new DarkSparkPower(p, 1),
            1
        )
    );

    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(
            new Burn(),
            1
        )
    );

    AbstractDungeon.actionManager.addToBottom(
        new SparkCostAction()
    );
  }

  public AbstractCard makeCopy() {
    return new DarkSpark();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      upgradeMagicNumber(UPG_DRAW);
    }
  }
}