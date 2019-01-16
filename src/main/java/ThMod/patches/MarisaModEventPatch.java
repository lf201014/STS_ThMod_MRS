package ThMod.patches;
/*
import ThMod.ThMod;

import ThMod.characters.Marisa;
import ThMod.event.Mushrooms_MRS;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;

public class MarisaModEventPatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.Exordium", method = "initializeEventList")
  public static class EventPatch {

    @SpirePostfixPatch
    public static void EventListPatch(AbstractDungeon _exordium) {
      ThMod.logger.info(
          "MarisaModEventPatch : EventListPatch :"
              + " PlayerCharacter : "
              + AbstractDungeon.player.title
      );
      String events = new String();
      for (String tempStr : AbstractDungeon.eventList) {
        events += tempStr + " ; ";
      }
      ThMod.logger.info("MarisaModEventPatch : current event list : " + events);
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "getEvent")
  public static class GetEventPatch {

    @SpirePostfixPatch
    public static AbstractEvent GetEventPatch(
        AbstractEvent _retVal,
        com.megacrit.cardcrawl.random.Random rng
    ) {
      ThMod.logger.info(
          "MarisaModEventPatch : GetEventPatch :" +
              " PlayerCharacter  : " +
              AbstractDungeon.player.title +
              " ; retVal event : " +
              _retVal.toString()
      );
      if ((_retVal instanceof Mushrooms)&&(AbstractDungeon.player instanceof Marisa)) {
        ThMod.logger.info("Swapping mushroom event");
        return new Mushrooms_MRS();
      }
      return _retVal;
    }
  }
}
*/