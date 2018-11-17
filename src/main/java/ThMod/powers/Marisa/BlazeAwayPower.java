package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlazeAwayPower
    extends AbstractPower {

  public static final String POWER_ID = "BlazeAwayPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public BlazeAwayPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/burst.png");
  }


  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    ThMod.logger.info("BlazeWayPower : card type check");
    if (card.type != CardType.ATTACK) {
      return;
    }

    ThMod.logger.info("BlazeWayPower : done checking");

    flash();
    AbstractCard c = card.makeStatEquivalentCopy();
    c.costForTurn = 0;

    ThMod.logger.info("BlazeWayPower : adding " + this.amount + " : " + c.cardID);

    AbstractDungeon.actionManager.addToBottom(
        new MakeTempCardInHandAction(c, this.amount)
    );

    ThMod.logger.info("BlazeWayPower : removing power");

    AbstractDungeon.actionManager.addToBottom(
        new RemoveSpecificPowerAction(owner, owner, this)
    );

    ThMod.logger.info("BlazeWayPower : all done");
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
      AbstractDungeon.actionManager.addToBottom(
          new RemoveSpecificPowerAction(this.owner, this.owner, this)
      );
    }
  }
}