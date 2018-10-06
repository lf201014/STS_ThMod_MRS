package ThMod_FnH.cards.Marisa;

import ThMod_FnH.ThMod;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class SuperPerseids extends CustomCard {

  public static final String ID = "SuperPerseids";
  public static final String IMG_PATH = "img/cards/SuperPerseids.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = -2;
  private static final int DMG = 15;
  private static final int UPG_DMG = 5;
  private static final int STACK = 2;

  public SuperPerseids() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.damage = this.baseDamage = DMG;
    this.magicNumber = this.baseMagicNumber = STACK;
    this.damageType = DamageType.THORNS;
    this.damageTypeForTurn = DamageType.THORNS;
  }

  public void triggerWhenDrawn() {
    this.applyPowers();
    ThMod.logger.info("SuperPerseids : triggerWhenDrawn : Granting Charge-up "
        + "; : upgraded : " + this.upgraded
    );
    AbstractPlayer p = AbstractDungeon.player;
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new ChargeUpPower(p,this.magicNumber),
            this.magicNumber
        )
    );
  }

  @Override
  public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    return false;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
  }

  public void triggerOnExhaust() {
    this.applyPowers();
    ThMod.logger.info("SuperPerseids : triggerOnExhaust : Damaging Random Enemy :"
        + "; upgraded : " + this.upgraded
        + "; damage : " + this.damage
    );
    AbstractDungeon.actionManager.addToBottom(
        new DamageRandomEnemyAction(
            new DamageInfo(
                AbstractDungeon.player,
                this.damage,
                DamageType.THORNS
            ),
            AttackEffect.FIRE
        )
    );
  }

  public AbstractCard makeCopy() {
    return new SuperPerseids();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
    }
  }
}