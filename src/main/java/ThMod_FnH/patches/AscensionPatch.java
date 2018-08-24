package ThMod_FnH.patches;
/*
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import basemod.BaseMod;

public class AscensionPatch{
    @SpirePatch(cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method = "updateHitbox")
    public static class CharSelectFix {
        @SpireInsertPatch(rloc = 64, localvars = { "c", "pref" })
        public static void Insert(final CharacterOption _inst, final AbstractPlayer.PlayerClass c, @ByRef final Prefs[] pref) {
            if (pref[0] == null) {
                pref[0] = SaveHelper.getPrefs(c.toString());
            }
        }
    }
    
    @SpirePatch(cls = "com.megacrit.cardcrawl.screens.stats.StatsScreen", method = "getVictory")
    public static class getVictoryFix {
        public static int Postfix(final int retVal, final AbstractPlayer.PlayerClass c) {
            if (BaseMod.playerStatsMap.get(c.toString()) != null) {
                return BaseMod.playerStatsMap.get(c.toString()).getVictoryCount();
            }
            return retVal;
        }
    }
}
*/