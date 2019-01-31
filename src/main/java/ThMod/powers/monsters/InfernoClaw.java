package ThMod.powers.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class InfernoClaw
    extends AbstractPower {

  public static final String POWER_ID = "InfernoClaw";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public InfernoClaw(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    updateDescription();
    this.img = new Texture("img/powers/thrillseeker.png");
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0];
  }

  @Override
  public void stackPower(int amount) {
  }

  public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
    if (damageAmount > 0 && info.type != DamageType.THORNS) {
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInDrawPileAction(new Burn(), 1, true, true)
      );
    }

  }
}

