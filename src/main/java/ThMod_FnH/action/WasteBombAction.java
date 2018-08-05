package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.powers.Marisa.TempStrengthLoss;

public class WasteBombAction
	extends AbstractGameAction{
	private int damage;
  
	public WasteBombAction(int dmg){
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = Settings.ACTION_DUR_FAST;
		this.damage = dmg;
	}
  
	public void update(){
		this.isDone = false;

		AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(true);
		
		AbstractDungeon.actionManager.addToBottom(new DamageAction(mon,
				new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL),
					AbstractGameAction.AttackEffect.SMASH));
		
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(
							mon,
							AbstractDungeon.player, 
							new TempStrengthLoss(mon, 3),
							3
							)
					);
	    
		this.isDone = true;
	}
}