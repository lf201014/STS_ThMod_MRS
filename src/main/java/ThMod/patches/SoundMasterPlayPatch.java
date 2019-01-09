package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;
import java.util.HashMap;
import com.megacrit.cardcrawl.audio.Sfx;

@SpirePatch(cls = "com.megacrit.cardcrawl.audio.SoundMaster", method = "play", paramtypes = {"java.lang.String", "boolean"})
public class SoundMasterPlayPatch {

  public static HashMap<String, Sfx> map = new HashMap();

  public static long Postfix(final long res, final SoundMaster _inst, final String key,
      final boolean useBgmVolume) {
    if (!SoundMasterPlayPatch.map.containsKey(key)) {
      return res;
    }
    if (useBgmVolume) {
      return SoundMasterPlayPatch.map.get(key).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
    }
    return SoundMasterPlayPatch.map.get(key).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
  }

  private static Sfx load(final String filename) {
    return new Sfx("audio/sound/" + filename, false);
  }

  static {
    map.put("SELECT_MRS", load("se_pldead00.ogg"));
  }
}
