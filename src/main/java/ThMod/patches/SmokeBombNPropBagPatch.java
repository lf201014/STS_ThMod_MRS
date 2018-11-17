package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.powers.Marisa.PropBagPower;

public class SmokeBombNPropBagPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.potions.SmokeBomb", method = "use")
  public static class SmokePatch {

    public static void Prefix() {
      for (AbstractPower p : AbstractDungeon.player.powers) {
        if (p instanceof PropBagPower) {
          p.onVictory();
        }
      }
    }
  }
}
