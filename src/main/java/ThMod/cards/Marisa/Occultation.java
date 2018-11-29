package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.action.OccultationAction;
import ThMod.patches.AbstractCardEnum;

public class Occultation extends CustomCard {

  public static final String ID = "Occultation";
  public static final String IMG_PATH = "img/cards/occultation.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final String EXT_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION[0];
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 2;
  private static final int UPG_COST = 1;
  private static final int BLOCK_AMT = 0;

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

  @Override
  public void applyPowers() {
    if (AbstractDungeon.player.drawPile.size() >= 0) {
      this.baseBlock = AbstractDungeon.player.drawPile.size();
      this.rawDescription = DESCRIPTION + EXT_DESCRIPTION;
      initializeDescription();
    }
    super.applyPowers();
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    this.block = this.baseBlock = 0;
    initializeDescription();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (!AbstractDungeon.player.drawPile.isEmpty()) {
      AbstractDungeon.actionManager.addToBottom(
          new OccultationAction()
      );
    }
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    /*
    if (this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(p, p, this.block)
      );
    }
    */
  }

  public AbstractCard makeCopy() {
    return new Occultation();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(UPG_COST);
      //this.rawDescription = (this.rawDescription + " NL Gain !B! block.");
      //initializeDescription();
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
    }
  }
}