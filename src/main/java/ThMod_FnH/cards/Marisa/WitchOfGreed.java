package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.WitchOfGreedGold;
import ThMod_FnH.powers.Marisa.WitchOfGreedPotion;

public class WitchOfGreed extends CustomCard {
	public static final String ID = "WitchOfGreed";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int STC = 20;
	private static final int UPG_STC = 10;
	
	private static final int AMP = 1;
	
	public WitchOfGreed() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

		this.baseMagicNumber = this.magicNumber = STC;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if (ThMod.Amplified(this.costForTurn+AMP, AMP)) {
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(p, p, new WitchOfGreedPotion(p, 1), 1));
		}
		
		ThMod.logger.info("WitchOfGreed : Applying power : gold ;");
		
		AbstractDungeon.actionManager.addToBottom(
				new ApplyPowerAction(p, p, new WitchOfGreedGold(p, this.magicNumber), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new WitchOfGreed();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPG_STC);
			this.rawDescription = DESCRIPTION_UPG;
			initializeDescription();
		}
	}
}