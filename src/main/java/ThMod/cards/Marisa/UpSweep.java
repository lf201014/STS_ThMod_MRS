package ThMod.cards.Marisa;

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
import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.ChargeUpPower;

public class UpSweep
    extends CustomCard {

  public static final String ID = "UpSweep";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/UpSweep.png";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 4;
  private static final int UPGRADE_PLUS_DMG = 1;
  private static final int CHG_GAIN = 1;
  private static final int UPG_CHG = 1;

  public UpSweep() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.BASIC,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = this.damage = ATTACK_DMG;
    this.magicNumber = this.baseMagicNumber = CHG_GAIN;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new ChargeUpPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new UpSweep();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_CHG);
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}