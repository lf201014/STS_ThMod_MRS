package ThMod.cards.derivations;

import ThMod.abstracts.AmplifiedAttack;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;

public class WhiteDwarf extends AmplifiedAttack {

  public static final String ID = "WhiteDwarf";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/pride.png";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 0;
  private static final int HAND_REQ = 4;
  private static final float MULTIPLIER = 1.5f;
  private static final float MULTIPLIER_UPG = 2.0f;

  private float magn = MULTIPLIER;

  public WhiteDwarf() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_DERIVATIONS,
        AbstractCard.CardRarity.SPECIAL,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = this.damage = ATTACK_DMG;

  }

  @Override
  public void applyPowers() {
    AbstractPlayer player = AbstractDungeon.player;
    this.ampNumber = (int) (Math.floor(player.discardPile.size() * this.magn));
    super.applyPowers();
  }

  @Override
  public void calculateDamageDisplay(AbstractMonster mo) {
    calculateCardDamage(mo);
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    AbstractPlayer player = AbstractDungeon.player;
    this.ampNumber = (int) (Math.floor(player.discardPile.size() * this.magn));
    super.applyPowers();
  }

  public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    if (p.hand.size() <= HAND_REQ) {
      return true;
    } else {
      this.cantUseMessage = EXTENDED_DESCRIPTION[0];
      return false;
    }
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.block, this.damageTypeForTurn),
            AttackEffect.SLASH_DIAGONAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(new Burn(), 2)
    );
  }

  public AbstractCard makeCopy() {
    return new WhiteDwarf();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.magn = MULTIPLIER_UPG;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}