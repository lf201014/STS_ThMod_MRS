package ThMod.cards.derivations;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;

public class GuidingStar extends CustomCard {

  public static final String ID = "GuidingStar";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/GuidingStar.png";
  private static final int COST = 1;
  private static final int UPG_COST = 0;

  public GuidingStar() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_DERIVATIONS,
        AbstractCard.CardRarity.SPECIAL,
        AbstractCard.CardTarget.SELF
    );
    this.exhaust = true;
    this.retain = true;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInDrawPileAction(
            this.makeStatEquivalentCopy(),
            1,
            true,
            true
        )
    );

    if (AbstractDungeon.player.discardPile.size() > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new EmptyDeckShuffleAction()
      );
      AbstractDungeon.actionManager.addToBottom(
          new ShuffleAction(AbstractDungeon.player.drawPile, false)
      );
    }
		/*
		p.drawPile.shuffle();
		
		for (AbstractRelic r : p.relics) {
			r.onShuffle();
	    }   
	    */
  }

  public void triggerAtStartOfTurn() {
    AbstractDungeon.actionManager.addToBottom(
        new GainEnergyAction(1)
    );
  }

  public AbstractCard makeCopy() {
    return new GuidingStar();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(UPG_COST);
    }
  }
}