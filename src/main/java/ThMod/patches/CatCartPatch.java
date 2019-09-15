package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class CatCartPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.characters.AbstractPlayer", method = "damage")
  public static class CatCartResurrect {

    @SpireInsertPatch(rloc = 141)
    public static SpireReturn insert(AbstractPlayer _inst, DamageInfo _info) {
      if ((_inst.hasRelic("CatCart"))&&(!_inst.hasRelic("Mark of the Bloom"))) {
        if ((_inst.getRelic("CatCart").counter > 0) && (!_inst.hasRelic("Mark of the Bloom"))) {
          _inst.currentHealth = 0;
          _inst.getRelic("CatCart").onTrigger();
          return SpireReturn.Return(null);
        }
      }
      return SpireReturn.Continue();
    }
  }
}
