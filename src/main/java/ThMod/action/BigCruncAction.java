package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod.ThMod;

public class BigCruncAction
	extends AbstractGameAction{
	private boolean upg = false; 
  
	public BigCruncAction(boolean upgraded){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
		this.upg = upgraded;
	}
  
	public void update(){
		this.isDone = false;
		
		AbstractPlayer p = AbstractDungeon.player;
		int div = 5;
		if (this.upg) {
			div --;
		}
		int cnt = p.discardPile.size() / 2;
		int total = cnt;
		p.discardPile.shuffle();
		
		ThMod.logger.info(
				"BigCruncAction : Discard size : "+p.discardPile.size()
				+" ; counter : "+cnt
				);
		
		if (cnt > 0) {
			for (int i = 0 ; i < cnt ; i++) {
				p.discardPile.moveToExhaustPile(p.discardPile.getTopCard());
			}
		}
		
		p.drawPile.shuffle();
		
	    cnt = p.drawPile.size() / 2;
		
		ThMod.logger.info(
				"BigCruncAction : Draw size : "+p.drawPile.size()
				+" ; counter : "+cnt
				);
	    
	    total += cnt;
	    
	    if (cnt > 0) {
	    	for (int i = 0 ; i < cnt ; i++) {
	    		p.drawPile.moveToExhaustPile(p.drawPile.getTopCard());
	    	}
	    }
	    
	    int res = total / div;
	    
	    ThMod.logger.info(
	    		"BigCruncAction : total :" + total
	    		+ " ; res : " + res
	    		);
	    
	    if (res > 0) {
	    	AbstractDungeon.actionManager.addToBottom(
	    			new GainEnergyAction(res)
	    			);
	    	AbstractDungeon.actionManager.addToBottom(
	    			new DrawCardAction(source, res)
	    			);
	    }
	    
		this.isDone = true;
	}
}