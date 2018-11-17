package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

@Deprecated
public class StarBarrage_0
    extends CustomCard {

  public static final String ID = "StarBarrage_0";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 4;
  private static final int UPGRADE_PLUS_DMG = 2;
  private static final int PILE_NUM = 15;
  private static final int UP_PILE_NUM = -3;

  public StarBarrage_0() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY);

    this.baseDamage = ATTACK_DMG;
    this.magicNumber = this.baseMagicNumber = PILE_NUM;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    if (p.drawPile.size() >= this.magicNumber) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));
    }

    if (p.discardPile.size() >= this.magicNumber) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
    }
  }

  public AbstractCard makeCopy() {
    return new StarBarrage_0();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      upgradeMagicNumber(UP_PILE_NUM);
    }
  }
}