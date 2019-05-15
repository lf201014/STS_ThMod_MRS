package ThMod.cards.Marisa;

import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AbsoluteMagnitude
    extends AmplifiedAttack {

  public static final String ID = "AbsoluteMagnitude";
  private static final CardStrings cardStrings =
      CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/absMagni.png";
  private static final int COST = 2;
  private static final float ATK_MULT = 2F;
  private static final float ATK_MULT_UPG = 3F;
  private float multiplier;

  public AbsoluteMagnitude() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.ENEMY
    );

    this.damage = this.baseDamage = 0;
    this.multiplier = ATK_MULT;
    this.block = this.baseBlock = 0;
  }

  public void applyPowers() {
    AbstractPlayer p = AbstractDungeon.player;
    if (p.hasPower("ChargeUpPower")) {
      this.ampNumber = (int) (p.getPower("ChargeUpPower").amount * this.multiplier);
    }
    super.applyPowers();
    this.isBlockModified = true;
  }

  public void onMoveToDiscard() {
    this.ampNumber = 0;
    super.applyPowers();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.block, this.damageTypeForTurn),
            AttackEffect.SLASH_DIAGONAL
        )
    );
  }

  public AbstractCard makeCopy() {
    return new AbsoluteMagnitude();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.multiplier = ATK_MULT_UPG;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}