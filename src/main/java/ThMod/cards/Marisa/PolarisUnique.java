package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomCard;
import ThMod.ThMod;
import ThMod.cards.derivations.GuidingStar;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.PolarisUniquePower;

public class PolarisUnique extends CustomCard {

  public static final String ID = "PolarisUnique";
  public static final String IMG_PATH = "img/cards/temp/PolUni.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int UPG_COST = -1;

  public PolarisUnique() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.RARE,
        CardTarget.SELF
    );
    this.isInnate = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    ThMod.logger.info("Applying Power:PolarisUnique");
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new PolarisUniquePower(p)
        )
    );

    ThMod.logger.info("Adding card to deck : GuidingStar");
    AbstractCard c = new GuidingStar();
    /*
    if (this.upgraded) {
      c.upgrade();
    }
    */
    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInDrawPileAction(c, 1, true, true)
    );

    ThMod.logger.info("Shuffling.");

    p.drawPile.shuffle();
    for (AbstractRelic r : p.relics) {
      r.onShuffle();
    }

    ThMod.logger.info("PolarisUnique : Finished.");
  }

  public AbstractCard makeCopy() {
    return new PolarisUnique();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      updateCost(UPG_COST);
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
    }
  }
}