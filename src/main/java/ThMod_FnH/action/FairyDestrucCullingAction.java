package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.PowerExpireTextEffect;

public class FairyDestrucCullingAction extends AbstractGameAction{
	private int threshold;
	
	public FairyDestrucCullingAction(int threshold) {
		this.threshold = threshold;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.isDone = false;
		if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
			this.isDone = true;
			return;
		}
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (m.currentHealth <= this.threshold) {
				if (m.hasPower("Intangible")) {
					AbstractPower removeMe = this.target.getPower("Intangible");
					AbstractDungeon.effectList.add(
							new PowerExpireTextEffect(
									this.target.hb.cX - this.target.animX,
									this.target.hb.cY + this.target.hb.height / 2.0F, 
									removeMe.name,
									removeMe.region128
									)
							);
			        removeMe.onRemove();
			        this.target.powers.remove(removeMe);
			        AbstractDungeon.onModifyPower();
				}
				if (m.hasPower("IntangiblePlayer")) {
					AbstractPower removeMe = this.target.getPower("IntangiblePlayer");
					AbstractDungeon.effectList.add(
							new PowerExpireTextEffect(
									this.target.hb.cX - this.target.animX,
									this.target.hb.cY + this.target.hb.height / 2.0F, 
									removeMe.name,
									removeMe.region128
									)
							);
			        removeMe.onRemove();
			        this.target.powers.remove(removeMe);
			        AbstractDungeon.onModifyPower();
				}
				m.damage(
						new DamageInfo(
								AbstractDungeon.player,
								Integer.MAX_VALUE,
								DamageType.HP_LOSS
								)
						);
			}
		}
		if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
			AbstractDungeon.actionManager.clearPostCombatActions();
		}
		this.isDone = true;
	}

}
