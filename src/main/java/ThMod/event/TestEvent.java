package ThMod.event;

//public class TestEvent {


import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

public class TestEvent extends AbstractEvent {

  public static final String ID = "WWWWWWWWWWWWWW";
  private static final EventStrings eventStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private static final String[] OPTIONS;
  private static final String INTRO_MSG;
  private CurScreen screen;

  public TestEvent() {
    this.screen = CurScreen.INTRO;
    this.initializeImage("images/events/sphereClosed.png", 1120.0F * Settings.scale,
        AbstractDungeon.floorY - 50.0F * Settings.scale);
    this.body = INTRO_MSG;
    this.roomEventText.addDialogOption(OPTIONS[0]);
    this.roomEventText.addDialogOption(OPTIONS[1]);
    this.hasDialog = true;
    this.hasFocus = true;
    AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("ZombieFairy");
  }

  public void update() {
    super.update();
    if (!RoomEventDialog.waitForInput) {
      this.buttonEffect(this.roomEventText.getSelectedOption());
    }

  }

  protected void buttonEffect(int buttonPressed) {
    switch (this.screen) {
      case INTRO:
        switch (buttonPressed) {
          case 0:
            this.screen = CurScreen.PRE_COMBAT;
            this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
            this.roomEventText.updateDialogOption(0, OPTIONS[2]);
            this.roomEventText.clearRemainingOptions();
            logMetric("WWWWWWWWWWWWWW", "Fight");
            return;
          case 1:
            this.screen = CurScreen.END;
            this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
            this.roomEventText.updateDialogOption(0, OPTIONS[1]);
            this.roomEventText.clearRemainingOptions();
            logMetricIgnored("WWWWWWWWWWWWWW");
            return;
          default:
            return;
        }
      case PRE_COMBAT:
        if (Settings.isDailyRun) {
          AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(50));
        } else {
          AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(45, 55));
        }

        AbstractDungeon.getCurrRoom()
            .addRelicToRewards(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE));
        this.img = ImageMaster.loadImage("images/events/sphereOpen.png");
        this.enterCombat();
        AbstractDungeon.lastCombatMetricKey = "ZombieFairy";
        break;
      case END:
        this.openMap();
    }

  }

  static {
    eventStrings = CardCrawlGame.languagePack.getEventString("WWWWWWWWWWWWWW");
    NAME = eventStrings.NAME;
    DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    OPTIONS = eventStrings.OPTIONS;
    INTRO_MSG = DESCRIPTIONS[0];
  }

  private enum CurScreen {
    INTRO,
    PRE_COMBAT,
    END;

    CurScreen() {
    }
  }
}