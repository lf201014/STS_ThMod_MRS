package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import ThMod_FnH.powers.Marisa.TempStrengthLoss;

public class DeepEcologicalBomb 
	extends CustomCard {
	
	public static final String ID = "DeepEcologicalBomb";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 1;
	private static final int ATK_DMG = 7;
	private static final int UPG_DMG = 3;
	private static final int AMP = 1;
	

	public DeepEcologicalBomb() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY);

		this.baseDamage = ATK_DMG;
		
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(true);
		if (mon != null) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(mon,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, p, new TempStrengthLoss(mon, 3), 3));
		}
		
		
	    if ( ThMod.Amplified(AMP+this.costForTurn,AMP) ) {
	    	mon = AbstractDungeon.getMonsters().getRandomMonster(true);
	    	if (mon != null) {
	    		AbstractDungeon.actionManager.addToBottom(new DamageAction(mon,
	    			new DamageInfo(p, this.damage, this.damageTypeForTurn),
	    			AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, p, new TempStrengthLoss(mon, 3), 3));
	    	}
	    	
	    }
	}

	public AbstractCard makeCopy() {
		return new DeepEcologicalBomb();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
		}
	}
}