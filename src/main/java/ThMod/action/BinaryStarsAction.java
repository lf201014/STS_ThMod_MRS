package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod.cards.derivations.BlackFlareStar;
import ThMod.cards.derivations.WhiteDwarf;

public class BinaryStarsAction extends AbstractGameAction{
	private AbstractPlayer p;
	private boolean upg;
  
	public BinaryStarsAction(boolean upgraded){
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, 1);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
		this.upg = upgraded;
	}
  
	public void update(){
		CardGroup tmp;
		if (this.duration == Settings.ACTION_DUR_MED){
			tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			AbstractCard c = new BlackFlareStar();
			if (this.upg)
				c.upgrade();
			tmp.addToTop(c);
			c = new WhiteDwarf();
			if (this.upg)
				c.upgrade();
			tmp.addToTop(c);
			AbstractDungeon.gridSelectScreen.open(tmp, 1, "Choose", false);
			tickDuration();
			return;
		}
		if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards){
				c.unhover();
				if (this.p.hand.size() == 10){
					this.p.createHandIsFullDialog();
					this.p.discardPile.addToTop(c);
				}
				else{
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
