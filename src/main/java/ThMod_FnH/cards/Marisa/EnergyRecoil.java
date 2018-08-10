package ThMod_FnH.cards.Marisa;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomCard;
import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.ChargeUpPower;

public class EnergyRecoil extends CustomCard {
	public static final String ID = "EnergyRecoil";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 7;
	private static final int UPG_PLUS_BLC = 2;
	private static final int BLC_CHRG = 4;
	private static final int UPG_PULS_CHRG = 1;

	public EnergyRecoil() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK_AMT;
		this.baseDamage = BLC_CHRG;
	}

	@Override
	public void applyPowers() {
		this.isBlockModified = false;
	    float tmp = this.baseBlock;
	    float amp = this.baseDamage;
	    for (AbstractPower p : AbstractDungeon.player.powers){
	    	tmp = p.modifyBlock(tmp);
	    	amp = p.modifyBlock(amp);
	    	if (this.baseBlock != MathUtils.floor(tmp)) {
	    		this.isBlockModified = true;
	    	}
	    	if (this.baseDamage != MathUtils.floor(amp)) {
	    		this.isDamageModified = true;
	    	}
	    }
	    if (tmp < 0.0F) {
	    	tmp = 0.0F;
	    }
	    if (amp < 0.0F) {
	    	amp = 0.0F;
	    }
	    this.block = MathUtils.floor(tmp);
	    this.damage = MathUtils.floor(amp);
	}

	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		calculateCardDamage(mo);
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		this.applyPowers();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		if (p.hasPower("ChargeUpPower")) {
			if (p.getPower("ChargeUpPower").amount >= 4){
				int cnt = p.getPower("ChargeUpPower").amount/4;
					AbstractDungeon.actionManager.addToBottom(
							new GainBlockAction(p ,p , cnt*this.damage));
					AbstractDungeon.actionManager.addToBottom(
							new ApplyPowerAction(p,p,new ChargeUpPower(p,-cnt*4),-cnt*4));
			}
		}
			
	}

	public AbstractCard makeCopy() {
		return new EnergyRecoil();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPG_PLUS_BLC);
			upgradeDamage(UPG_PULS_CHRG);
		}
	}
}