package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import ThMod.ThMod;

public class TyphoonCounterPreBattlePatch {
  @SpirePatch(cls = "com.megacrit.cardcrawl.rooms.MonsterRoom",method = "onPlayerEntry")
  public static class TyphoonCounterReset{
    public static void PostFix(){
      ThMod.typhoonCounter = 0;
      ThMod.logger.info("TyphoonCounterPatch : PreBattle : typhoon counter reset ; current counter : "+ThMod.typhoonCounter);
    }
  }

}
