package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class HandCheckAction
	extends AbstractGameAction {
	private boolean upg;
	
	public HandCheckAction(boolean upgraded){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	    this.duration = Settings.ACTION_DUR_FAST;
	    this.upg = upgraded;
	}

	public void update() {
	    if (this.duration == Settings.ACTION_DUR_FAST) {
	    	for (AbstractCard c:AbstractDungeon.player.hand.group) {
	    		c.exhaust = true;
	    		if (!this.upg)
	    			c.isEthereal = true;
	    		ThMod.logger.info(
	    				"HandCheckAction : id : " + c.cardID +
	    				" ; cost for turn : " + c.costForTurn +
	    				" ; is Ethereal : " + c.isEthereal +
	    				" ; Exhaust : " + c.exhaust
	    				);
	    	}
	    }
	    tickDuration();
		return;
	}
}