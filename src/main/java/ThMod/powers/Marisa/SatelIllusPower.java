package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

public class SatelIllusPower extends AbstractPower {

  public static final String POWER_ID = "SatelIllusPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private static int counter;

  public void checkDrawPile() {
    int temp = AbstractDungeon.player.drawPile.size();
    ThMod.logger.info(
        "SatelIllusPower : checkDrawPile :"
            + " counter : " + counter
            + " ; drawPile size " + temp
            + " ; grant energy :" + (temp > counter)
    );
    if (temp > counter) {
      this.flash();
      AbstractDungeon.actionManager.addToBottom(
          new GainEnergyAction(this.amount)
      );
    }
    if (temp != counter) {
      counter = temp;
    }
  }

  public SatelIllusPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/absorb.png");
    counter = AbstractDungeon.player.drawPile.size();
  }

  @Override
  public void onDrawOrDiscard() {
    ThMod.logger.info("SatelIllusPower : onDrawOrDiscard : checkDrawPile");
    checkDrawPile();
  }

  @Override
  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
    ThMod.logger.info("SatelIllusPower : onApplyPower : checkDrawPile");
    checkDrawPile();
  }

  @Override
  public void onInitialApplication() {
    ThMod.logger.info("SatelIllusPower : onInitialApplication : checkDrawPile");
    checkDrawPile();
  }

  public void atEndOfRound() {
    ThMod.logger.info("SatelIllusPower : checkDrawPile : atEndOfRound ");
    checkDrawPile();
  }

  public void onAfterUseCard(AbstractCard card, UseCardAction action) {
    ThMod.logger.info("SatelIllusPower : checkDrawPile : onAfterUseCard : " + card.cardID);
    checkDrawPile();
  }

  public void atStartOfTurnPostDraw() {
    ThMod.logger.info("SatelIllusPower : checkDrawPile : atStartOfTurnPostDraw ");
    checkDrawPile();
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}