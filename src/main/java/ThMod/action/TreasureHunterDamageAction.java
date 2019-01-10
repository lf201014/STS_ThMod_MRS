package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import ThMod.ThMod;

public class TreasureHunterDamageAction extends AbstractGameAction {

  private RelicTier tier;
  private DamageInfo info;
  private static final float DURATION = 0.1F;
  private boolean reward = false;

  public TreasureHunterDamageAction(AbstractCreature target, DamageInfo info) {
    this.info = info;
    setValues(target, info);
    this.tier = RelicTier.RARE;
    this.actionType = AbstractGameAction.ActionType.DAMAGE;
    this.duration = DURATION;
  }

  public void update() {
    if ((this.duration == 0.1F) && (this.target != null)) {
      AbstractDungeon.effectList.add(
          new FlashAtkImgEffect(
              this.target.hb.cX, this.target.hb.cY,
              AbstractGameAction.AttackEffect.BLUNT_HEAVY
          )
      );

      ThMod.logger.info(
          "TreasureHunterDamageAction : target : " +
              this.target.id +
              " ; damage : " +
              this.info.base
      );

      this.target.damage(this.info);
      AbstractMonster mon = (AbstractMonster) this.target;

      AbstractRoom curRoom = AbstractDungeon.getCurrRoom();

      ThMod.logger.info(
          "TreasureHunterDamageAction : Checking : MonsterRoomElite :" +
              (curRoom.eliteTrigger) +
              " ; MonsterRoomBoss :" +
              (curRoom instanceof MonsterRoomBoss) +
              " ; MindBloom boss fight :" +
              AbstractDungeon.lastCombatMetricKey.equals("Mind Bloom Boss Battle")
      );

      reward = (curRoom.eliteTrigger) ||
          (curRoom instanceof MonsterRoomBoss) ||
          AbstractDungeon.lastCombatMetricKey.equals("Mind Bloom Boss Battle");

      if (
          !(curRoom instanceof MonsterRoomBoss) && (
              !AbstractDungeon.lastCombatMetricKey.equals("Mind Bloom Boss Battle"))
      ) {
        while (this.tier == RelicTier.RARE) {
          this.tier = AbstractDungeon.returnRandomRelicTier();
        }
      }

      if (reward) {
        ThMod.logger.info(
            "TreasureHunterDamageAction : Checking : isDying :" + mon.isDying +
                " ; Current hp : " + mon.currentHealth
        );
        if (
            ((((AbstractMonster) this.target).isDying) || (this.target.currentHealth <= 0)) &&
                (!this.target.halfDead) &&
                (!this.target.hasPower("Minion"))
        ) {
          ThMod.logger.info("TreasureHunterDamageAction : Granting relic tier :" + this.tier);
          AbstractDungeon.getCurrRoom().addRelicToRewards(this.tier);
        }
      }
      if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
        AbstractDungeon.actionManager.clearPostCombatActions();
      }
    }
    tickDuration();
  }
}
