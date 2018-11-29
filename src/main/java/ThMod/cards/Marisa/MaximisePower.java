package ThMod.cards.Marisa;

import ThMod.cards.derivations.Exhaustion_MRS;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.MPPower;
import basemod.abstracts.CustomCard;

public class MaximisePower extends CustomCard {

  public static final String ID = "MaximisePower";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/temp/MaxP.png";
  private static final int COST = 3;

  public MaximisePower() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = 2;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (p.hasPower("ChargeUpPower")) {
      if (p.getPower("ChargeUpPower").amount > 0) {
        AbstractDungeon.actionManager.addToBottom(
            new GainEnergyAction(p.getPower("ChargeUpPower").amount)
        );
        p.getPower("ChargeUpPower").amount = 0;
      }
    }
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new MPPower(p, 1),
            1
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(
            new Exhaustion_MRS(),
            1
        )
    );
  }

  public AbstractCard makeCopy() {
    return new MaximisePower();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      updateCost(-1);
      //upgradeMagicNumber(1);
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
      //this.exhaust = false;
    }
  }
}