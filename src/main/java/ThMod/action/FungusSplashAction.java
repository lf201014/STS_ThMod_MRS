package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FearPotion;
import com.megacrit.cardcrawl.potions.FirePotion;
import com.megacrit.cardcrawl.potions.PoisonPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.potions.WeakenPotion;

public class FungusSplashAction extends AbstractGameAction{
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	public static final String[] TEXT = uiStrings.TEXT;
	private AbstractPlayer p;
	public static int numExhausted;
  
	public FungusSplashAction(AbstractCreature Target) {
		this.amount = 1;
		this.p = AbstractDungeon.player;
		setValues(Target, p, amount);
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.EXHAUST;
	}
  
	public void update() {
		if (this.duration == Settings.ACTION_DUR_FAST) {
			if (this.p.hand.size() == 0) {
				this.isDone = true;
				return;
			}
			if (this.p.hand.size() == 1) {
				AbstractCard c = this.p.hand.getTopCard();
				usePotion(c.type);
				this.p.hand.moveToExhaustPile(c);
				return;
			}
			AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false);
			tickDuration();
			return;
		}
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				usePotion(c.type);
				this.p.hand.moveToExhaustPile(c);
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
	
	public void usePotion(CardType type) {
		AbstractPotion p;
		switch (type) {
		case ATTACK:
			p = new FearPotion();
			p.use(target);
			break;
		case SKILL:
			p = new WeakenPotion();
			p.use(target);
			break;
		case POWER:
			p = new PoisonPotion();
			p.use(target);
			break;
		case STATUS:
			p = new FirePotion();
			p.use(target);
			break;
		case CURSE:
			p = new SmokeBomb();
			if (p.canUse())
				p.use(this.p);
			break;
		default:
			break;
		}
	}
}
