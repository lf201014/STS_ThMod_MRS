package ThMod.powers.deprecated;

import ThMod.powers.Marisa.ChargeUpPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

@Deprecated
public class DarkSparkPower extends AbstractPower {

  public static final String POWER_ID = "DarkSparkPower";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public DarkSparkPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/hemokinesis.png");
  }

  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    if (ThMod.isSpark(card)) {
      flash();
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              owner,
              owner,
              new ChargeUpPower(this.owner, this.amount),
              amount
          )
      );
    }
  }

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
      AbstractDungeon.actionManager.addToBottom(
          new RemoveSpecificPowerAction(this.owner, this.owner, this)
      );
    }
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }
}