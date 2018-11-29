package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class StarBarrage
    extends CustomCard {

  public static final String ID = "StarBarrage";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/temp/StarBarrage.png";
  private static final int COST = 1;
  private static final int ATK_DMG = 7;
  private static final int UPGRADE_PLUS_DMG = 3;
  private static final int TAP = 1;

  public StarBarrage() {
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
    this.magicNumber = this.baseMagicNumber = TAP;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    this.magicNumber = this.baseMagicNumber;
    for (int i = 0; i < this.magicNumber; i++) {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AttackEffect.FIRE
          )
      );
    }
    this.upgradeMagicNumber(TAP);
    this.applyPowers();
  }

  public AbstractCard makeCopy() {
    return new ThMod.cards.Marisa.StarBarrage();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}