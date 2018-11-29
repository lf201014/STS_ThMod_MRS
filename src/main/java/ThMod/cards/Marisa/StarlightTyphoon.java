package ThMod.cards.Marisa;

import ThMod.abstracts.AmplifiedAttack;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
  public static final String IMG_PATH = "img/cards/temp/Typhoon.png";
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
        CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY
    );
    this.magicNumber = this.baseMagicNumber = MULT;
    this.damage = this.baseDamage = 0;
    this.isMultiDamage = true;
  }

  @Override
  public void applyPowers() {
    counter = 0;
    for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
      if ((c.costForTurn == 0) || (c.costForTurn <= -2)){
        counter ++;
      }
    }
    if (counter > 0) {
      this.ampNumber = this.magicNumber * counter;
      this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
      initializeDescription();
      super.applyPowers();
    }
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    super.calculateCardDamage(mo);
    this.rawDescription = DESCRIPTION;
    this.rawDescription += EXTENDED_DESCRIPTION[0];
    initializeDescription();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            this.multiAmpDamage,
            this.damageTypeForTurn,
            AttackEffect.FIRE
        )
    );
  }

  public AbstractCard makeCopy() {
    return new StarlightTyphoon();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MULT);
    }
  }
}