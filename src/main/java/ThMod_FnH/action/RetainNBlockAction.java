package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class RetainNBlockAction extends AbstractGameAction{
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
	public static final String[] TEXT = uiStrings.TEXT;
  
	public RetainNBlockAction(AbstractCreature source, int amount){
		setValues(AbstractDungeon.player, source, amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
  
	public void update(){
		if (this.duration == 0.5F){
			AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
			tickDuration();
			return;
		}
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
			int blc = 0;
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group){
				if (!c.isEthereal) {
					c.retain = true;
				}
				if (c.costForTurn > 0)
					blc += c.costForTurn*2;
				AbstractDungeon.player.hand.addToTop(c);
			}
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(source, source, blc));
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}
