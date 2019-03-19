package ThMod.event;

import static ThMod.ThMod.logger;

import ThMod.ThMod;
import ThMod.cards.derivations.Wraith;
import ThMod.relics.CatCart;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
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
  private AbstractMonster orin;
  private boolean satori;

  private enum CurScreen {
    INTRO,
    PRE_COMBAT,
    END;

    CurScreen() {
    }
  }

  public OrinTheCat() {
    /*
    initializeImage(
        "images/events/sphereClosed.png",
        1120.0F * Settings.scale,
        AbstractDungeon.floorY - 50.0F * Settings.scale
    );
*/
    this.roomEventText.clear();
    this.body = INTRO_MSG;
    satori = AbstractDungeon.player.name.equals("Komeiji");
    if (satori) {
      this.roomEventText.addDialogOption(OPTIONS[4]);
    } else {
      this.roomEventText.addDialogOption(OPTIONS[0], CardLibrary.getCopy("Wraith"));
    }
    this.roomEventText.addDialogOption(OPTIONS[1]);

    this.hasDialog = true;
    this.hasFocus = true;
    AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Orin");
    orin = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
    if (orin != null) {
      logger.info("OrinTheCat : orin get : " + orin.name);
    } else {
      logger.info("OrinTheCat : error : null orin");
    }
  }

  @Override
  public void update() {
    super.update();
    if (!RoomEventDialog.waitForInput) {
      buttonEffect(this.roomEventText.getSelectedOption());
    }
  }

  @Override
  public void onEnterRoom() {
    logger.info("OrinTheCat : OnEnterRoom");
    AbstractDungeon.getCurrRoom().rewards.clear();
  }

  @Override
  protected void buttonEffect(int buttonPressed) {
    switch (this.screen) {
      case INTRO:
        switch (buttonPressed) {
          case 0:
            if (satori) {
              this.screen = CurScreen.END;
              AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                  (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), new CatCart()
              );
              this.roomEventText.updateBodyText(DESCRIPTIONS[3]);
              this.roomEventText.updateDialogOption(0, OPTIONS[3]);
              this.roomEventText.clearRemainingOptions();
              logMetric(ID, "Special");
              return;
            } else {
              logger.info("OrinTheCat : INTRO : Skipping fight!");
              this.screen = CurScreen.END;
              /*
              if (orin != null) {
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(orin));
              }
              */
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
            }
          case 1:
            if (satori) {
              this.screen = CurScreen.END;
              /*
              if (orin != null) {
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(orin));
              }
              */
              this.roomEventText.updateBodyText(DESCRIPTIONS[4]);
              this.roomEventText.updateDialogOption(0, OPTIONS[3]);
              this.roomEventText.clearRemainingOptions();
              logMetricIgnored(ID);
              return;
            } else {
              logger.info("OrinTheCat : INTRO : Fight!");
              this.screen = CurScreen.PRE_COMBAT;
              this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
              this.roomEventText.updateDialogOption(0, OPTIONS[2]);
              this.roomEventText.clearRemainingOptions();
              logMetric(ID, "Fight");
              return;
            }
            /*
          default:
            logger.info(
                "OrinTheCat event : error : key number "
                    + buttonPressed
                    + " should never be pressed."
            );
            */
        }
        break;
      case PRE_COMBAT:
        logger.info("OrinTheCat : PreCombat : Adding Reward");
        AbstractRoom currRoom = AbstractDungeon.getCurrRoom();
        currRoom.rewards.clear();
        if(!AbstractDungeon.player.hasRelic("CatCart")){
          if (Settings.isDailyRun) {
            currRoom.addGoldToRewards(AbstractDungeon.miscRng.random(50));
          } else {
            currRoom.addGoldToRewards(AbstractDungeon.miscRng.random(45, 55));
          }
          currRoom.addRelicToRewards(new CatCart());
        }

        AbstractDungeon.getCurrRoom().eliteTrigger = true;
        //this.img = ImageMaster.loadImage("images/events/sphereOpen.png");

        logger.info("OrinTheCat : PreCombat : Entering combat");
        enterCombat();
        AbstractDungeon.lastCombatMetricKey = "Orin";
        break;
      case END:
        logger.info("OrinTheCat : end : Opening Map");
        openMap();
    }
  }
}

//public class OrinTheCat \
