package ThMod_FnH.cards.special;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class Burn_MK extends CustomCard {
	public static final String ID = "Burn_MK";
	public static final String NAME = "Burn";
	public static final String DESCRIPTION = "No effect.";
	private static final int COST = -2;

	public Burn_MK() {
		super(ID, NAME, "img/cards/burn.png", COST, DESCRIPTION,
				AbstractCard.CardType.STATUS,  AbstractCard.CardColor.COLORLESS,
				CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);

		this.damage = 5;
		this.magicNumber = 2;
	}

	
	@Override
	public void update() {
		//updateEnigmaValue();
		super.update();
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m){
		if (p != null) {
			if ((!this.dontTriggerOnUseCard) && (p.hasRelic("Medical Kit"))){
				useMedicalKit(p);
			}
		else{
			 AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
		}
		}			
			//AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Burn_MK();
	}

	@Override
	public void upgrade() {
	      upgradeName();
	      upgradeDamage(3);
		return;
	}
}


