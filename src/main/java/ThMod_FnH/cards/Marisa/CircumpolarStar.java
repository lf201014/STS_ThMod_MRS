package ThMod_FnH.cards.Marisa;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class CircumpolarStar extends CustomCard {
	public static final String ID = "CircumpolarStar";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 6;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int AMP_BLC = 6;
	private static final int UPG_AMP = 1;
	private static final int AMP = 1;
	private static final int DRAW = 1;
	
	
	public CircumpolarStar(){
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK_AMT;
		this.magicNumber = this.baseMagicNumber = AMP_BLC;
		this.damage = this.baseDamage = DRAW;
	}
	
	@Override
	public void applyPowers(){
		this.isBlockModified = false;
		float tmp = this.baseBlock;
		float amp = this.baseMagicNumber;
		for (AbstractPower p : AbstractDungeon.player.powers){
			tmp = p.modifyBlock(tmp);
			amp = p.modifyBlock(amp);
			if (this.baseBlock != MathUtils.floor(tmp)) {
				this.isBlockModified = true;
			}
			if (this.baseMagicNumber != MathUtils.floor(amp)) {
				this.isMagicNumberModified = true;
			}
		}
		if (tmp < 0.0F) {
	    	tmp = 0.0F;
		}
		this.block = MathUtils.floor(tmp);
		this.magicNumber = MathUtils.floor(amp);
	}
	
	@Override	
	public void calculateDamageDisplay(AbstractMonster mo){}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo){}
	
	
	public void use(AbstractPlayer p, AbstractMonster m){
		int blc = this.block;
		
		if (ThMod.Amplified(this.costForTurn+AMP, AMP))
			blc += this.magicNumber;
		
		AbstractDungeon.actionManager.addToBottom(
				new GainBlockAction(p, p, blc));
		
		AbstractDungeon.actionManager.addToBottom(
				new DrawCardAction(p,this.damage));
		
		this.baseDamage++;
		this.damage++;
		this.isDamageModified = true;
	}

	public AbstractCard makeCopy(){
		return new CircumpolarStar();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPG_AMP);
		}
	}
}