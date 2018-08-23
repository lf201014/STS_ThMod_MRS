package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.powers.Marisa.TempStrengthLoss;

public class WasteBombAction
	extends AbstractGameAction{
	private int damage;
	private int num;
	AbstractCreature target;
  
	public WasteBombAction(AbstractCreature target,int dmg,int numTimes){
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = Settings.ACTION_DUR_FAST;
		this.damage = dmg;
		this.target = target;
		this.num = numTimes;
	}
  
	public void update(){
		if (target == null) {
			this.isDone = true;
			return;
		}
		
		if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
			AbstractDungeon.actionManager.clearPostCombatActions();
			this.isDone = true;
			return;
		}
		
		if (target.currentHealth > 0) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,
					new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL),
					AbstractGameAction.AttackEffect.SMASH));
			
			if ((!this.target.isDeadOrEscaped())&&(!this.target.isDying)) {
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(
								this.target,
								AbstractDungeon.player, 
								new TempStrengthLoss(this.target, 3),
								3
								)
						);
			}
		
			if ((this.num > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
				this.num --;
				AbstractDungeon.actionManager.addToTop(
						new WasteBombAction(
								AbstractDungeon.getMonsters().getRandomMonster(true),
								this.damage, 
								this.num
								)
						);
			}
		}
		
		AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
		
		this.isDone = true;
	}
}