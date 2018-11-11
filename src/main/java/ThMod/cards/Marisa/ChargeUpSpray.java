package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class ChargeUpSpray
    extends CustomCard {

  public static final String ID = "ChargeUpSpray";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/ChargeUpSpray.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 8;
  private static final int UPGRADE_PLUS_DMG = 4;
  private static final int DRAW = 2;
  //private static final int UPG_DRAW = 0;

  public ChargeUpSpray() {
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

    this.baseDamage = ATTACK_DMG;
    this.magicNumber = this.baseMagicNumber = DRAW;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = 8;
    if (p.hasRelic("SimpleLauncher")) {
      cnt = 6;
    }
    if (p.hasPower("ChargeUpPower")) {
      if (p.getPower("ChargeUpPower").amount >= cnt) {
        AbstractDungeon.actionManager.addToTop(
            new DrawCardAction(AbstractDungeon.player, this.magicNumber)
        );
        AbstractDungeon.actionManager.addToTop(
            new GainEnergyAction(1)
        );
      }
    }
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
  }

  public AbstractCard makeCopy() {
    return new ChargeUpSpray();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      //upgradeMagicNumber(UPG_DRAW);
    }
  }
}