package ThMod_FnH.patches;
/*
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BewitchedHakkeroObtainPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "returnRandomRelicKey")
  public static class ReturnRelicPatch {

    @SpireInsertPatch(rloc = 58, localvars = {"retVal"})
    public static SpireReturn<String> Insert(AbstractDungeon inst,
        AbstractRelic.RelicTier tier, String retVal) {
      if ((retVal.equals("EnhancedHakkero"))
          && (!AbstractDungeon.player.hasRelic("MiniHakkero"))) {
        return SpireReturn.Return(AbstractDungeon.returnRandomRelicKey(tier));
      }
      return SpireReturn.Continue();
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "returnEndRandomRelicKey")
  public static class ReturnEndRelicPatch {

    @SpireInsertPatch(rloc = 56, localvars = {"retVal"})
    public static SpireReturn<String> Insert(AbstractDungeon inst,
        AbstractRelic.RelicTier tier, String retVal) {
      if ((retVal.equals("EnhancedHakkero"))
          && (!AbstractDungeon.player.hasRelic("MiniHakkero"))) {
        return SpireReturn.Return(AbstractDungeon.returnEndRandomRelicKey(tier));
      }
      return SpireReturn.Continue();
    }
  }
}
*/