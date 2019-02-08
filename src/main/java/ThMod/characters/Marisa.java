package ThMod.characters;

import ThMod.ThMod;
import ThMod.cards.Marisa.MasterSpark;
import ThMod.patches.AbstractCardEnum;
import ThMod.patches.ThModClassEnum;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Marisa extends CustomPlayer {

  private static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
  private static final String MARISA_SHOULDER_2 = "img/char/Marisa/shoulder2.png"; // shoulder2 / shoulder_1
  private static final String MARISA_SHOULDER_1 = "img/char/Marisa/shoulder1.png"; // shoulder1 / shoulder_2
  private static final String MARISA_CORPSE = "img/char/Marisa/fallen.png"; // dead corpse
  public static final Logger logger = LogManager.getLogger(ThMod.class.getName());
  //private static final float[] layerSpeeds = { 20.0F, 0.0F, -40.0F, 0.0F, 0.0F, 5.0F, 0.0F, -8.0F, 0.0F, 8.0F };
  private static final String MARISA_SKELETON_ATLAS = "img/char/Marisa/MarisaModelv3.atlas";// Marisa_v0 / MarisaModel_v02 /MarisaModelv3
  private static final String MARISA_SKELETON_JSON = "img/char/Marisa/MarisaModelv3.json";
  private static final String MARISA_ANIMATION = "Idle";// Sprite / Idle
  private static final String[] ORB_TEXTURES = {
      "img/UI/EPanel/layer5.png",
      "img/UI/EPanel/layer4.png",
      "img/UI/EPanel/layer3.png",
      "img/UI/EPanel/layer2.png",
      "img/UI/EPanel/layer1.png",
      "img/UI/EPanel/layer0.png",
      "img/UI/EPanel/layer5d.png",
      "img/UI/EPanel/layer4d.png",
      "img/UI/EPanel/layer3d.png",
      "img/UI/EPanel/layer2d.png",
      "img/UI/EPanel/layer1d.png"
  };
  private static final String ORB_VFX = "img/UI/energyBlueVFX.png";
  private static final float[] LAYER_SPEED =
      {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
  //public static final String SPRITER_ANIM_FILEPATH = "img/char/MyCharacter/marisa_test.scml"; // spriter animation scml

  public Marisa(String name) {
    //super(name, setClass, null, null , null ,new SpriterAnimation(SPRITER_ANIM_FILEPATH));
    super(name, ThModClassEnum.MARISA, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
    //super(name, setClass, null, null, (String) null, null);

    this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
    this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

    logger.info("init Marisa");

    initializeClass(
        null,
        MARISA_SHOULDER_2, // required call to load textures and setup energy/loadout
        MARISA_SHOULDER_1,
        MARISA_CORPSE,
        getLoadout(),
        20.0F, -10.0F, 220.0F, 290.0F,
        new EnergyManager(ENERGY_PER_TURN)
    );

    loadAnimation(MARISA_SKELETON_ATLAS, MARISA_SKELETON_JSON, 2.0F);
    // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
    AnimationState.TrackEntry e = this.state.setAnimation(0, MARISA_ANIMATION, true);
    e.setTime(e.getEndTime() * MathUtils.random());
    this.stateData.setMix("Hit", "Idle", 0.1F);
    e.setTimeScale(1.0F);
    logger.info("init finish");
  }

  public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
    ArrayList<String> retVal = new ArrayList<>();
    retVal.add("Strike_MRS");
    retVal.add("Strike_MRS");
    retVal.add("Strike_MRS");
    retVal.add("Strike_MRS");
    retVal.add("Defend_MRS");
    retVal.add("Defend_MRS");
    retVal.add("Defend_MRS");
    retVal.add("Defend_MRS");
    retVal.add("MasterSpark");
    retVal.add("UpSweep");
    return retVal;
  }

  public ArrayList<String> getStartingRelics() { // starting relics - also simple
    ArrayList<String> retVal = new ArrayList<>();
    retVal.add("MiniHakkero");
    UnlockTracker.markRelicAsSeen("MiniHakkero");
    return retVal;
  }

  private static final int STARTING_HP = 75;
  private static final int MAX_HP = 75;
  private static final int STARTING_GOLD = 99;
  private static final int HAND_SIZE = 5;
  private static final int ASCENSION_MAX_HP_LOSS = 5;

  public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
    String title;
    String flavor;
    if (Settings.language == Settings.GameLanguage.ZHS) {
      title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
      flavor = "\u4f4f\u5728\u9b54\u6cd5\u68ee\u6797\u7684\u9b54\u6cd5\u4f7f\u3002 NL \u5584\u957f\u4e8e\u5149\u548c\u70ed\u7684\u9b54\u6cd5\u3002";
    } else if (Settings.language == Settings.GameLanguage.JPN) {
      title = "\u666e\u901a\u306e\u9b54\u6cd5\u4f7f\u3044";
      flavor = "\u9b54\u6cd5\u306e\u68ee\u306b\u4f4f\u3093\u3067\u3044\u308b\u666e\u901a\u306e\u9b54\u6cd5\u4f7f\u3044\u3002 NL \u5149\u3068\u71b1\u306e\u9b54\u6cd5\u304c\u5f97\u610f\u3002";
    } else if (Settings.language == Settings.GameLanguage.ZHT) {
      title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
      flavor = "\u4f4f\u5728\u9b54\u6cd5\u68ee\u6797\u7684\u9b54\u6cd5\u4f7f\u3002 NL \u5584\u9577\u65bc\u5149\u548c\u71b1\u7684\u9b54\u6cd5\u3002";
    } else if (Settings.language == Settings.GameLanguage.KOR) {
      title = "\ud3c9\ubc94\ud55c \ub9c8\ubc95\uc0ac";
      flavor = "\ub9c8\ubc95\uc758 \uc232\uc5d0 \uc0ac\ub294 \"\ud3c9\ubc94\ud55c\" \ub9c8\ubc95\uc0ac \uc785\ub2c8\ub2e4. NL \ube5b\uacfc \uc5f4 \ub9c8\ubc95\uc774 \ud2b9\uae30\uc785\ub2c8\ub2e4.";
    } else if(Settings.language == Settings.GameLanguage.FRA) {
      title = "La magicienne ordinaire";
      flavor = "La magicienne \"ordinaire\" vie dans la for\u00eat magique.  NL Sp\u00e9cialis\u00e9es dans la magie de la lumi\u00e8re et de la chaleur.";
    } else {
      title = "The Ordinary Magician";
      flavor = "The \"ordinary\" magician lives in the Forest of Magic. NL Specializes in light and heat magic.";
    }
    return new CharSelectInfo(
        title,
        flavor,
        STARTING_HP,
        MAX_HP,
        0,
        STARTING_GOLD,
        HAND_SIZE,
        this,
        getStartingRelics(),
        getStartingDeck(),
        false
    );
  }

  public AbstractCard.CardColor getCardColor() {
    return AbstractCardEnum.MARISA_COLOR;
  }

  public AbstractCard getStartCardForEvent() {
    return new MasterSpark();
  }

  public String getTitle(PlayerClass playerClass) {
    String title;
    if (Settings.language == GameLanguage.ZHS) {
      title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
    } else if (Settings.language == GameLanguage.JPN) {
      title = "\u666e\u901a\u306e\u9b54\u6cd5\u4f7f\u3044";
    } else if (Settings.language == GameLanguage.ZHT) {
      title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
    } else if (Settings.language == GameLanguage.KOR) {
      title = "\ud3c9\ubc94\ud55c \ub9c8\ubc95\uc0ac";
    } else {
      title = "The Ordinary Magician";
    }
    return title;
  }

  public Color getCardTrailColor() {
    return ThMod.STARLIGHT;
  }

  public int getAscensionMaxHPLoss() {
    return ASCENSION_MAX_HP_LOSS;
  }

  public BitmapFont getEnergyNumFont() {
    return FontHelper.energyNumFontBlue;
  }

  public void doCharSelectScreenSelectEffect() {
    CardCrawlGame.sound.playA("SELECT_MRS", MathUtils.random(-0.1F, 0.1F));
    CardCrawlGame.screenShake.shake(
        ScreenShake.ShakeIntensity.MED,
        ScreenShake.ShakeDur.SHORT,
        false
    );
  }

  public String getCustomModeCharacterButtonSoundKey() {
    return "SELECT_MRS";
  }

  public String getLocalizedCharacterName() {
    String char_name;
    if ((Settings.language == Settings.GameLanguage.JPN) || (Settings.language
        == Settings.GameLanguage.ZHS) || (Settings.language == Settings.GameLanguage.ZHT)) {
      char_name = "\u9b54\u7406\u6c99";
    } else if (Settings.language == Settings.GameLanguage.KOR) {
      char_name = "\ub9c8\ub9ac\uc0ac";
    } else {
      char_name = "Marisa";
    }
    return char_name;
  }

  public AbstractPlayer newInstance() {
    return new Marisa(this.name);
  }

  @Override
  public String getVampireText() {
    return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
  }

  public Color getCardRenderColor() {
    return ThMod.STARLIGHT;
  }

  public void updateOrb(int orbCount) {
    this.energyOrb.updateOrb(orbCount);
  }

  public TextureAtlas.AtlasRegion getOrb() {
    return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ThMod.CARD_ENERGY_ORB), 0, 0, 24, 24);
  }

  public Color getSlashAttackColor() {
    return ThMod.STARLIGHT;
  }

  public AttackEffect[] getSpireHeartSlashEffect() {
    return new AttackEffect[]{
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL,
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL
    };
  }

  public String getSpireHeartText() {
    return com.megacrit.cardcrawl.events.beyond.SpireHeart.DESCRIPTIONS[10];
  }

  public void damage(DamageInfo info) {
    if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (
        info.output - this.currentBlock > 0)) {
      AnimationState.TrackEntry e =
          this.state.setAnimation(0, "Hit", false);
      this.state.addAnimation(0, "Idle", true, 0.0F);
      e.setTimeScale(1.0F);
    }
    super.damage(info);
  }

  public void applyPreCombatLogic() {
    super.applyPreCombatLogic();
    ThMod.typhoonCounter = 0;
    ThMod.logger.info(
        "Marisa : applyPreCombatLogic : I just reset the god damn typhoon counter ! current counter : "
            + ThMod.typhoonCounter
    );
  }

}
