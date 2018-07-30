package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class SuperPerseids extends CustomCard {
	public static final String ID = "SuperPerseids";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;
	private static final int DMG = 12;
	private static final int UPG_DMG = 4;
	
	public SuperPerseids() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

		this.baseDamage = DMG;
	}
	
	public void triggerOnExhaust() {
		
		ThMod.logger.info("SuperPerseids : on exhaust");
		
		this.triggerOnManualDiscard();
		
		ThMod.logger.info("SuperPerseids : onExhaust finished");
	}
	  
	public void use(AbstractPlayer p, AbstractMonster m) {}
	  
	public boolean canUse(AbstractPlayer p, AbstractMonster m){
		this.cantUseMessage = "I can't use this card.";
		return false;
	}
	
	public void triggerOnManualDiscard() {
		ThMod.logger.info("SuperPerseids : on exhaust");
		
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, 
			      DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));

		ThMod.logger.info("SuperPerseids : on exhaust");
	}

	public AbstractCard makeCopy() {
		return new SuperPerseids();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
		}
	}
}