package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
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
	private static final int ATK_DMG = 14;
	private static final int UPG_DMG = 6;

	public DragonMeteor() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.damage = this.baseDamage = ATK_DMG;
	}
	
	@Override
	public void applyPowers() {
		int dmg = ATK_DMG;
		if (this.upgraded) {
			dmg += UPG_DMG;
		}
		this.baseDamage = dmg + AbstractDungeon.player.exhaustPile.size();
		super.applyPowers();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		
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
	    					AttackEffect.FIRE
	    					)
				);
	}

	public AbstractCard makeCopy() {
		return new DragonMeteor();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
		}
	}
}