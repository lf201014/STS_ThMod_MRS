package ThMod.patches;
/*
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.SoundInfo;
import com.megacrit.cardcrawl.audio.SoundMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.SoundMaster", method="update")
public class SoundMasterUpdatePatch
{
  public static final Logger logger = LogManager.getLogger(SoundMasterUpdatePatch.class);

  @SpireInsertPatch(rloc=4, localvars={"e", "sfx"})
  public static void Insert(SoundMaster _inst, SoundInfo e, @ByRef(type="com.megacrit.cardcrawl.audio.Sfx") Object[] sfx)
  {
    if (sfx[0] == null) {
      sfx[0] = SoundMasterPlayPatch.map.get(e.name);
    }
  }
}

*/