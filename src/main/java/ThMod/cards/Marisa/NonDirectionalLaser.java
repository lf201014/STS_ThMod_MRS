package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class NonDirectionalLaser
    extends CustomCard {

  public static final String ID = "NonDirectionalLaser";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/NonDirectLaser.png";
  private static final int COST = 1;
  private static final int ATK_DMG = 5;
  private static final int UPG_DMG = 2;

  public NonDirectionalLaser() {
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
    this.isMultiDamage = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            multiDamage,
            damageTypeForTurn,
            AttackEffect.SLASH_HORIZONTAL
        )
    );

    AbstractDungeon.actionManager.addToBottom(
        new DamageRandomEnemyAction(
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL
        )
    );

  }

  public AbstractCard makeCopy() {
    return new NonDirectionalLaser();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
    }
  }
}
