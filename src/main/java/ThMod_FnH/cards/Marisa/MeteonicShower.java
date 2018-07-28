package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ThMod_FnH.ThMod;
import ThMod_FnH.abstracts.AmplifiedAttack;
import ThMod_FnH.patches.AbstractCardEnum;

public class MeteonicShower 
	extends AmplifiedAttack {
	
	public static final String ID = "MeteonicShower";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 1;
	private static final int AMP = 1;
	private static final int CNT = 4;
	private static final int UPG_CNT = 2;
	

	public MeteonicShower() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = 3;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.ampNumber = 0;
		this.block = this.baseBlock = 0;
	}
	
	@Override
	public void applyPowers(){
		AbstractPlayer player = AbstractDungeon.player;

		this.baseBlock = (AbstractDungeon.player.hand.size()-1)*2;
		for (AbstractCard i:player.hand.group)
			if (i.cardID == "Burn")
				this.baseBlock++;
		super.applyPowers();
	}
	
	@Override
	public void calculateDamageDisplay(AbstractMonster mo){
		calculateCardDamage(mo);
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo){
		AbstractPlayer player = AbstractDungeon.player;

		this.baseBlock = (AbstractDungeon.player.hand.size()-1)*2;
		for (AbstractCard i:player.hand.group)
			if (i.cardID == "Burn")
				this.baseBlock++;
		super.calculateCardDamage(mo);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if ( ThMod.Amplified(AMP+this.costForTurn,AMP) ) {
			
			
			for (int i=0;i<this.magicNumber;i++) 
				AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
						new DamageInfo(p, this.block, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			
			for (AbstractCard i:p.hand.group)
				AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(i, p.hand, true));
			
		}else		
			for (int i=0;i<this.magicNumber;i++) 
				AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
						new DamageInfo(p, this.damage, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}

	public AbstractCard makeCopy() {
		return new MeteonicShower();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPG_CNT);
		}
	}
}