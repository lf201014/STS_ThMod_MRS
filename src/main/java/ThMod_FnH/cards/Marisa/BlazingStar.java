package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import ThMod_FnH.ThMod;
import ThMod_FnH.abstracts.AmplifiedAttack;
import ThMod_FnH.patches.AbstractCardEnum;

public class BlazingStar 
	extends AmplifiedAttack {
	
	public static final String ID = "BlazingStar";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 2;
	private static final int ATK_DMG = 15;
	private static final int UPG_DMG = 5;
	private static final int AMP_DMG = 6;
	private static final int UPG_AMP = 2;
	private static final int AMP = 1;
	private static final int PW_APL = 1;
	
	public BlazingStar() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = ATK_DMG;
		this.ampNumber = AMP_DMG;
		this.baseBlock = this.baseDamage + this.ampNumber;
		this.magicNumber = this.baseMagicNumber = PW_APL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int tmp = this.magicNumber;
		if (ThMod.Amplified(AMP+this.costForTurn,AMP)) {
			tmp++;
			AbstractDungeon.actionManager.addToBottom(
					new DamageAction(
							m,
							new DamageInfo(p, this.block, this.damageTypeForTurn),
							AbstractGameAction.AttackEffect.SLASH_DIAGONAL
							)
					);
	    	AbstractDungeon.actionManager.addToBottom(
	    			new MakeTempCardInHandAction(new Burn(), 1)
	    			);
		} else {
			AbstractDungeon.actionManager.addToBottom(
					new DamageAction(
							m,
							new DamageInfo(p, this.damage, this.damageTypeForTurn),
							AbstractGameAction.AttackEffect.SLASH_DIAGONAL
							)
					);
		}
	    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), 1));
	    AbstractDungeon.actionManager.addToBottom(
	    			new ApplyPowerAction(m, p, 
	    					new VulnerablePower(m, tmp+1, false), tmp+1, true)
	    			);	
	    	AbstractDungeon.actionManager.addToBottom(
	    	    	new ApplyPowerAction(m, p, 
	    	    			new WeakPower(m, tmp, false), tmp, true)
	    	    	);
	}

	public AbstractCard makeCopy() {
		return new BlazingStar();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
			this.ampNumber += UPG_AMP;
			this.block = this.baseDamage + this.ampNumber;
			this.isBlockModified = true;
		}
	}
}