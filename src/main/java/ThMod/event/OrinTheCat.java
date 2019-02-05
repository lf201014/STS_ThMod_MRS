package ThMod.event;

import ThMod.ThMod;
import ThMod.cards.derivations.Wraith;
import ThMod.relics.CatCart;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class OrinTheCat
    extends AbstractEvent {

  public static final String ID = "OrinTheCat";
  private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
  public static final String NAME = eventStrings.NAME;
  private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
  private static final String[] OPTIONS = eventStrings.OPTIONS;
  private static final String INTRO_MSG = DESCRIPTIONS[0];
  private CurScreen screen = CurScreen.INTRO;

  enum CurScreen {
    INTRO, PRE_COMBAT, END
  }

  public OrinTheCat() {
    initializeImage(
        "images/events/sphereClosed.png",
        1120.0F * Settings.scale,
        AbstractDungeon.floorY - 50.0F * Settings.scale
    );

    this.roomEventText.clear();

    this.body = INTRO_MSG;
    this.roomEventText.addDialogOption(OPTIONS[0]);
    this.roomEventText.addDialogOption(OPTIONS[1]);

    this.hasDialog = true;
    this.hasFocus = true;
    AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Orin");
  }

  public void update() {
    super.update();
    if (!RoomEventDialog.waitForInput) {
      buttonEffect(this.roomEventText.getSelectedOption());
    }
  }

  protected void buttonEffect(int buttonPressed) {
    switch (this.screen) {
      case INTRO:
        switch (buttonPressed) {
          case 0:
            this.screen = CurScreen.END;
            this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
            this.roomEventText.updateDialogOption(0, OPTIONS[3]);
            this.roomEventText.clearRemainingOptions();
            AbstractDungeon.effectList.add(
                new ShowCardAndObtainEffect(
                    new Wraith(),
                    Settings.WIDTH / 2.0F,
                    Settings.HEIGHT / 2.0F
                )
            );
            logMetricIgnored(ID);
            return;
          case 1:
            this.screen = CurScreen.PRE_COMBAT;
            this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
            this.roomEventText.updateDialogOption(0, OPTIONS[2]);
            this.roomEventText.clearRemainingOptions();
            logMetric(ID, "Fight");
            return;
          default:
            ThMod.logger.info(
                "OrinTheCat event : error : key number "
                    + buttonPressed
                    + " should never be pressed."
            );
        }
        break;
      case PRE_COMBAT:
        if (Settings.isDailyRun) {
          AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(50));
        } else {
          AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(45, 55));
        }
        AbstractDungeon.getCurrRoom().addRelicToRewards(
            new CatCart()
        );
        //this.img = ImageMaster.loadImage("images/events/sphereOpen.png");

        enterCombat();
        AbstractDungeon.lastCombatMetricKey = "Orin";
        break;
      case END:
        openMap();
    }
  }
}

//public class OrinTheCat \
