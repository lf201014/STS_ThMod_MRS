package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscToHandRandAction extends AbstractGameAction{
	
	public DiscToHandRandAction(){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
	@Override
	public void update() {
		AbstractPlayer p = AbstractDungeon.player;
		if (p.discardPile.isEmpty()) {
			this.isDone = true;
			return;
		} else {
			int rng = AbstractDungeon.miscRng.random(0, p.discardPile.size()-1);
			AbstractCard card = p.discardPile.group.get(rng);
			p.hand.addToHand(card);
			card.lighten(false);
			p.discardPile.removeCard(card);
			p.hand.refreshHandLayout();
			this.isDone = true;
			return;
		}
	}
}
