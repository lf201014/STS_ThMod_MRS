package ThMod_FnH.action;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class UnstableBombAction
	extends AbstractGameAction{
	private int max;
	private int min;
  
	public UnstableBombAction(int dmg_min,int dmg_max){
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = Settings.ACTION_DUR_FAST;
		this.min = dmg_min;
		this.max = dmg_max;
	}
  
	public void update(){
		this.isDone = false;
		
		int dmg = MathUtils.random(min, max);
		ThMod.logger.info("UnstableBombAction : Random damage :"+dmg);
		AbstractDungeon.actionManager.addToBottom(
				new DamageRandomEnemyAction(
						new DamageInfo(AbstractDungeon.player, dmg, DamageType.NORMAL),
						AbstractGameAction.AttackEffect.FIRE)
				);
	    
		this.isDone = true;
	}
}