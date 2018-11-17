package ThMod.cards.Marisa;

import ThMod.action.ConsumeChargeUpAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class ManaConvection extends CustomCard {

  public static final String ID = "ManaConvection";
  public static final String IMG_PATH = "img/cards/ManaConvection.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int DRAW = 2;
  private static final int UPG_DRAW = 1;
  private static final int EXHT = 2;
  private static final int ENEG_GAIN = 2;
  private static final int CHRG_DRAIN = 8;

  public ManaConvection() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = DRAW;

  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(p, this.magicNumber)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ExhaustAction(p, p, EXHT, false)
    );

    int drain = CHRG_DRAIN;
    int ene_gain = ENEG_GAIN;
    if (p.hasRelic("SimpleLauncher")) {
      drain = 6;
    }

    if (p.hasPower("ChargeUpPower")) {
      if (p.getPower("ChargeUpPower").amount >= drain) {
        AbstractDungeon.actionManager.addToBottom(
            new GainEnergyAction(ene_gain)
        );
        AbstractDungeon.actionManager.addToBottom(
            new ConsumeChargeUpAction(drain)
        );
      }
    }
  }

  public AbstractCard makeCopy() {
    return new ManaConvection();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_DRAW);
    }
  }
}