package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod.characters.Marisa;
import ThMod.relics.SproutingBranch;

public class DeadBranchObtainPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.relics.DeadBranch", method = "makeCopy")
  public static class DeadBranchObtain {

    @SpirePrefixPatch
    public static SpireReturn<AbstractRelic> Prefix(AbstractRelic _inst) {
      if ((AbstractDungeon.player instanceof Marisa)) {
        return SpireReturn.Return(new SproutingBranch());
      }
      return SpireReturn.Continue();
    }
  }
}
