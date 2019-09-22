package ThMod.action.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

@Deprecated
public class StarBarrageDamageAction
    extends AbstractGameAction {

  private DamageInfo info;
  private AbstractCard card;
  private static final float DURATION = 0.1F;

  public StarBarrageDamageAction(AbstractCreature target, AbstractCard card) {
    this.card = card;
    this.info =
      new DamageInfo(
          AbstractDungeon.player,
          card.damage,
          card.damageTypeForTurn
      );
    setValues(target, info);
    this.actionType = AbstractGameAction.ActionType.DAMAGE;
    this.duration = DURATION;
  }

  public void update() {
    if ((this.duration == 0.1F) && (this.target != null)) {
      AbstractDungeon.effectList.add(
          new FlashAtkImgEffect(
              this.target.hb.cX,
              this.target.hb.cY,
              AttackEffect.FIRE)
      );

      AbstractMonster mon = (AbstractMonster) this.target;

      int tmp = mon.currentHealth;

      this.target.damage(this.info);

      int res;

      if (mon.isDying) {
        res = tmp;
      } else {
        res = tmp - mon.currentHealth;
      }

      if (res > 0) {
        card.baseDamage += res;
        card.applyPowers();
      }

      if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
        AbstractDungeon.actionManager.clearPostCombatActions();
      }
    }
    tickDuration();
  }
}
