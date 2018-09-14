package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod_FnH.action.OccultationAction;
import ThMod_FnH.patches.AbstractCardEnum;

public class Occultation extends CustomCard {

  public static final String ID = "Occultation";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 3;

  public Occultation() {
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

    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (!AbstractDungeon.player.drawPile.isEmpty()) {
      AbstractDungeon.actionManager.addToBottom(
          new OccultationAction()
      );
    }
    if (this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(p, p, this.block)
      );
    }
  }

  public AbstractCard makeCopy() {
    return new Occultation();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = (this.rawDescription + " NL Gain !B! block.");
      initializeDescription();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}