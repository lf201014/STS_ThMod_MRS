package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;

@Deprecated
public class FluorensentBeam
    extends AmplifiedAttack {

  public static final String ID = "FluorensentBeam";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 7;
  private static final int UPG_DMG = 3;
  private static final int AMP_DMG = 13;
  private static final int UPG_AMP = 2;
  private static final int AMP = 2;


  public FluorensentBeam() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ALL_ENEMY
    );

    this.baseDamage = ATK_DMG;
    this.ampNumber = AMP_DMG;
    this.baseBlock = this.baseDamage + this.ampNumber;
    this.isMultiDamage = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));

    if (ThMod.Amplified(this, AMP)) {
      AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,
          this.multiAmpDamage, DamageType.HP_LOSS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    } else {
      AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,
          this.multiDamage, DamageType.HP_LOSS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
  }

  public AbstractCard makeCopy() {
    return new FluorensentBeam();
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