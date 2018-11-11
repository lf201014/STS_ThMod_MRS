package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import ThMod.ThMod;

public class MagicChantAction extends AbstractGameAction{
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
	public static final String[] TEXT = uiStrings.TEXT;
  
	public MagicChantAction(AbstractCreature source, int amount){
		setValues(AbstractDungeon.player, source, amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	public void update(){
		if (this.duration == 0.5F){
			AbstractDungeon.handCardSelectScreen.open(
					TEXT[0], this.amount, false, true, false, false, true
					);
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
			tickDuration();
			return;
		}
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
			if (AbstractDungeon.handCardSelectScreen.selectedCards.size()>0) {
				for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group){
					ThMod.logger.info("RetainNBlockAction : Retaining :"+c.name);
					
					if (c.canUpgrade()) {
						c.upgrade();
					}
					if (!c.isEthereal) {
						c.retain = true;
					}
					/*
					ThMod.logger.info("RetainNBlockAction : Applying power for :"+c.name);
					
					AbstractDungeon.actionManager.addToBottom(
							new ApplyPowerAction(
									this.source,this.source,
									new MagicChantPower(this.source,c)
									)
							);
							*/
					AbstractDungeon.player.hand.addToTop(c);
				}
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}
