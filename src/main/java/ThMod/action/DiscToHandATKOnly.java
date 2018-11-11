package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DiscToHandATKOnly extends AbstractGameAction{
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AttackFromDeckToHandAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private AbstractPlayer p;
  
	public DiscToHandATKOnly(int amount){
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
	}
  
	public void update(){
		CardGroup tmp;
		if (this.duration == Settings.ACTION_DUR_MED){
			tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			for (AbstractCard c : this.p.discardPile.group) {
				if (c.type == AbstractCard.CardType.ATTACK) {
					tmp.addToRandomSpot(c);
				}
			}
			if (tmp.size() == 0){
				this.isDone = true;
				return;
			}
			if (tmp.size() == 1){
				AbstractCard card = tmp.getTopCard();
				if (this.p.hand.size() == 10){
					this.p.createHandIsFullDialog();
				}
				else{
					card.unhover();
					card.lighten(true);
					card.setAngle(0.0F);
					card.drawScale = 0.12F;
					card.targetDrawScale = 0.75F;
					card.current_x = CardGroup.DRAW_PILE_X;
					card.current_y = CardGroup.DRAW_PILE_Y;
					this.p.discardPile.removeCard(card);
					AbstractDungeon.player.hand.addToTop(card);
					AbstractDungeon.player.hand.refreshHandLayout();
					AbstractDungeon.player.hand.applyPowers();
				}
				this.isDone = true;
				return;
			}
			AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
			tickDuration();
			return;
		}
		if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards){
				c.unhover();
				if (this.p.hand.size() == 10){
					this.p.createHandIsFullDialog();
				}
				else{
					this.p.discardPile.removeCard(c);
					this.p.hand.addToTop(c);
				}
				this.p.hand.refreshHandLayout();
				this.p.hand.applyPowers();
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			this.p.hand.refreshHandLayout();
		}
		tickDuration();
	}
}
