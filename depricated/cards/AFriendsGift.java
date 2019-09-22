package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;

@Deprecated
public class AFriendsGift
    extends AmplifiedAttack {

  public static final String ID = "AFriendsGift_1";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 6;
  private static final int UPG_DMG = 3;
  private static final int AMP_DMG = 6;
  private static final int UPG_AMP = 3;
  private static final int AMP = 1;


  public AFriendsGift() {
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
    this.exhaust = true;
    if (ThMod.Amplified(this, AMP)) {
      this.exhaust = false;
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.block, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    }
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  public void atTurnStart() {
    ThMod.logger.info(
        "AFriendsGift_1 : atTurnStart : Upgrading damage : base :" + this.baseDamage +
            " ; damage : " + this.damage +
            " ; Amplified base : " + this.baseBlock +
            " ; AMplified damage : " + this.block
    );
    this.upgradeDamage(2);
    ThMod.logger.info(
        "AFriendsGift_1 : atTurnStart : upgraded damage : base :" + this.baseDamage +
            " ; damage : " + this.damage +
            " ; Amplified base : " + this.baseBlock +
            " ; AMplified damage : " + this.block
    );
  }

  public AbstractCard makeCopy() {
    return new AFriendsGift();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.ampNumber += UPG_AMP;
      this.block = this.baseDamage + this.ampNumber;
      this.isBlockModified = true;
    }
  }
}