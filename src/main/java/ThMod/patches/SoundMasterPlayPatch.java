package ThMod.patches;
/*
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;
import java.util.HashMap;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.SoundMaster", method="play", paramtypes={"java.lang.String", "boolean"})
public class SoundMasterPlayPatch
{
  public static HashMap<String, Sfx> map = new HashMap();

  public static long Postfix(long res, SoundMaster _inst, String key, boolean useBgmVolume)
  {
    if (map.containsKey(key))
    {
      if (useBgmVolume) {
        return ((Sfx)map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
      }
      return ((Sfx)map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
    }
    return res;
  }

  private static Sfx load(String filename)
  {
    return new Sfx("audio/sound/" + filename, false);
  }

  static
  {
    map.put("MRS_SELECT", load("miss.ogg"));
  }
}

*/