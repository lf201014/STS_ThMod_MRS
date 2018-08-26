package ThMod_FnH.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod_FnH.ThMod;
import ThMod_FnH.action.ConsumeChargeUpAction;

public class ChargeUpPower
	extends AbstractPower{
	public static final String POWER_ID = "ChargeUpPower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	private int cnt;
	private int stc;
	private int ACT_STACK = 8;
	private int IMPR_STACK = 6;
  
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
		//ThMod.logger.info("ChargeUpPower : StackPower");
		//ThMod.logger.info("ChargeUpPower : adding");
		
	    this.fontScale = 8.0F;
	    this.amount += stackAmount;
		if (this.amount <= 0){
			this.amount = 0;
			return;
	    }
		//ThMod.logger.info("ChargeUpPower : checking counter");
		if (AbstractDungeon.player.hasRelic("SimpleLauncher"))
			this.stc = IMPR_STACK;
		else
			this.stc = ACT_STACK;
		
		ThMod.logger.info(
				"ChargeUpPower : Checking stack divider :"+this.stc
				+" ; Checking stack number :"+this.amount
				);
		
		this.cnt = (int) Math.floor(this.amount/this.stc);
		//ThMod.logger.info("ChargeUpPower : Done StackPower ; cnt : "+this.cnt);
	}
	
	public void updateDescription(){
		if (this.cnt>0)
			this.description = 
					(
							DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]
									+","+DESCRIPTIONS[2]+(int)Math.pow(2,this.cnt)+DESCRIPTIONS[3]
									);
		else	
			this.description = (DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+".");
	}
	
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m){
		if (this.owner.hasPower("MoraleDepletionPlusPower")) {
			return;
		}
		if ((this.cnt>0)&&(card.type == CardType.ATTACK)) {
			ThMod.logger.info("ChargeUpPower : consuming stacks for :"+card.cardID);
			
			if ( owner.hasPower("OrrerysSunPower") )
				owner.getPower("OrrerysSunPower").onSpecificTrigger();
			flash();
			
			if (AbstractDungeon.player.hasRelic("SimpleLauncher"))
				this.stc = IMPR_STACK;
			else
				this.stc = ACT_STACK;
			ThMod.logger.info("ChargeUpPower : Checking stack number :"+this.stc);
			
			//this.stackPower(-cnt*this.stc);
			AbstractDungeon.actionManager.addToBottom(
					new ConsumeChargeUpAction(cnt*this.stc)
					);
		}
	}
	
	
	@Override
	public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
		if (this.owner.hasPower("MoraleDepletionPlusPower")) {
			return damage;
		}
		if (cnt>0)
			if ((type == DamageType.NORMAL)&&(this.amount >= 1)) {
				return (float) (damage * Math.pow(2, this.cnt));
			}
		return damage;
	}
}