package ThMod.cards.Marisa;

import static ThMod.patches.CardTagEnum.SPARK;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class DarkSpark
    extends CustomCard {

  public static final String ID = "DarkSpark";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/darkSpark.png";

  private static final int COST = 2;
  private static final int ATK_DMG = 6;
  private static final int UPG_DMG = 2;

  public DarkSpark(){
    this(0);
  }

  public DarkSpark(int timeUpgraded) {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY
    );

    this.tags.add(SPARK);
    this.baseDamage = ATK_DMG;
    this.isMultiDamage = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    /*
    AbstractDungeon.actionManager.addToBottom(
        new ExhaustAllNonAttackAction()
    );
    */
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            this.multiDamage,
            this.damageTypeForTurn,
            AbstractGameAction.AttackEffect.NONE
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            this.multiDamage,
            this.damageTypeForTurn,
            AbstractGameAction.AttackEffect.NONE
        )
    );
  }

  public boolean canUpgrade()
  {
    return true;
  }

  public AbstractCard makeCopy() {
    return new DarkSpark(timesUpgraded);
  }

  public void upgrade() {
    upgradeDamage(UPG_DMG + this.timesUpgraded);
    this.timesUpgraded += 1;
    this.upgraded = true;
    this.name = (cardStrings.NAME + "+" + this.timesUpgraded);
    initializeTitle();
  }
}