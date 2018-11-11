package ThMod.patches;
/*
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.stats.CharStat;

import ThMod.ThMod;
import basemod.BaseMod;

public class AscensionPatch {

  public static final Logger logger = ThMod.logger;

  @SpirePatch(cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method = "updateHitbox")
  public static class CharSelectFix {

    @SpireInsertPatch(rloc = 64, localvars = {"c", "pref"})
    public static void Insert(CharacterOption _inst, AbstractPlayer.PlayerClass c,
        @ByRef Prefs[] pref) {
      AscensionPatch.logger.info("AscensionPatch : CharSelectFix");
      if (pref[0] == null) {
        AscensionPatch.logger.info(c.toString());
        pref[0] = SaveHelper.getPrefs(c.toString());
        if (pref[0] == null) {
          AscensionPatch.logger.info("AscensionPatch : CharSelectFix : pref == null?");
        } else {
          AscensionPatch.logger.info(Integer.valueOf(pref[0].getInteger("ASCENSION_LEVEL", 1)));
        }
      }
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.screens.stats.StatsScreen", method = "getVictory")
  public static class getVictoryFix {

    public static int Postfix(int retVal, AbstractPlayer.PlayerClass c) {
      if (BaseMod.playerStatsMap.get(c) != null) {
        return ((CharStat) BaseMod.playerStatsMap.get(c)).getVictoryCount();
      }
      return retVal;
    }
  }
}
*/