package ThMod.event;

import ThMod.ThMod;
import ThMod.characters.Marisa;
import ThMod.relics.ShroomBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.OddMushroom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.Logger;

public class Mushrooms_MRS
    extends AbstractEvent {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "Mushrooms_MRS";
  private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Mushrooms");
  public static final String NAME = eventStrings.NAME;
  public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
  public static final String[] OPTIONS = eventStrings.OPTIONS;
  public static final String ENC_NAME = "The Mushroom Lair";
  private Texture fgImg = ImageMaster.loadImage("images/events/fgShrooms.png");
  private Texture bgImg = ImageMaster.loadImage("images/events/bgShrooms.png");
  private static final float HEAL_AMT = 0.25F;
  private static final String HEAL_MSG = DESCRIPTIONS[0];
  private static final String FIGHT_MSG = DESCRIPTIONS[1];
  private int screenNum = 0;
  public static String DESCRIPTION_MRS =
      "You enter a corridor full of #b~hypnotizing~ #b~colored~ #b~mushrooms.~"
          + " NL These mushrooms are nothing like any other ones you've seen before."
          + " NL You want to collect some of them, but feel oddly compelled to eat #b~one...~";
  public static String DESCRIPTION_MRS_ZHS =
      "\u4f60\u8d70\u8fdb\u4e00\u6761\u904d\u5730\u662f"
          + " #b~\u4e94\u5f69\u6591\u6593\u8611\u83c7~ "
          + "\u7684\u8d70\u5eca\uff0c"
          + " NL \u8fd9\u4e9b\u8611\u83c7\u548c\u4f60\u4e4b\u524d\u89c1\u8fc7\u7684\u79cd\u7c7b\u90fd\u4e0d\u4e00\u6837\u3002"
          + " NL \u4f60\u60f3\u8981\u5c06\u5b83\u4eec\u6536\u96c6\u8d77\u6765\uff0c\u4f46\u5374\u53c8\u6709\u4e00\u79cd\u5947\u602a\u7684\u51b2\u52a8\u60f3\u8981\u53bb\u5403\u4e00\u4e2a"
          + " #b~\u8611\u83c7......~ ";
  public static String OPTION_MRS = "[Collect] #rAnger #rthe #rMushrooms.";
  public static String OPTION_MRS_ZHS = "[\u6536\u96c6] #r\u6fc0\u6012\u8611\u83c7\u4eec\u3002 ";

  public Mushrooms_MRS() {
    this.body = DESCRIPTIONS[2];

    if (AbstractDungeon.player instanceof Marisa) {
      this.body = (Settings.language == GameLanguage.ZHS) ? DESCRIPTION_MRS_ZHS : DESCRIPTION_MRS;
      this.roomEventText.addDialogOption(
          (Settings.language == GameLanguage.ZHS) ? OPTION_MRS_ZHS : OPTION_MRS
      );
    } else {
      this.roomEventText.addDialogOption(OPTIONS[0]);
    }

    int temp = (int) (AbstractDungeon.player.maxHealth * 0.25F);
    this.roomEventText.addDialogOption(
        OPTIONS[1] + temp + OPTIONS[2],
        CardLibrary.getCopy("Parasite")
    );

    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.EVENT;
    this.hasDialog = true;
    this.hasFocus = true;
  }

  public void update() {
    super.update();
    if (!RoomEventDialog.waitForInput) {
      buttonEffect(this.roomEventText.getSelectedOption());
    }
  }

  protected void buttonEffect(int buttonPressed) {
    switch (buttonPressed) {
      case 0:
        if (this.screenNum == 0) {
          AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("The Mushroom Lair");

          this.roomEventText.updateBodyText(FIGHT_MSG);
          this.roomEventText.updateDialogOption(0, OPTIONS[3]);
          this.roomEventText.removeDialogOption(1);
          AbstractEvent.logMetric("Mushrooms", "Fought Mushrooms");
          this.screenNum += 2;
        } else if (this.screenNum == 1) {
          openMap();
        } else if (this.screenNum == 2) {
          if (Settings.isDailyRun) {
            AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.eventRng.random(25));
          } else {
            AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.eventRng.random(20, 30));
          }
          if (AbstractDungeon.player.hasRelic("ShroomBag")) {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new OddMushroom());
          } else {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new ShroomBag());
          }
          enterCombat();
          AbstractDungeon.lastCombatMetricKey = ENC_NAME;
        }
        return;
      case 1:
        AbstractCard curse = new Parasite();
        int healAmt = (int) (AbstractDungeon.player.maxHealth * HEAL_AMT);
        AbstractEvent.logMetricObtainCardAndHeal(
            "Mushrooms",
            "Healed and dodged fight",
            curse,
            healAmt
        );
        AbstractDungeon.player.heal(healAmt);
        AbstractDungeon.effectList
            .add(new ShowCardAndObtainEffect(curse, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        this.roomEventText.updateBodyText(HEAL_MSG);
        this.roomEventText.updateDialogOption(0, OPTIONS[4]);
        this.roomEventText.removeDialogOption(1);
        this.screenNum = 1;
        return;
    }
    logger.info("ERROR: case " + buttonPressed + " should never be called");
  }

  public void render(SpriteBatch sb) {
    sb.setColor(Color.WHITE);
    sb.draw(this.bgImg, 0.0F, -10.0F, Settings.WIDTH, 1080.0F * Settings.scale);
    sb.draw(this.fgImg, 0.0F, -20.0F * Settings.scale, Settings.WIDTH, 1080.0F * Settings.scale);
  }
}
