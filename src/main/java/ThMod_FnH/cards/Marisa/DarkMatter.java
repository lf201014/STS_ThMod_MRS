package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class DarkMatter extends CustomCard {
	public static final String ID = "DarkMatter";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final int COST = 0;
	private static final int BLC_GAIN = 4;
	private static final int UPG_BLC = 2;

	public DarkMatter() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.isEthereal =true;
		this.block = this.baseBlock = BLC_GAIN;
	}
	
	public void triggerOnExhaust() {
		AbstractPlayer p = AbstractDungeon.player;
		AbstractDungeon.actionManager.addToBottom(
				new GainBlockAction(p, p, this.block));
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(
				new MakeTempCardInDrawPileAction(
						this.makeStatEquivalentCopy(),
						1,
						true,
						true)
				);
		AbstractDungeon.actionManager.addToBottom(
				new MakeTempCardInDrawPileAction(
						this.makeStatEquivalentCopy(),
						1,
						true,
						true)
				);
		/*
		AbstractCard c = new DarkMatter();
		if (this.upgraded)
			c.upgrade();
		AbstractDungeon.effectList.add(
        		new ShowCardAndAddToDrawPileEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, true)
        		);
		
		c = new DarkMatter();
		if (this.upgraded)
			c.upgrade();
		AbstractDungeon.effectList.add(
        		new ShowCardAndAddToDrawPileEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, true)
        		);
        		*/
		/*
		p.drawPile.shuffle();
	    for (AbstractRelic r : p.relics)
	        r.onShuffle();  
	        */
	}

	public AbstractCard makeCopy() {
		return new DarkMatter();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPG_BLC);
			this.rawDescription = DESCRIPTION_UPG;
			initializeDescription();
		}
	}
}