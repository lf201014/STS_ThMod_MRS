package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;

public class BlazingStar
    extends AmplifiedAttack {

  public static final String ID = "BlazingStar";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/BlazingStar.png";

  private static final int COST = 2;
  private static final int ATK_DMG = 14;
  private static final int UPG_DMG = 4;
  private static final int AMP_DMG = 7;
  private static final int UPG_AMP = 2;
  private static final int AMP = 1;

  public BlazingStar() {
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

    this.block = this.baseBlock = this.damage = this.baseDamage = ATK_DMG;
    this.magicNumber = this.baseMagicNumber = AMP_DMG;
    this.isException = true;
  }

  @Override
  public void applyPowers() {
    AbstractPlayer p = AbstractDungeon.player;
    this.block = this.baseDamage;
    for (AbstractCard c : p.hand.group) {
      if ((c instanceof Burn)) {
        this.block += this.magicNumber;
      }
    }
    super.applyPowers();
  }

  @Override
  public void calculateDamageDisplay(AbstractMonster mo) {
    calculateCardDamage(mo);
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    AbstractPlayer p = AbstractDungeon.player;
    this.block = this.baseDamage;
    for (AbstractCard c : p.hand.group) {
      if ((c instanceof Burn)) {
        this.block += this.magicNumber;
      }
    }
    super.calculateCardDamage(mo);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      this.block *= 2;
    }
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.block, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.FIRE
        )
    );
  }

  public AbstractCard makeCopy() {
    return new BlazingStar();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      upgradeMagicNumber(UPG_AMP);
      this.block = this.baseBlock = this.baseDamage;
      this.isBlockModified = true;
    }
  }
}