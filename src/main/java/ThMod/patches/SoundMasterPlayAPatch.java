package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;
import java.util.HashMap;

@SpirePatch(cls = "com.megacrit.cardcrawl.audio.SoundMaster", method = "playA", paramtypes = {"java.lang.String", "float"})
public class SoundMasterPlayAPatch {

  public static HashMap<String, Sfx> map = new HashMap();

  public static long Postfix(long res, SoundMaster _inst, String key, float pitchAdjust) {
    if (map.containsKey(key)) {
      return ((Sfx) map.get(key))
          .play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + pitchAdjust, 0.0F);
    }
    return 0L;
  }

  private static Sfx load(String filename) {
    return new Sfx("audio/sound/" + filename, false);
  }

  static {
    map.put("SELECT_MRS", load("se_pldead00.ogg"));
  }
}