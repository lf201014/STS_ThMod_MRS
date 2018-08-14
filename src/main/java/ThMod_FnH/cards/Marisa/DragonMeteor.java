package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class DragonMeteor 
	extends CustomCard {

	public static final String ID = "DragonMeteor";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_PLUS_DMG = 1;

	public DragonMeteor() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.magicNumber = this.baseMagicNumber = ATTACK_DMG;
		this.damage = this.baseDamage = 0;
	}
	
	@Override
	public void applyPowers() {
		this.baseDamage = this.magicNumber * AbstractDungeon.player.exhaustPile.size();
		super.applyPowers();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		/*
		int cnt = p.exhaustPile.size();
		for (int i = 0; i<cnt ; i++)
			AbstractDungeon.actionManager.addToBottom(
					new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
					*/
		
		if (m != null) {
		      AbstractDungeon.actionManager.addToBottom(
		    		  new VFXAction(
		    				  new WeightyImpactEffect(m.hb.cX, m.hb.cY)
		    				  )
		    		  );
		    }
		
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
		    
		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(m,
	    					new DamageInfo(
	    							p,
	    							this.damage,
	    							this.damageTypeForTurn),
	    					AbstractGameAction.AttackEffect.SLASH_DIAGONAL
	    					)
				);
	}

	public AbstractCard makeCopy() {
		return new DragonMeteor();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_DMG);
		}
	}
}