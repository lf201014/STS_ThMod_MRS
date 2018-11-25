package ThMod.action;

import ThMod.powers.monsters.WraithPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import java.util.ArrayList;

public class OrinsDebuffAction extends AbstractGameAction {

  private AbstractPlayer p = AbstractDungeon.player;
  private int stack;
  AbstractCreature orin;

  public OrinsDebuffAction(int amount, AbstractCreature source) {
    this.actionType = ActionType.DEBUFF;
    this.duration = Settings.ACTION_DUR_FAST;
    stack = amount;
    orin = source;
  }

  public void update() {
    this.isDone = false;
    ArrayList<AbstractPower> pows = new ArrayList<>();
    for (AbstractPower pow : p.powers) {
      if (pow.type == AbstractPower.PowerType.BUFF) {
        pows.add(pow);
      }
    }
    if (!pows.isEmpty()) {
      AbstractPower po = pows.get(AbstractDungeon.miscRng.random(0, pows.size() - 1));
      AbstractDungeon.actionManager.addToBottom(
          new RemoveSpecificPowerAction(p, orin, po)
      );
    }
    int stc = AbstractDungeon.miscRng.random(stack, stack + 2);

    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            orin,
            new WraithPower(p, stack),
            stack
        )
    );
    this.isDone = true;
    AbstractDungeon.actionManager.addToTop(
        new VFXAction(
            p
            , new InflameEffect(p)
            , 0.2F
        )
    );
  }
}
