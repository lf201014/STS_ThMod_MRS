package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

public class BewitchedHakkeroObtainPatch {


  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "returnRandomRelicKey")
  public static class ReturnRelicPatch {

    @SpirePostfixPatch
    public static String Postfix(String retVal, RelicTier tier) {
      if ((retVal.equals("EnhancedHakkero")) && (!AbstractDungeon.player.hasRelic("MiniHakkero"))) {
        return AbstractDungeon.returnRandomRelicKey(tier);
      }
      return retVal;
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "returnEndRandomRelicKey")
  public static class ReturnEndRelicPatch {

    @SpirePostfixPatch
    public static String Postfix(String retVal, RelicTier tier) {
      if ((retVal.equals("EnhancedHakkero")) && (!AbstractDungeon.player.hasRelic("MiniHakkero"))) {
        return AbstractDungeon.returnEndRandomRelicKey(tier);
      }
      return retVal;
    }
  }
}

