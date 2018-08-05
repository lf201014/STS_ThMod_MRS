package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomCard;

public class UltraShortWave extends CustomCard {
	public static final String ID = "UltraShortWave";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UPG_CHG = 2;
	protected static int ene_gain;
	protected static int chrg_gain;
	


	public UltraShortWave() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		if (ene_gain <= 0)
			ene_gain = 1;
		if (chrg_gain <= 0)
			chrg_gain = 1;
	}
	
	@Override
	public void applyPowers(){
		this.magicNumber = UltraShortWave.ene_gain;
		this.block = UltraShortWave.chrg_gain;
		if (this.upgraded)
			this.block += UltraShortWave.UPG_CHG;
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo){
		this.magicNumber = UltraShortWave.ene_gain;
		this.block = UltraShortWave.chrg_gain;
		if (this.upgraded)
			this.block += UltraShortWave.UPG_CHG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ChargeUpPower(p,this.magicNumber+1),this.magicNumber+1));
		UltraShortWave.ene_gain++;
		UltraShortWave.chrg_gain++;
		this.applyPowers();
	}

	public AbstractCard makeCopy() {
		return new UltraShortWave();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.applyPowers();
		}
	}
}