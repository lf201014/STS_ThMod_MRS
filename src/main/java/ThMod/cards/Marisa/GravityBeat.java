package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class GravityBeat extends CustomCard {

  public static final String ID = "GravityBeat";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/GravityBeat.png";
  private static final int COST = 2;
  private static final int ATTACK_DMG = 9;
  private static final int UPGRADE_PLUS_DMG = 3;
  private static final int WK = 2;
  private static final int UPG_WK = 1;

  public GravityBeat() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.ALL_ENEMY
    );

    this.isMultiDamage = true;
    this.baseDamage = this.damage = ATTACK_DMG;
    this.magicNumber = this.baseMagicNumber = WK;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            this.multiDamage,
            this.damageTypeForTurn,
            AttackEffect.SLASH_DIAGONAL
        )
    );

    if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
      for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                mo,
                p,
                new WeakPower(mo, this.magicNumber, false),
                this.magicNumber,
                true,
                AttackEffect.NONE
            )
        );
      }
    }
  }

  public AbstractCard makeCopy() {
    return new GravityBeat();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_WK);
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}