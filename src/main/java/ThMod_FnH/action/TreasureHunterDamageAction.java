package ThMod_FnH.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import ThMod_FnH.ThMod;

public class TreasureHunterDamageAction
	extends AbstractGameAction{
	private RelicTier tier;
	private DamageInfo info;
	private static final float DURATION = 0.1F;
  
	public TreasureHunterDamageAction(AbstractCreature target, DamageInfo info){
		this.info = info;
		setValues(target, info);
		this.tier = RelicTier.RARE;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = DURATION;
	}
  
	public void update(){
		if ((this.duration == 0.1F) && (this.target != null)){
			AbstractDungeon.effectList.add(
					new FlashAtkImgEffect(
							this.target.hb.cX, this.target.hb.cY,
							AbstractGameAction.AttackEffect.BLUNT_HEAVY
							)
					);
			
			ThMod.logger.info(
					"TreasureHunterDamageAction : target : "+this.target.id
					+" ; damage : "+this.info.base
					); 
			
			this.target.damage(this.info);
			AbstractMonster mon = (AbstractMonster) this.target;
			
			ThMod.logger.info("TreasureHunterDamageAction : Checking : type :"+mon.type.toString()); 
			
			if (mon.type == AbstractMonster.EnemyType.BOSS)
				this.tier = RelicTier.BOSS;
			
			ThMod.logger.info("TreasureHunterDamageAction : Checking : Tier :"+mon.type.toString()); 
			
			if ((mon.type != AbstractMonster.EnemyType.NORMAL)
					||(mon.id == "Orb Walker")) {
				
				ThMod.logger.info("TreasureHunterDamageAction : Checking : isDying :"+mon.isDying+" ; Current hp : "+mon.currentHealth); 
				
				if (((((AbstractMonster)this.target).isDying)
						|| (this.target.currentHealth <= 0)) && (!this.target.halfDead)){
					AbstractRelic r = AbstractDungeon.returnRandomRelic(this.tier);
					ThMod.logger.info("TreasureHunterDamageAction : Granting relic :"+r.relicId);
					AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
							Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r
							);
					r.flash();
				}
			}
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
