package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class SporeBomb extends CustomCard {
	public static final String ID = "SporeBomb";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int AMP = 1;
	private static final int STC = 2;
	private static final int UPG_STC = 1;
	private static final int AMP_STC = 1;
	private static final int UPG_AMP = 1;
	
	public SporeBomb() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = STC;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int stack = this.magicNumber;
		if (ThMod.Amplified(this.costForTurn+AMP, AMP)) {
			if (this.upgraded)
				stack += AMP_STC;
			else
				stack += UPG_AMP;
		}
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(mo, p, new VulnerablePower(mo, stack, false), stack, true, AttackEffect.NONE));
	}

	public AbstractCard makeCopy() {
		return new SporeBomb();
	}
	
	@Override
	public boolean isDefend(){
		return true;
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