package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class MysteriousBeam
    extends CustomCard {

  public static final String ID = "MysteriousBeam";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/MysteriousBeam.png";

  private static final int COST = 1;

  public MysteriousBeam() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY
    );
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    float tmp = this.baseDamage;
    if (mo != null) {
      for (AbstractPower p : mo.powers) {
        tmp = p.atDamageReceive(tmp, this.damageTypeForTurn);
      }
      for (AbstractPower p : mo.powers) {
        tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn);
        if (this.baseDamage != (int) tmp) {
          this.isDamageModified = true;
        }
      }
    }
    this.damage = (int) tmp;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractCard c =
        AbstractDungeon.returnTrulyRandomCardInCombat(
            AbstractCard.CardType.ATTACK
        ).makeCopy();
    if (this.upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(c, true)
    );
    c.applyPowers();

    this.baseDamage = c.damage;
    this.calculateCardDamage(m);

    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(
                p,
                this.damage,
                this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
  }

  public AbstractCard makeCopy() {
    return new MysteriousBeam();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}