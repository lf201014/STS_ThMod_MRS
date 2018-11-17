package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;
import ThMod.action.DiscToHandATKOnly;

public class EventHorizonPower
    extends AbstractPower {

  public static final String POWER_ID = "EventHorizonPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private int cnt;

  public EventHorizonPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/darkness.png");
    this.cnt = amount;
  }

  public void atStartOfTurnPostDraw() {
    this.cnt = this.amount;
  }

  public void stackPower(int stackAmount) {
    super.stackPower(stackAmount);
    this.cnt += stackAmount;
  }

  public void onSpecificTrigger() {
    ThMod.logger.info("EventHorizonPower : Checking ; counter : " + this.cnt);
    if (this.cnt <= 0) {
      return;
    }

    ThMod.logger.info("EventHorizonPower : Action");
    AbstractPlayer p = AbstractDungeon.player;
    if (!p.discardPile.isEmpty()) {
      flash();
      AbstractDungeon.actionManager.addToBottom(
          new DiscToHandATKOnly(1)
      );
      this.cnt--;
    }

    ThMod.logger.info("EventHorizonPower : Done ; counter : " + this.cnt);
  }

  public void updateDescription() {
    this.description = (
        DESCRIPTIONS[0]
            + this.amount
            + DESCRIPTIONS[1]
            + DESCRIPTIONS[2]
            + this.cnt
            + DESCRIPTIONS[3]
    );
  }

}