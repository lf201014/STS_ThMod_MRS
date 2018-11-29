package ThMod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod.ThMod;


public class OccultationAction
	extends AbstractGameAction {
	private AbstractPlayer p;
	
	public OccultationAction(){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	    this.duration = Settings.ACTION_DUR_FAST;
		p = AbstractDungeon.player;
	}

	public void update() {
		if (AbstractDungeon.player.drawPile.group.isEmpty()) {
			return;
		}
		

		ArrayList<AbstractCard> cards = AbstractDungeon.player.drawPile.group;
		//int cnt = 0;
		ThMod.logger.info(("Draw pile:"+cards.size()));
		
		while (!p.drawPile.isEmpty()) {
			AbstractCard c = p.drawPile.getTopCard();
			
			p.drawPile.moveToDiscardPile(c);
			GameActionManager.incrementDiscard(false);
			c.triggerOnManualDiscard();
			p.drawPile.removeCard(c);
			
			//cnt++;
			
		}
		//AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, cnt));
		this.isDone = true;
		return;
	}
}
