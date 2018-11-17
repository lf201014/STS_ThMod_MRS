package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlackFlareStarAction extends AbstractGameAction{
	private int blc;
  
	public BlackFlareStarAction(int block) {
		setValues(AbstractDungeon.player, AbstractDungeon.player, -1);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.blc = block;
	}
  
	public void update() {
		if (this.duration == 0.5F){
			AbstractDungeon.handCardSelectScreen.open("Discard", 99, true, true);
      
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
			tickDuration();
			return;
		}
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
			if (!AbstractDungeon.
					handCardSelectScreen.selectedCards.group.isEmpty()){
				int cnt = AbstractDungeon.
						handCardSelectScreen.selectedCards.group.size();
				AbstractDungeon.actionManager.addToTop(
						new GainBlockAction(source, source, blc*cnt)
						);
				
				for (AbstractCard c : 
						AbstractDungeon.handCardSelectScreen.selectedCards.group){
					AbstractDungeon.player.hand.moveToDiscardPile(c);
					GameActionManager.incrementDiscard(false);
					c.triggerOnManualDiscard();
				}
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}
