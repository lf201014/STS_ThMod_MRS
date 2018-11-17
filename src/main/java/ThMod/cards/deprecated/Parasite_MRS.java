package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomCard;

@Deprecated
public class Parasite_MRS extends CustomCard {
	public static final String ID = "Parasite_MRS";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Parasite");
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;
	private static final int HEAL = 3;
  
	public Parasite_MRS(){
		super(
				ID, 
				NAME, 
				"img/cards/parasite.png",
				COST,
				DESCRIPTION, 
				AbstractCard.CardType.CURSE, 
				AbstractCard.CardColor.CURSE, 
				AbstractCard.CardRarity.CURSE,
				AbstractCard.CardTarget.SELF
				);
		this.exhaust = true;
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		return true;
	}

	public void use(AbstractPlayer p, AbstractMonster m){
		if (p.hasRelic("ShroomBag")) {
			AbstractRelic r = p.getRelic("ShroomBag");
			r.flash();
			AbstractDungeon.actionManager.addToBottom(
  	      		  new RelicAboveCreatureAction(AbstractDungeon.player,r)
  	      		  );
		}
		AbstractDungeon.actionManager.addToBottom(
				new HealAction(p, p, HEAL)
				);
	}

	public AbstractCard makeCopy(){
		return new Parasite_MRS();
	}

	public void upgrade() {}
}
