package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class RobberyDamageAction
    extends AbstractGameAction {

  private DamageInfo info;
  private static final float DURATION = 0.1F;
  private boolean amp;

  public RobberyDamageAction(AbstractCreature target, DamageInfo info, boolean amp) {
    this.info = info;
    setValues(target, info);
    this.actionType = AbstractGameAction.ActionType.DAMAGE;
    this.duration = DURATION;
    this.amp = amp;
  }

  public void update() {
    if ((this.duration == 0.1F) && (this.target != null)) {
      AbstractDungeon.effectList.add(
          new FlashAtkImgEffect(
              this.target.hb.cX,
              this.target.hb.cY,
              AbstractGameAction.AttackEffect.BLUNT_HEAVY
          )
      );

      AbstractMonster mon = (AbstractMonster) this.target;

      int tmp = mon.currentHealth;

      this.target.damage(this.info);

      int res;

      if ((((AbstractMonster) this.target).isDying) || (this.target.currentHealth <= 0)) {
        res = tmp;
      } else {
        res = tmp - mon.currentHealth;
      }
      if (this.amp) {
        res *= 2;
      }

      AbstractPlayer p = AbstractDungeon.player;

      p.gainGold(res);
      for (int i = 0; i < res; i++) {
        AbstractDungeon.effectList.add(
            new GainPennyEffect(
                p,
                target.hb.cX,
                target.hb.cY,
                p.hb.cX,
                p.hb.cY,
                true
            )
        );
      }

      if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
        AbstractDungeon.actionManager.clearPostCombatActions();
      }
    }
    tickDuration();
  }
}
