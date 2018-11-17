package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import ThMod.ThMod;

public class RefractionSparkAction
	extends AbstractGameAction{
	private DamageInfo info;
	private static final float DURATION = 0.1F;
  
	public RefractionSparkAction(AbstractCreature target,DamageInfo info ){
		this.info = info;
		setValues(target, info);
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = DURATION;
	}
  
	public void update(){
		if ((this.duration == 0.1F) && (this.target != null)){
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			
			AbstractMonster mon = (AbstractMonster) this.target;
			
			ThMod.logger.info("RefractionSparkAction : calculating damage : "+this.info.base);
			
	        float tmp = this.info.base;
	        if (mon.currentBlock > 0)
	        	tmp -= mon.currentBlock;
	        if (tmp > mon.currentHealth) {
	        	tmp = mon.currentHealth;
	        }
	        if (tmp<0)
	        	tmp = 0;
	        
	        if (tmp > 0) {
	        	ThMod.logger.info("RefractionSparkAction : increasing damage : "+tmp);
	        
	        	for (AbstractCard c:AbstractDungeon.player.hand.group)
	        		if ((ThMod.isSpark(c))) {
	        			ThMod.logger.info("RefractionSparkAction : increasing damage for : "+c.cardID);
	        			c.baseDamage += tmp;
	        			c.flash();
	        			c.applyPowers();
	        		}
	        }
	        
	        ThMod.logger.info("RefractionSparkAction : dealing damage : "+tmp);
	        
			this.target.damage(this.info);
			
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
