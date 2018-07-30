package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class StarDustReverieAction
	extends AbstractGameAction{
	private AbstractPlayer p;
	private boolean upgraded = false;
  
	public StarDustReverieAction(boolean armamentsPlus){
		this.duration = Settings.ACTION_DUR_FAST;
		this.upgraded = armamentsPlus;
		this.p = AbstractDungeon.player;
	}
  
	public void update(){
		this.isDone = false;
		int cnt = 0;
		
		ThMod.logger.info("StarDustReverieAction : player hand size : "+p.hand.size());
		if (!p.hand.isEmpty())
			while (!p.hand.isEmpty()) {
				AbstractCard c = p.hand.getTopCard();
				ThMod.logger.info("StarDustReverieAction : moving "+c.cardID);
					p.hand.moveToDeck(c, true);
				cnt++;
				ThMod.logger.info("StarDustReverieAction : Counter : "+cnt);
			}

		for (int i=0;i<=cnt;i++) {

			AbstractCard c = AbstractDungeon.getCard(AbstractDungeon.rollRarity(AbstractDungeon.miscRng), AbstractDungeon.miscRng).makeStatEquivalentCopy();
	        	
			ThMod.logger.info("StarDustReverieAction : adding "+c.cardID);
				
			if (this.upgraded)
				c.upgrade();
	        	
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			}
	    
		this.isDone = true;
	}
}