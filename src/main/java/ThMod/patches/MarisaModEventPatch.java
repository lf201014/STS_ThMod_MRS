package ThMod.patches;

import static ThMod.ThMod.logger;

import ThMod.ThMod;

import ThMod.characters.Marisa;
import ThMod.event.Mushrooms_MRS;
import ThMod.event.OrinTheCat;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;

public class MarisaModEventPatch {

  @SpirePatch(clz = AbstractDungeon.class, method = "initializeCardPools")
  public static class EventPatch {

    @SpirePostfixPatch
    public static void EventListPatch(AbstractDungeon _exordium) {
      logger.info(
          "MarisaModEventPatch : EventListPatch :"
              + " PlayerCharacter : "
              + AbstractDungeon.player.title
      );
      String events = "";
      for (String tempStr : AbstractDungeon.eventList) {
        events += tempStr + " ; ";
      }
      logger.info("MarisaModEventPatch : current event list : " + events);

      if (AbstractDungeon.player instanceof Marisa) {
        logger.info("Marisa detected,removing Mushroom_MRS");
        AbstractDungeon.eventList.remove("Mushrooms");
      } else {
        logger.info("removing Mushroom_MRS");
        AbstractDungeon.eventList.remove("Mushrooms_MRS");
      }
      events = "";
      for (String tempStr : AbstractDungeon.eventList) {
        events += tempStr + " ; ";
      }
      logger.info("MarisaModEventPatch : current event list : " + events);
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "getEvent")
  public static class GetEventPatch {

    @SpirePostfixPatch
    public static AbstractEvent GetEventPatch(
        AbstractEvent _retVal,
        com.megacrit.cardcrawl.random.Random rng
    ) {
      logger.info(
          "MarisaModEventPatch : GetEventPatch :" +
              " PlayerCharacter  : " +
              AbstractDungeon.player.title +
              " ; retVal event : " +
              _retVal.toString()
      );
      /*
      if ((_retVal instanceof Mushrooms) && (AbstractDungeon.player instanceof Marisa)) {
        logger.info("Swapping mushroom event");
        return new Mushrooms_MRS();
      }
      return _retVal;
      */
      if (
          ((_retVal instanceof Mushrooms_MRS) && (AbstractDungeon.floorNum <= 6)) ||
          ((_retVal instanceof OrinTheCat) && (AbstractDungeon.player.hasRelic("CatCart")))
      ) {
        return AbstractDungeon.getEvent(AbstractDungeon.eventRng);
      } else {
        return _retVal;
      }
    }
  }
}
