package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class _6AAction
	extends AbstractGameAction{
	private DamageInfo info;
	private static final float DURATION = 0.1F;
  
	public _6AAction(AbstractCreature target,DamageInfo info ){
		this.info = info;
		setValues(target, info);
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = DURATION;
	}
  
	public void update(){
		if ((this.duration == 0.1F) && (this.target != null)){
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			
			AbstractMonster mon = (AbstractMonster) this.target;
			
	        int tmp = this.info.base;
	        tmp -= mon.currentBlock;
	        if (tmp > mon.currentHealth) {
	        	tmp = mon.currentHealth;
	        }
	        
	        AbstractDungeon.player.addBlock(tmp);
	        
			this.target.damage(this.info);
			
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
