package ThMod_FnH.cards.Marisa;

//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class OpenUniverse extends CustomCard {
	public static final String ID = "OpenUniverse";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	static final int AMP = 1;
	


	public OpenUniverse() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.exhaust =true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m){
		boolean amp = ThMod.Amplified(this.costForTurn+AMP , AMP);

		ThMod.logger.info("OpenUniverse : generationg cards");
		
	    for (int i = 0; i < 5; i++){
	        AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeStatEquivalentCopy();
	        if (this.upgraded)
	        	card.upgrade();
	        if (amp) {
		    	card.freeToPlayOnce = true;
		    	card.applyPowers();
		    }
			ThMod.logger.info("OpenUniverse : adding : "+card.cardID);
			
	        AbstractDungeon.effectList.add(
	        		new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, true)
	        		);
	    }
	    
		ThMod.logger.info("OpenUniverse : shuffling");
	    
	    p.drawPile.shuffle();
	    for (AbstractRelic r : p.relics)
	    	r.onShuffle();
	    
	     
		ThMod.logger.info("OpenUniverse : done");
	}

	public AbstractCard makeCopy() {
		return new OpenUniverse();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;;
			initializeDescription();
		}
	}
	
}
