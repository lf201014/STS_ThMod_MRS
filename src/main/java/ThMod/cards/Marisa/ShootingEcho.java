package ThMod.cards.Marisa;

import ThMod.action.ShootingEchoAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class ShootingEcho
    extends CustomCard {

  public static final String ID = "ShootingEcho";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/echo.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 10;
  private static final int UPGRADE_PLUS_DMG = 4;

  public ShootingEcho() {
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

    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    addToTop(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AttackEffect.FIRE
        )
    );

    addToBot(
        new ShootingEchoAction(this)
    );
  }

  public AbstractCard makeCopy() {
    return new ShootingEcho();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      //initializeDescription();
    }
  }
}