package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class CatCartPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.characters.AbstractPlayer", method = "damage")
  public static class CatCartResurrect {

    @SpireInsertPatch(rloc = 92)
    public static void insert(AbstractPlayer _inst, DamageInfo _info) {
      if ((_inst.currentHealth < 1) && (_inst.hasRelic("CatCart"))) {
        if ((_inst.getRelic("CatCart").counter > 0) && (!_inst.hasRelic("Mark of the Bloom"))) {
          _inst.currentHealth = 0;
          _inst.getRelic("CatCart").onTrigger();
        }
      }
    }
  }
}
