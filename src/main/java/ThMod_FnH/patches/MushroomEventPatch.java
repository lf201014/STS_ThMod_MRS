package ThMod_FnH.patches;

import static ThMod_FnH.ThMod.logger;

import ThMod_FnH.characters.Marisa;
import ThMod_FnH.event.Mushrooms_MRS;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;
import com.megacrit.cardcrawl.random.Random;

public class MushroomEventPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "getEvent")
  public static class GetEventFix {

    @SpirePostfixPatch()
    public static AbstractEvent PostFix(AbstractEvent _retVal,Random random) {
      logger.info(
          "MarisaModEventPatch : GetEvent : _retVal : "
              + _retVal.toString()
              + " ; PlayerCharacter : "
              + AbstractDungeon.player.toString()
      );
      if ((AbstractDungeon.player instanceof Marisa) && (_retVal instanceof Mushrooms)) {
        logger.info("Marisa & Mushrooms detected,altering return value.");
        //return new Mushrooms_MRS();
        return AbstractDungeon.getShrine(AbstractDungeon.eventRng);
      } else if (_retVal instanceof Mushrooms_MRS) {
        logger.info("Mushrooms_MRS detected,returning shrine.");
        return AbstractDungeon.getShrine(AbstractDungeon.eventRng);
      }
      return _retVal;
    }
  }
}
/*
import ThMod_FnH.characters.Marisa;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;

public class MushroomEventPatch {

  public static String DESCRIPTION_MRS =
      "You enter a corridor full of #b~hypnotizing~ #b~colored~ #b~mushrooms.~"
          + " NL These mushrooms are nothing like any other ones you've seen before."
          + " NL You want to collect some of them, but feel oddly compelled to eat #b~one...~";
  public static String DESCRIPTION_MRS_ZHS =
      "\u4f60\u8d70\u8fdb\u4e00\u6761\u904d\u5730\u662f"
          + " #b~\u4e94\u5f69\u6591\u6593\u8611\u83c7~ "
          + "\u7684\u8d70\u5eca\uff0c"
          + " NL \u8fd9\u4e9b\u8611\u83c7\u548c\u4f60\u4e4b\u524d\u89c1\u8fc7\u7684\u79cd\u7c7b\u90fd\u4e0d\u4e00\u6837\u3002"
          + "\u4f60\u60f3\u8981\u5c06\u5b83\u4eec\u6536\u96c6\u8d77\u6765\uff0c\u4f46\u5374\u53c8\u6709\u4e00\u79cd\u5947\u602a\u7684\u51b2\u52a8\u60f3\u8981\u53bb\u5403\u4e00\u4e2a"
          + " #b~\u8611\u83c7......~ ";
  public static String OPTION_MRS = "[Collect] #rAnger #rthe #rMushrooms.";
  public static String OPTION_MRS_ZHS = "[\u6536\u96c6] #r\u6fc0\u6012\u8611\u83c7\u4eec\u3002 ";

  @SpirePatch(cls = "com.megacrit.cardcrawl.events.exordium.Mushrooms",method = SpirePatch.STATICINITIALIZER)
  public static class MushroomEventInitPatch{

    @SpireInsertPatch(
        rloc = 13,
        localvars = {
            "OPTIONS",
            "DESCRIPTIONS"
        }
    )
    public static void Insert(Mushrooms _inst,String[] OPTIONS,String[] DESCRIPTIONS){
      if (AbstractDungeon.player instanceof Marisa){
        OPTIONS[0] = (Settings.language == GameLanguage.ZHS) ? OPTION_MRS_ZHS : OPTION_MRS ;
        DESCRIPTIONS[2] = (Settings.language == GameLanguage.ZHS) ? DESCRIPTION_MRS_ZHS : DESCRIPTION_MRS ;
      }
    }
  }

}
*/