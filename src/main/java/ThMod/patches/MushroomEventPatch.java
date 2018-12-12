package ThMod.patches;
/*
import static ThMod.ThMod.logger;

import ThMod.characters.Marisa;
import ThMod.event.Mushrooms_MRS;
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
}*/