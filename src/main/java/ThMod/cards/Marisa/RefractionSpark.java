package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.action.RefractionSparkAction;
import ThMod.action.SparkCostAction;
import ThMod.patches.AbstractCardEnum;

public class RefractionSpark
    extends AmplifiedAttack {

  public static final String ID = "RefractionSpark";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Refraction.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 4;
  private static final int UPG_DMG = 1;
  private static final int AMP_DMG = 2;
  private static final int UPG_AMP = 1;
  private static final int AMP = 1;


  public RefractionSpark() {
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
    this.ampNumber = AMP_DMG;
    this.baseBlock = this.baseDamage + this.ampNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new SparkCostAction()
    );

    if (ThMod.Amplified(this, AMP)) {
      AbstractDungeon.actionManager.addToBottom(
          new RefractionSparkAction(
              m,
              new DamageInfo(p, this.block, this.damageTypeForTurn)
          )
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new RefractionSparkAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn)
          )
      );
    }
  }

  public AbstractCard makeCopy() {
    return new RefractionSpark();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.ampNumber += UPG_AMP;
      this.isBlockModified = true;
      this.block = this.baseDamage + this.ampNumber;
    }
  }
}