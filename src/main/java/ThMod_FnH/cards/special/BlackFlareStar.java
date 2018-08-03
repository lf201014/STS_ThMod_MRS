package ThMod_FnH.cards.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.action.BlackFlareStarAction;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class BlackFlareStar extends CustomCard {
	public static final String ID = "BlackFlareStar";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/pride.png";
	private static final int COST = 0;
	private static final int BLC_AMT = 5;
	private static final int UPG_BLC = 7;

	public BlackFlareStar() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLC_AMT;
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m){
		if (p.hand.size() >= 3)
			return true;
		return false;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(
				new BlackFlareStarAction(this.upgraded)
				);
	}

	public AbstractCard makeCopy() {
		return new BlackFlareStar();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBlock(UPG_BLC);
		}
	}
}