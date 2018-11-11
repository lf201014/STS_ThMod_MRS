package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MPPower extends AbstractPower {

  public static final String POWER_ID = "MPPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public int ene;

  public MPPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/doubleDamage.png");
  }

  public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
    if (type == DamageInfo.DamageType.NORMAL) {
      return (float) (damage * Math.pow(2, this.amount));
    }
    return damage;
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + Math.pow(2, this.amount) + DESCRIPTIONS[1]);
  }

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }
  }
	/*
	@Override
	public void onDrawOrDiscard() {
		ThMod.logger.info("MPPower : onDrawOrDiscard : DiscardNoneAttack");
		DiscardNoneAttack();
	}
	
	@Override
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		ThMod.logger.info("MPPower : onApplyPower : DiscardNoneAttack");
		DiscardNoneAttack();
	}
	@Override
	public void onInitialApplication() {
		ThMod.logger.info("MPPower : onInitialApplication : DiscardNoneAttack");
		DiscardNoneAttack();
	}
	
	private void DiscardNoneAttack() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c.type != CardType.ATTACK) {
				AbstractDungeon.actionManager.addToBottom(
						new DiscardSpecificCardAction(c)
					);
			}
		}
	}
	
	
	*/
}