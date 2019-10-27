package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import java.util.ArrayList;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class CatCartPatch {

  @SpirePatch(clz = AbstractPlayer.class, method = "damage",paramtypez = {DamageInfo.class})
  public static class CatCartResurrect {

    @SpireInsertPatch(
        //locator = Locator.class
        rloc = 149
    )
    public static SpireReturn insert(AbstractPlayer _inst, DamageInfo _info) {
      if ((_inst.hasRelic("CatCart")) && (!_inst.hasRelic("Mark of the Bloom"))) {
        if ((_inst.getRelic("CatCart").counter > 0) && (!_inst.hasRelic("Mark of the Bloom"))) {
          _inst.currentHealth = 0;
          _inst.getRelic("CatCart").onTrigger();
          return SpireReturn.Return(null);
        }
      }
      return SpireReturn.Continue();
    }
  }
/*
  private static class Locator extends SpireInsertLocator {

    public int[] Locate(CtBehavior ctMethodToPatch)
        throws CannotCompileException, PatchingException {
      Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "isDead");
      int[] loc = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
      return new int[]{loc[0]};
    }
  }
  */
}
