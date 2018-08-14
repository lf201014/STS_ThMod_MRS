package ThMod_FnH.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ThMod_FnH.ThMod;

import com.megacrit.cardcrawl.localization.PowerStrings;

public class AsteroidBeltPower
	extends AbstractPower{
	public static final String POWER_ID = "AsteroidBeltPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public boolean isAttacked;
	
	public AsteroidBeltPower(AbstractCreature owner){
	 this.name = NAME;
	 this.ID = POWER_ID;
	 this.owner = owner;
	 this.type = AbstractPower.PowerType.BUFF;
	 updateDescription();
	 this.img = new Texture("img/powers/dodgeRoll.png");
	 this.isAttacked = false;
  	}

	public int onAttacked(DamageInfo info, int damageAmount){
	    if (info.type == DamageInfo.DamageType.NORMAL) {
	    	this.isAttacked = true;
	    	ThMod.logger.info("AsteroidBeltPower : Attacked");
		    AbstractDungeon.actionManager.addToTop(
		    		new RemoveSpecificPowerAction( owner , owner , this )
		    		);
	    }
	    	
		return damageAmount;
	}
	
	@Override
	public void stackPower(int stackAmount) {}
 
	public void atStartOfTurn() {
		flash();
		AbstractDungeon.actionManager.addToBottom(
				new GainBlockAction(
						AbstractDungeon.player,
						AbstractDungeon.player,
						this.amount
						)
				);
	    AbstractDungeon.actionManager.addToBottom(
	    		new RemoveSpecificPowerAction( owner , owner , this )
	    		);
	}
	
	public void atEndOfRound() {
		flash();
		this.amount = AbstractDungeon.player.currentBlock;
		updateDescription();
	}
  
public void updateDescription(){
    this.description = (DESCRIPTIONS[0]);
 	}
}