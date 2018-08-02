package ThMod_FnH.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod_FnH.ThMod;

import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChargeUpPower
	extends AbstractPower{
	public static final String POWER_ID = "ChargeUpPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int cnt;
  
	public ChargeUpPower(AbstractCreature owner, int amount){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/generator.png");
		this.cnt = (int) Math.floor(this.amount/6);
	}
	
	@Override
	public void stackPower(int stackAmount){

		ThMod.logger.info("ChargeUpPower : StackPower");

		ThMod.logger.info("ChargeUpPower : adding");

	    this.fontScale = 8.0F;
	    this.amount += stackAmount;
		if (this.amount <= 0){
			this.amount = 0;
			return;
	    }
		
		ThMod.logger.info("ChargeUpPower : checking counter");

		this.cnt = (int) Math.floor(this.amount/6);
		
		ThMod.logger.info("ChargeUpPower : Done StackPower ; cnt : "+this.cnt);
	}
	
	public void updateDescription(){
		if (this.cnt>0)
			this.description = 
					(DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+","+DESCRIPTIONS[2]+(int)Math.pow(2,this.cnt)+DESCRIPTIONS[3]);
		else	
			this.description = (DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+".");
	}
	
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m){
		if ((this.cnt>0)&&(card.type == CardType.ATTACK)) {
			ThMod.logger.info("ChargeUpPower : using stacks for :"+card.cardID);
			if ( owner.hasPower("OrrerysSunPower") )
				owner.getPower("OrrerysSunPower").onSpecificTrigger();
			flash();
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(owner,owner,
							new ChargeUpPower(owner,-cnt*6),-cnt*6)
					);
		}
	}
	
	
	@Override
	public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
		if (cnt>0)
			if ((type== DamageType.NORMAL)&&(this.amount >= 1)) {
				return (float) (damage * Math.pow(2, this.cnt));
			}
		return damage;
	}
}