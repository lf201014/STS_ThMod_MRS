package ThMod_FnH.powers.Marisa;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import ThMod_FnH.ThMod;
import ThMod_FnH.cards.special.Burn_MRS;

public class SuperNovaPower extends AbstractPower{
	public static final String POWER_ID = "SuperNovaPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
	public SuperNovaPower(AbstractCreature owner){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = -1;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/impulse.png");
	
	}

	@Override
	public void stackPower(int stackAmount) {
		 
	}
	
	public void atEndOfTurn(boolean isPlayer) {
		AbstractPlayer p = AbstractDungeon.player;
		if (!p.hand.isEmpty()) {
			flash();
			int cnt = 0;
			for (AbstractCard c:p.hand.group) {
				if ((c instanceof Burn)||(c instanceof Burn_MRS))
					cnt++;
				AbstractDungeon.actionManager.addToBottom(
						new ExhaustSpecificCardAction(c, p.hand));
			}
			if (cnt>0)
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(p, p, new StrengthPower(p, cnt), cnt));
		}
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type != CardType.ATTACK) {
			ThMod.logger.info("SuperNovaPower : Adding Burn for Using card : "+card.cardID);
			AbstractDungeon.actionManager.addToBottom(
					new MakeTempCardInHandAction(new Burn_MRS(),1));
		}
	}
		
	@Override
	public void onDrawOrDiscard() {
		ThMod.logger.info("SuperNovaPower : onDrawOrDiscard : replaceBurn");
		replaceBurn();
	}
	
	@Override
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		ThMod.logger.info("SuperNovaPower : onApplyPower : replaceBurn");
		replaceBurn();
	}
	@Override
	public void onInitialApplication() {
		ThMod.logger.info("SuperNovaPower : onApplyPower : replaceBurn");
		replaceBurn();
	}
 
	public void updateDescription(){
		this.description = (DESCRIPTIONS[0]);
 	}
	
	private void replaceBurn() {
		ArrayList<AbstractCard> temp = new ArrayList<AbstractCard>();
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c instanceof Burn) {
				temp.add(c);
			}
		}
		while (temp.size() > 0)
		{
			AbstractCard c = temp.remove(0);
			AbstractDungeon.player.hand.removeCard(c);
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Burn_MRS(), 1));
		}
	}
}