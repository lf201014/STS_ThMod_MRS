package ThMod;

import static ThMod.patches.AbstractCardEnum.MARISA_COLOR;
import static ThMod.patches.ThModClassEnum.MARISA;

import ThMod.cards.Marisa.AlicesGift;
import ThMod.cards.Marisa.EnergyRecoil;
import ThMod.cards.Marisa.ManaRampage;
import ThMod.cards.Marisa.SprinkleStarSeal;
import ThMod.cards.derivations.Exhaustion_MRS;
import ThMod.potions.ShroomBrew;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PotionStrings;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import ThMod.cards.Marisa.AbsoluteMagnitude;
import ThMod.cards.Marisa.AsteroidBelt;
import ThMod.cards.Marisa.BigCrunch;
import ThMod.cards.Marisa.BinaryStars;
import ThMod.cards.Marisa.BlazeAway;
import ThMod.cards.Marisa.BlazingStar;
import ThMod.cards.Marisa.CasketOfStar;
import ThMod.cards.Marisa.ChargeUpSpray;
import ThMod.cards.Marisa.ChargingUp;
import ThMod.cards.Marisa.CollectingQuirk;
import ThMod.cards.Marisa.D6C;
import ThMod.cards.Marisa.DarkMatter;
import ThMod.cards.Marisa.DarkSpark;
import ThMod.cards.Marisa.DeepEcologicalBomb;
import ThMod.cards.Marisa.Defend_MRS;
import ThMod.cards.Marisa.DoubleSpark;
import ThMod.cards.Marisa.DragonMeteor;
import ThMod.cards.Marisa.EnergyFlow;
import ThMod.cards.Marisa.EscapeVelocity;
import ThMod.cards.Marisa.EventHorizon;
import ThMod.cards.Marisa.FairyDestructionRay;
import ThMod.cards.Marisa.FinalSpark;
import ThMod.cards.Marisa.GalacticHalo;
import ThMod.cards.Marisa.GasGiant;
import ThMod.cards.Marisa.GrandCross;
import ThMod.cards.Marisa.GravityBeat;
import ThMod.cards.Marisa.IllusionStar;
import ThMod.cards.Marisa.JA;
import ThMod.cards.Marisa.LuminesStrike;
import ThMod.cards.Marisa.MachineGunSpark;
import ThMod.cards.Marisa.MagicAbsorber;
import ThMod.cards.Marisa.MagicChant;
import ThMod.cards.Marisa.ManaConvection;
import ThMod.cards.Marisa.MasterSpark;
import ThMod.cards.Marisa.MaximisePower;
import ThMod.cards.Marisa.MeteoricShower;
import ThMod.cards.Marisa.MilkyWay;
import ThMod.cards.Marisa.MillisecondPulsars;
import ThMod.cards.Marisa.MoraleDepletion;
import ThMod.cards.Marisa.MysteriousBeam;
import ThMod.cards.Marisa.NonDirectionalLaser;
import ThMod.cards.Marisa.Occultation;
import ThMod.cards.Marisa.OortCloud;
import ThMod.cards.Marisa.OpenUniverse;
import ThMod.cards.Marisa.Orbital;
import ThMod.cards.Marisa.OrrerysSun;
import ThMod.cards.Marisa.PolarisUnique;
import ThMod.cards.Marisa.PowerUp;
import ThMod.cards.Marisa.PropBag;
import ThMod.cards.Marisa.PulseMagic;
import ThMod.cards.Marisa.RefractionSpark;
import ThMod.cards.Marisa.Robbery;
import ThMod.cards.Marisa.SatelliteIllusion;
import ThMod.cards.Marisa.ShootTheMoon;
import ThMod.cards.Marisa.ShootingEcho;
import ThMod.cards.Marisa.Singularity;
import ThMod.cards.Marisa.SporeBomb;
import ThMod.cards.Marisa.StarBarrage;
import ThMod.cards.Marisa.StarDustReverie;
import ThMod.cards.Marisa.StarlightTyphoon;
import ThMod.cards.Marisa.Strike_MRS;
import ThMod.cards.Marisa.SuperNova;
import ThMod.cards.Marisa.SuperPerseids;
import ThMod.cards.Marisa.TreasureHunter;
import ThMod.cards.Marisa.UltraShortWave;
import ThMod.cards.Marisa.UnstableBomb;
import ThMod.cards.Marisa.UpSweep;
import ThMod.cards.Marisa.WitchLeyline;
import ThMod.cards.Marisa.WitchOfGreed;
import ThMod.cards.Marisa._6A;
import ThMod.cards.Marisa.EarthLightRay;
import ThMod.cards.derivations.BlackFlareStar;
import ThMod.cards.derivations.GuidingStar;
import ThMod.cards.derivations.Spark;
import ThMod.cards.derivations.WhiteDwarf;
import ThMod.characters.Marisa;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.GrandCrossPower;
import ThMod.relics.AmplifyWand;
import ThMod.relics.BreadOfAWashokuLover;
import ThMod.relics.EnhancedBroom;
import ThMod.relics.EnhancedHakkero;
import ThMod.relics.ExperimentalFamiliar;
import ThMod.relics.HandmadeGrimoire;
import ThMod.relics.MiniHakkero;
import ThMod.relics.RampagingMagicTools;
import ThMod.relics.ShroomBag;
import ThMod.relics.SimpleLauncher;
import ThMod.relics.SproutingBranch;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnPowersModifiedSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

@SpireInitializer
public class ThMod implements PostExhaustSubscriber,
    PostBattleSubscriber,
    PostDungeonInitializeSubscriber,
    EditCharactersSubscriber,
    PostInitializeSubscriber,
    EditRelicsSubscriber,
    EditCardsSubscriber,
    EditStringsSubscriber,
    OnCardUseSubscriber,
    EditKeywordsSubscriber,
    OnPowersModifiedSubscriber,
    PostDrawSubscriber {

  public static final Logger logger = LogManager.getLogger(ThMod.class.getName());

  //card backgrounds
  private static final String ATTACK_CC = "img/512/bg_attack_MRS_s.png";
  private static final String SKILL_CC = "img/512/bg_skill_MRS_s.png";
  private static final String POWER_CC = "img/512/bg_power_MRS_s.png";
  private static final String ENERGY_ORB_CC = "img/512/cardOrb.png";

  private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_MRS.png";
  private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_MRS.png";
  private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_MRS.png";
  private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/cardOrb.png";

  public static final Color STARLIGHT = CardHelper.getColor(0f, 10f, 125.0f);

  private static final String MY_CHARACTER_BUTTON = "img/charSelect/MarisaButton.png";
  private static final String MARISA_PORTRAIT = "img/charSelect/marisaPortrait.jpg";

  private static final String CARD_STRING = "localization/ThMod_Fnh_cards.json";
  private static final String CARD_STRING_JP = "localization/ThMod_Fnh_cards-jp.json";
  private static final String CARD_STRING_ZH = "localization/ThMod_Fnh_cards-zh.json";
  private static final String CARD_STRING_ZHT = "localization/ThMod_Fnh_cards-zht.json";
  private static final String CARD_STRING_KR = "localization/ThMod_Fnh_cards-kr.json";
  private static final String RELIC_STRING = "localization/ThMod_Fnh_relics.json";
  private static final String RELIC_STRING_JP = "localization/ThMod_Fnh_relics-jp.json";
  private static final String RELIC_STRING_ZH = "localization/ThMod_Fnh_relics-zh.json";
  private static final String RELIC_STRING_ZHT = "localization/ThMod_Fnh_relics-zht.json";
  private static final String RELIC_STRING_KR = "localization/ThMod_Fnh_relics-kr.json";
  private static final String POWER_STRING = "localization/ThMod_Fnh_powers.json";
  private static final String POWER_STRING_JP = "localization/ThMod_Fnh_powers-jp.json";
  private static final String POWER_STRING_ZH = "localization/ThMod_Fnh_powers-zh.json";
  private static final String POWER_STRING_ZHT = "localization/ThMod_Fnh_powers-zht.json";
  private static final String POWER_STRING_KR = "localization/ThMod_Fnh_powers-kr.json";
  private static final String POTION_STRING = "localization/ThMod_MRS_potions.json";
  private static final String POTION_STRING_JP = "localization/ThMod_MRS_potions-jp.json";
  private static final String POTION_STRING_ZH = "localization/ThMod_MRS_potions-zh.json";
  private static final String POTION_STRING_ZHT = "localization/ThMod_MRS_potions-zht.json";
  private static final String POTION_STRING_KR = "localization/ThMod_MRS_potions-kr.json";
  private static final String KEYWORD_STRING = "localization/ThMod_MRS_keywords.json";
  private static final String KEYWORD_STRING_JP = "localization/ThMod_MRS_keywords-zh.json";
  private static final String KEYWORD_STRING_KR = "localization/ThMod_MRS_keywords-kr.json";
  private static final String KEYWORD_STRING_ZHS = "localization/ThMod_MRS_keywords-zh.json";
  private static final String KEYWORD_STRING_ZHT = "localization/ThMod_MRS_keywords-zht.json";

  public static int typhoonCounter = 0;

  //For Spark Themed cards
  public static boolean isSpark(AbstractCard card) {
    return (
        (card.cardID.equals("Spark")) ||
            (card.cardID.equals("DarkSpark")) ||
            (card.cardID.equals("Strike_MRS")) ||
            (card.cardID.equals("FinalSpark")) ||
            (card.cardID.equals("DoubleSpark")) ||
            (card.cardID.equals("RefractionSpark")) ||
            (card.cardID.equals("MachineGunSpark")) ||
            (card.cardID.equals("MasterSpark"))
    );
  }

  //For the FXXKING Exhaustion curse
  public static boolean ExhaustionCheck() {
    boolean res = false;
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      if (c instanceof Exhaustion_MRS) {
        res = true;
      }
    }
    return res;
  }

  //For Amplify cards
  public static boolean Amplified(AbstractCard card, int AMP) {
    logger.info(
        "ThMod.Amplified : card to check : "
            + card.cardID
            + " ; costForTurn : "
            + card.costForTurn
    );
    AbstractPlayer p = AbstractDungeon.player;
    if (p.hasPower("MoraleDepletionPlusPower")) {
      logger.info("ThMod.Amplified :MoraleDepletionPower detected,returning false.");
      return false;
    }

    boolean res = false;
    if ((p.hasPower("MilliPulsaPower")) || (p.hasPower("PulseMagicPower"))
        || (card.freeToPlayOnce)) {
      logger.info(
          "ThMod.Amplified :Free Amplify tag detected,returning true : Milli :"
              + (p.hasPower("MilliPulsaPower"))
              + " ; Pulse :"
              + (p.hasPower("PulseMagicPower"))
              + " ; Free2Play :"
              + card.freeToPlayOnce
      );
      res = true;
    } else {
      if (EnergyPanel.totalCount >= (card.costForTurn + AMP)) {
        logger.info("ThMod.Amplified : Sufficient energy ,adding and returning true;");
        /*
        if ((card.costForTurn == 0) && (card.cost == 0)) {
          p.energy.use(AMP);
        }
        */
        card.costForTurn += AMP;
        res = true;
      }
    }

    if (res) {
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(
              p,
              p,
              new GrandCrossPower(p)
          )
      );
      if (p.hasPower("EventHorizonPower")) {
        p.getPower("EventHorizonPower").onSpecificTrigger();
      }
      if (p.hasRelic("AmplifyWand")) {
        AmplifyWand r = (AmplifyWand) p.getRelic("AmplifyWand");
        r.onSpecificTrigger();
      }
    }
    logger.info(
        "ThMod.Amplified : card : " + card.cardID + " ; Amplify : " + res + " ; costForTurn : "
            + card.costForTurn);
    return res;
  }

  public ThMod() {
    BaseMod.subscribe(this);
    logger.info("creating the color : MARISA_COLOR");
    BaseMod.addColor(
        MARISA_COLOR,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        ATTACK_CC,
        SKILL_CC,
        POWER_CC,
        ENERGY_ORB_CC,
        ATTACK_CC_PORTRAIT,
        SKILL_CC_PORTRAIT,
        POWER_CC_PORTRAIT,
        ENERGY_ORB_CC_PORTRAIT
    );
    BaseMod.addColor(
        AbstractCardEnum.MARISA_DERIVATIONS,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        STARLIGHT,
        ATTACK_CC,
        SKILL_CC,
        POWER_CC,
        ENERGY_ORB_CC,
        ATTACK_CC_PORTRAIT,
        SKILL_CC_PORTRAIT,
        POWER_CC_PORTRAIT,
        ENERGY_ORB_CC_PORTRAIT
    );
  }

  public void receiveEditCharacters() {
    logger.info("begin editing characters");

    logger.info("add " + MARISA.toString());
    BaseMod.addCharacter(
        new Marisa("Marisa"),
        MY_CHARACTER_BUTTON,
        MARISA_PORTRAIT,
        MARISA
    );
    logger.info("done editing characters");
  }

  public void receiveEditRelics() {
    logger.info("Begin editing relics.");

    BaseMod.addRelicToCustomPool(
        new MiniHakkero(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new EnhancedHakkero(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new EnhancedBroom(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new AmplifyWand(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new ExperimentalFamiliar(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new RampagingMagicTools(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new BreadOfAWashokuLover(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new SimpleLauncher(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new HandmadeGrimoire(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new ShroomBag(),
        MARISA_COLOR
    );
    BaseMod.addRelicToCustomPool(
        new SproutingBranch(),
        MARISA_COLOR
    );
    //BaseMod.addRelicToCustomPool(new Cape_1(), AbstractCardEnum.MARISA_COLOR);

    logger.info("Relics editting finished.");
  }

  public void receiveEditCards() {
    logger.info("begin editting cards");

    logger.info("add cards for MARISA");
    //starter:4
    BaseMod.addCard(new Strike_MRS());
    UnlockTracker.unlockCard("Strike_MRS");
    BaseMod.addCard(new Defend_MRS());
    UnlockTracker.unlockCard("Defend_MRS");
    BaseMod.addCard(new MasterSpark());
    UnlockTracker.unlockCard("MasterSpark");
    BaseMod.addCard(new UpSweep());
    UnlockTracker.unlockCard("UpSweep");
    //attack:31
    //Common: 10
    BaseMod.addCard(new DoubleSpark());
    UnlockTracker.unlockCard("DoubleSpark");
    BaseMod.addCard(new NonDirectionalLaser());
    UnlockTracker.unlockCard("NonDirectionalLaser");
    BaseMod.addCard(new LuminesStrike());
    UnlockTracker.unlockCard("LuminesStrike");
    BaseMod.addCard(new MysteriousBeam());
    UnlockTracker.unlockCard("MysteriousBeam");
    BaseMod.addCard(new WitchLeyline());
    UnlockTracker.unlockCard("WitchLeyline");
    BaseMod.addCard(new D6C());
    UnlockTracker.unlockCard("D6C");
    BaseMod.addCard(new _6A());
    UnlockTracker.unlockCard("6A");
    BaseMod.addCard(new UnstableBomb());
    UnlockTracker.unlockCard("UnstableBomb");
    BaseMod.addCard(new StarBarrage());
    UnlockTracker.unlockCard("StarBarrage_0");
    BaseMod.addCard(new ShootingEcho());
    UnlockTracker.unlockCard("ShootingEcho");
    //Uncommon: 12
    BaseMod.addCard(new MachineGunSpark());
    UnlockTracker.unlockCard("MachineGunSpark");
    BaseMod.addCard(new DarkSpark());
    UnlockTracker.unlockCard("DarkSpark");
    BaseMod.addCard(new DeepEcologicalBomb());
    UnlockTracker.unlockCard("DeepEcoloBomb");
    BaseMod.addCard(new MeteoricShower());
    UnlockTracker.unlockCard("MeteoricShower");
    BaseMod.addCard(new GravityBeat());
    UnlockTracker.unlockCard("GravityBeat");
    BaseMod.addCard(new GrandCross());
    UnlockTracker.unlockCard("GrandCross");
    BaseMod.addCard(new DragonMeteor());
    UnlockTracker.unlockCard("DragonMeteor");
    BaseMod.addCard(new RefractionSpark());
    UnlockTracker.unlockCard("RefractionSpark");
    BaseMod.addCard(new Robbery());
    UnlockTracker.unlockCard("Robbery");
    BaseMod.addCard(new ChargeUpSpray());
    UnlockTracker.unlockCard("ChargeUpSpray");
    BaseMod.addCard(new AlicesGift());
    UnlockTracker.unlockCard("AlicesGift");
    BaseMod.addCard(new FairyDestructionRay());
    UnlockTracker.unlockCard("FairyDestructionRay");
    //Rare:  7
    BaseMod.addCard(new BlazingStar());
    UnlockTracker.unlockCard("BlazingStar");
    BaseMod.addCard(new ShootTheMoon());
    UnlockTracker.unlockCard("ShootTheMoon");
    BaseMod.addCard(new FinalSpark());
    UnlockTracker.unlockCard("FinalSpark");
    BaseMod.addCard(new JA());
    UnlockTracker.unlockCard("JA");
    BaseMod.addCard(new AbsoluteMagnitude());
    UnlockTracker.unlockCard("AbsoluteMagnitude");
    BaseMod.addCard(new TreasureHunter());
    UnlockTracker.unlockCard("TreasureHunter");
    BaseMod.addCard(new CollectingQuirk());
    UnlockTracker.unlockCard("CollectingQuirk");

    //skill:28
    //Common : 6
    BaseMod.addCard(new MilkyWay());
    UnlockTracker.unlockCard("MilkyWay");
    BaseMod.addCard(new AsteroidBelt());
    UnlockTracker.unlockCard("AsteroidBelt");
    BaseMod.addCard(new PowerUp());
    UnlockTracker.unlockCard("PowerUp");
    BaseMod.addCard(new SporeBomb());
    UnlockTracker.unlockCard("SporeBomb");
    BaseMod.addCard(new IllusionStar());
    UnlockTracker.unlockCard("IllusionStar");
    BaseMod.addCard(new EnergyRecoil());
    UnlockTracker.unlockCard("EnergyRecoil");
    //Uncommon : 17
    BaseMod.addCard(new GasGiant());
    UnlockTracker.unlockCard("GasGiant");
    BaseMod.addCard(new StarDustReverie());
    UnlockTracker.unlockCard("StarDustReverie");
    BaseMod.addCard(new MagicAbsorber());
    UnlockTracker.unlockCard("MagicAbsorber");
    BaseMod.addCard(new Occultation());
    UnlockTracker.unlockCard("Occultation");
    BaseMod.addCard(new EarthLightRay());
    UnlockTracker.unlockCard("EarthLightRay");
    BaseMod.addCard(new BlazeAway());
    UnlockTracker.unlockCard("BlazeAway");
    BaseMod.addCard(new ChargingUp());
    UnlockTracker.unlockCard("ChargingUp");
    BaseMod.addCard(new DarkMatter());
    UnlockTracker.unlockCard("DarkMatter");
    BaseMod.addCard(new MagicChant());
    UnlockTracker.unlockCard("MagicChant");
    BaseMod.addCard(new MoraleDepletion());
    UnlockTracker.unlockCard("MoraleDepletion");
    BaseMod.addCard(new ManaConvection());
    UnlockTracker.unlockCard("ManaConvection");
    BaseMod.addCard(new PropBag());
    UnlockTracker.unlockCard("PropBag");
    BaseMod.addCard(new SprinkleStarSeal());
    UnlockTracker.unlockCard("SprinkleStarSeal");
    BaseMod.addCard(new GalacticHalo());
    UnlockTracker.unlockCard("GalacticHalo");
    BaseMod.addCard(new SuperPerseids());
    UnlockTracker.unlockCard("SuperPerseids");
    BaseMod.addCard(new PulseMagic());
    UnlockTracker.unlockCard("PulseMagic");
    BaseMod.addCard(new Orbital());
    UnlockTracker.unlockCard("Orbital");
    //Rare : 7
    BaseMod.addCard(new BigCrunch());
    UnlockTracker.unlockCard("BigCrunch");
    BaseMod.addCard(new OpenUniverse());
    UnlockTracker.unlockCard("OpenUniverse");
    BaseMod.addCard(new StarlightTyphoon());
    UnlockTracker.unlockCard("StarlightTyphoon");
    BaseMod.addCard(new MaximisePower());
    UnlockTracker.unlockCard("MaximisePower");
    BaseMod.addCard(new UltraShortWave());
    UnlockTracker.unlockCard("UltraShortWave");
    BaseMod.addCard(new ManaRampage());
    UnlockTracker.unlockCard("ManaRampage");
    BaseMod.addCard(new BinaryStars());
    UnlockTracker.unlockCard("BinaryStars");

    //power:12
    //common:1
    BaseMod.addCard(new WitchOfGreed());
    UnlockTracker.unlockCard("WitchOfGreed");
    //uncommon: 7
    BaseMod.addCard(new SatelliteIllusion());
    UnlockTracker.unlockCard("SatelliteIllusion");
    BaseMod.addCard(new OortCloud());
    UnlockTracker.unlockCard("OortCloud");
    BaseMod.addCard(new OrrerysSun());
    UnlockTracker.unlockCard("OrrerysSun");
    BaseMod.addCard(new EnergyFlow());
    UnlockTracker.unlockCard("EnergyFlow");
    BaseMod.addCard(new EventHorizon());
    UnlockTracker.unlockCard("EventHorizon");
    BaseMod.addCard(new Singularity());
    UnlockTracker.unlockCard("Singularity");
    BaseMod.addCard(new CasketOfStar());
    UnlockTracker.unlockCard("CasketOfStar");
    //rare: 4
    BaseMod.addCard(new PolarisUnique());
    UnlockTracker.unlockCard("PolarisUnique");
    BaseMod.addCard(new EscapeVelocity());
    UnlockTracker.unlockCard("EscapeVelocity");
    BaseMod.addCard(new MillisecondPulsars());
    UnlockTracker.unlockCard("MillisecondPulsars");
    BaseMod.addCard(new SuperNova());
    UnlockTracker.unlockCard("SuperNova");

    //derivations:4
    BaseMod.addCard(new Spark());
    UnlockTracker.unlockCard("Spark");
    BaseMod.addCard(new GuidingStar());
    UnlockTracker.unlockCard("GuidingStar");
    BaseMod.addCard(new BlackFlareStar());
    UnlockTracker.unlockCard("BlackFlareStar");
    BaseMod.addCard(new WhiteDwarf());
    UnlockTracker.unlockCard("WhiteDwarf");
    BaseMod.addCard(new Exhaustion_MRS());
    UnlockTracker.unlockCard("Exhaustion_MRS");

    logger.info("done editing cards");
  }

  public static void initialize() {
    new ThMod();
  }

  @Override
  public void receivePostExhaust(AbstractCard c) {
    // TODO Auto-generated method stub
  }

  @Override
  public void receivePostBattle(AbstractRoom r) {
    typhoonCounter = 0;
    logger.info("ThMod : PostBattle ; typhoon-counter reset");
  }

  @Override
  public void receiveCardUsed(AbstractCard card) {
    ThMod.logger.info("ThMod : Card used : " + card.cardID + " ; cost : " + card.costForTurn);
    if ((card.costForTurn == 0) || (card.costForTurn <= -2) || ((card.costForTurn == -1) && (
        AbstractDungeon.player.energy.energy <= 0))) {
      typhoonCounter++;
      ThMod.logger.info("typhoon-counter increased , now :" + typhoonCounter);
    }
    if (card.retain) {
      card.retain = false;
    }
  }

  @Override
  public void receivePowersModified() {
    // TODO Auto-generated method stub

  }

  @Override
  public void receivePostDungeonInitialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void receivePostDraw(AbstractCard arg0) {
    // TODO Auto-generated method stub
  }

  private static String loadJson(String jsonPath) {
    return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
  }

  @Override
  public void receiveEditKeywords() {
    logger.info("Setting up custom keywords");

    String keywordsPath;
    switch (Settings.language) {
      case ZHT:
        keywordsPath = KEYWORD_STRING_ZHT;
        break;
      case ZHS:
        keywordsPath = KEYWORD_STRING_ZHS;
        break;
      case KOR:
        keywordsPath = KEYWORD_STRING_KR;
        break;
      case JPN:
        keywordsPath = KEYWORD_STRING_JP;
        break;
      default:
        keywordsPath = KEYWORD_STRING;
        break;
    }

    Gson gson = new Gson();
    Keywords keywords;
    keywords = gson.fromJson(loadJson(keywordsPath), Keywords.class);
    for (Keyword key : keywords.keywords) {
      logger.info("Loading keyword : " + key.NAMES[0]);
      BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
    }
/*
    if (Settings.language == Settings.GameLanguage.ZHS) {
      BaseMod.addKeyword(new String[]{"\u529b\u7aed"},
          "\u529b\u7aed\u4f1a\u4f7f\u4f60\u65e0\u6cd5\u83b7\u5f97\u6216\u4f7f\u7528 \u84c4\u529b \u3002");
      BaseMod
          .addKeyword(new String[]{"\u53d6\u51b3\u4e8e\u6240\u6d88\u8017\u5361\u7684\u79cd\u7c7b"},
              "\u653b\u51fb\uff1a\u6050\u60e7\u836f\u6c34\uff1b\u6280\u80fd\uff1a\u865a\u5f31\u836f\u6c34\uff1b\u80fd\u529b\uff1a\u6bd2\u7d20\u836f\u6c34\uff1b\u72b6\u6001\uff1a\u706b\u7130\u836f\u6c34\uff1b\u8bc5\u5492\uff1a\u70df\u96fe\u0020\u5f39\u0020\u3002");
      BaseMod.addKeyword(new String[]{"\u706b\u82b1"},
          "\u706b\u82b1\u662f\u4e00\u5f20\u6d88\u8017\u4e3a\u0030\u7684\u653b\u51fb\u724c");
      BaseMod.addKeyword(new String[]{"\u84c4\u529b"},
          "\u6bcf8\u5c42\u84c4\u529b\u4f1a\u4f7f\u4f60\u7684\u4e0b\u4e00\u6b21\u653b\u51fb\u4f24\u5bb3\u7ffb\u4e00\u500d\u3002");
      BaseMod.addKeyword(new String[]{"\u589e\u5e45"},
          "\u5f53\u4f60\u7684\u8d39\u7528\u8db3\u591f\u4f7f\u7528\u8fd9\u5f20\u724c\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u65f6\uff0c\u4f7f\u7528\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u3002");
    } else if (Settings.language == Settings.GameLanguage.ZHT) {
      BaseMod.addKeyword(new String[]{"\u529b\u7aed"},
          "\u529b\u7aed\u6642\u7121\u6cd5\u7372\u5f97\u6216\u4f7f\u7528 \u84c4\u529b");
      BaseMod
          .addKeyword(new String[]{"\u53d6\u6c7a\u65bc\u6240\u6d88\u8017\u724c\u7684\u7a2e\u985e"},
              "\u653b\u64ca\uff1a\u6050\u61fc\u85e5\u6c34\uff1b\u6280\u80fd\uff1a\u865b\u5f31\u85e5\u6c34\uff1b\u80fd\u529b\uff1a\u6bd2\u7d20\u85e5\u6c34\uff1b\u72c0\u614b\uff1a\u706b\u7130\u85e5\u6c34\uff1b\u8a5b\u5492\uff1a\u7159\u9727\u5f48");
      BaseMod.addKeyword(new String[]{"\u706b\u82b1"},
          "\u706b\u82b1\u662f\u6703\u6d88\u8017\u7684 #b0 \u8017\u80fd\u653b\u64ca\u724c");
      BaseMod.addKeyword(new String[]{"\u84c4\u529b"},
          "\u6bcf\u0038\u5c64\u84c4\u529b\u6703\u4f7f\u4f60\u7684\u4e0b\u4e00\u6b21\u653b\u64ca\u50b7\u5bb3\u52a0\u500d");
      BaseMod.addKeyword(new String[]{"\u589e\u5e45"},
          "\u7576\u6709\u8db3\u5920\u7684 [B] \u6642\uff0c\u4f7f\u7528\u5176\u9032\u968e\u6548\u679c");
    } else {
     
    BaseMod.addKeyword(new String[]{"\u6d88\u8017"},
            "\u6d88\u8017\u3059\u308b\u3068\u3001 \u30c1\u30e3\u30fc\u30b8 \u3092\u5f97\u305f\u308a\u6d88\u8cbb\u3059\u308b\u4e8b\u304c\u51fa\u6765\u306a\u3044\u3002");
    BaseMod.addKeyword(new String[]{"\u30ab\u30fc\u30c9\u306b\u3088\u3063\u3066\u7570\u306a\u308b"},
            "\u30a2\u30bf\u30c3\u30af \uff1a \u6050\u6016\u30dd\u30fc\u30b7\u30e7\u30f3 NL \u30b9\u30ad\u30eb \uff1a \u8131\u529b\u30dd\u30fc\u30b7\u30e7\u30f3 NL \u30d1\u30ef\u30fc \uff1a \u6bd2\u30dd\u30fc\u30b7\u30e7\u30f3 NL \u72b6\u614b\u7570\u5e38 \uff1a \u706b\u708e\u30dd\u30fc\u30b7\u30e7\u30f3 NL \u546a\u3044 \uff1a \u7159\u7389");
    BaseMod.addKeyword(new String[]{"\u30b9\u30d1\u30fc\u30af"},
            "\u30b9\u30d1\u30fc\u30af\u306f\u30b3\u30b9\u30c8 #b0 \u306e \u30a2\u30bf\u30c3\u30af \u30ab\u30fc\u30c9");
    BaseMod.addKeyword(new String[]{"\u30c1\u30e3\u30fc\u30b8"},
            "#b8 \u30b9\u30bf\u30c3\u30af\u3054\u3068\u306b\u3001\u4e0e\u3048\u308b\u30c0\u30e1\u30fc\u30b8\u304c #b2 \u500d\u306b\u306a\u308b\u3002");
    BaseMod.addKeyword(new String[]{"\u5897\u5e45"},
            "\u5897\u5e45\u306b\u5fc5\u8981\u306a [B] \u3092\u6301\u3063\u3066\u3044\u308b\u5834\u5408\u3001[B] \u3092\u6d88\u8cbb\u3057\u3000\u5897\u5e45\u52b9\u679c\u3092\u5f97\u308b");

      //Korean Localization
      BaseMod.addKeyword(new String[]{"\uc99d\ud3ed"},
          "\ucda9\ubd84\ud55c [B] \uac00 \uc788\ub2e4\uba74 \ucd94\uac00\ub85c \uc18c\ubaa8\ud574 \ud6a8\uacfc\ub97c \uac15\ud654\ud569\ub2c8\ub2e4.");
      BaseMod.addKeyword(new String[]{"\uace0\uac08"},
          "\uc99d\ud3ed, \ucda9\uc804 \ud6a8\uacfc\ub97c \ubc1c\ub3d9 \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
      BaseMod.addKeyword(new String[]{"\uc2a4\ud30c\ud06c"},
          "\ube44\uc6a9\uc774 0\uc778 \uacf5\uaca9\uce74\ub4dc\uc785\ub2c8\ub2e4.");
      BaseMod.addKeyword(new String[]{"\ucda9\uc804"},
          "\ucda9\uc804\uc774 8 \uc313\uc77c \ub54c \ub9c8\ub2e4 \ub2e4\uc74c \uc0ac\uc6a9\ud558\ub294 \uacf5\uaca9 \uce74\ub4dc\uc758 \ud53c\ud574\uac00 2\ubc30\uc529 \uc99d\uac00\ud558\uba70 \ucda9\uc804\uc744 \uc18c\ubaa8\ud569\ub2c8\ub2e4.");
      BaseMod.addKeyword(new String[]{"\uc18c\uba78\uc2dc\ud0a8 \uce74\ub4dc\uc758 \uc885\ub958"},
          "\uacf5\uaca9 : \uacf5\ud3ec \ud3ec\uc158 ; NL \uc2a4\ud0ac : \uc57d\ud654 \ud3ec\uc158 ; NL \ud30c\uc6cc : \uc911\ub3c5 \ud3ec\uc158 ; NL \uc0c1\ud0dc\uc774\uc0c1 : \ud654\uc5fc \ud3ec\uc158 ; NL \uc800\uc8fc : \uc5f0\ub9c9\ud0c4.");

      BaseMod.addKeyword(new String[]{"amplify", "Amplify"},
          "Pay extra energy for its effect when you have enough  [B] .");
      BaseMod.addKeyword(new String[]{"Exhaustion", "exhaustion"},
          "Exhaustion prevent you from gaining or using Charge-Up .");
      BaseMod.addKeyword(new String[]{"Spark", "spark"},
          "Spark is an attack card cost 0 energy.");
      BaseMod.addKeyword(new String[]{"Charge-up", "charge-up", "chargeup", "ChargeUp"},
          "For every 8 stacks,double your damage.");
      BaseMod.addKeyword(new String[]{
              "Depends On The Type Of The Card",
              "depends on the type of the card"
          },
          "Attack : Fear Potion ; NL Skill : Weak Potion ; NL Power : Poison Potion ; Status : Fire Potion ; Curse : Smoke Bomb ."
      );
    }
*/
    logger.info("Keywords setting finished.");
  }

  @Override
  public void receiveEditStrings() {
    logger.info("start editing strings");

    String relicStrings, cardStrings, powerStrings, potionStrings, relic, card, power, potion;

    if (Settings.language == Settings.GameLanguage.ZHS) {
      logger.info("lang == zhs");
      card = CARD_STRING_ZH;
      relic = RELIC_STRING_ZH;
      power = POWER_STRING_ZH;
      potion = POTION_STRING_ZH;
    } else if (Settings.language == Settings.GameLanguage.JPN) {
      logger.info("lang == jpn");
      card = CARD_STRING_JP;
      relic = RELIC_STRING_JP;
      power = POWER_STRING_JP;
      potion = POTION_STRING_JP;
    } else if (Settings.language == Settings.GameLanguage.ZHT) {
      logger.info("lang == zht");
      card = CARD_STRING_ZHT;
      relic = RELIC_STRING_ZHT;
      power = POWER_STRING_ZHT;
      potion = POTION_STRING_ZHT;
    } else if (Settings.language == Settings.GameLanguage.KOR) {
      logger.info("lang == kor");
      card = CARD_STRING_KR;
      relic = RELIC_STRING_KR;
      power = POWER_STRING_KR;
      potion = POTION_STRING_KR;
    } else {
      logger.info("lang == eng");
      card = CARD_STRING;
      relic = RELIC_STRING;
      power = POWER_STRING;
      potion = POTION_STRING;
    }

    relicStrings = Gdx.files.internal(relic).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    cardStrings = Gdx.files.internal(card).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    powerStrings = Gdx.files.internal(power).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    potionStrings = Gdx.files.internal(potion).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);

    logger.info("done editing strings");
  }

  @Override
  public void receivePostInitialize() {
    // TODO Auto-generated method stub
    //BaseMod.addEvent(Mushrooms_MRS.ID, Mushrooms_MRS.class, Exordium.ID);
    BaseMod
        .addPotion(ShroomBrew.class, Color.NAVY.cpy(), Color.LIME.cpy(), Color.OLIVE, "ShroomBrew",
            MARISA);
  }

  class Keywords {

    Keyword[] keywords;
  }
	/*
	


............................................................................................................................................
............................................................................................................................................
....................................................:7......................................................................................
....................................................7II7?,::::::::::::......................................................................
.................................,,,...............,,????7+~::::::::::::,...................................................................
..............................~:,,,:::......,..::::=I777 7?I77,:::::::::::..................................................................
...................................,,:::::::::::::,7777I777777777=,:::::::::................................................................
....................................,,,,:::,,,,,.::=7 777777?777777I::,,,,,,::..............................................................
...............................................,:,,777777777777?777777+.,,,,,,,.............................................................
..............................................:,I7III777777?77777I?77I77.,,,,,,,,...........................................................
............................................,,,:7I++II7777777?=7I77I=77I=,,,,,,,,,.....~?IIIIII?+=:,........................................
...........................................,,,,,,=7777777II77777?I?77+I7I,,,,,,,,.=+7777777777777777777777I?=====...........................
..........................................,,,,,,7II7777777777=?7II77???=~:..,,:,II77I?==+?I77777777777777IIIIII7:...........................
........................................,,,,,,,..===7777?=I=IIIII++++?I7III7I77~7II777777777777777777777777+................................
.......................................,,,,~7III7=I?...,~?I7?III=II..,,,,,,..~I~=?77I?+++?I7777777777I7777=.................................
..................................,,,,,,,,,~++III?+IIIIIIIIIIII7=====III7+~~?II77:,,,?7I?+?I7777777I++IIIII7................................
..........................,,,,,,+?III?7~~??IIII+7+==+:+?++?IIIIIIIIIIII?7=I?IIIIIIII=,,.,:,+77777?I7777=.,..................................
.......................,,,,,,,7IIIIII+?7III7I~~:III~?II=?III+:~IIIIIIIII?II,==:I7=II~,,,,,,,,.~7I77I=+7.....................................
...................::::::,,,,IIIIIIIIIIII:II=IIIIIII?I=I???~~+~I=I?II??I?I7II:III=?IIII+..I??I7,,,.+777.....................................
................,:::::::,,:~7IIIII7I7I7??I?=???I?IIII?+I?I=~I?,I?=??????IIII?I+??I=?=+III??IIII7.,,,,.......................................
.............:::::::,~+=+7IIIIIIII7?I7:???~?????~I???+~I??=~I?=~?I=+II~???????7????I=I??III7?II,,,,,:,,,:...................................
............:::::,+IIIIII?+II7II77777II??=????+?:I???+=????+?I+7+II=?:I?+??????=+????=???+IIIIIII:.7I~7?:,,,................................
...........,:::,7IIIIIIIIIIII7I777?+~77?I7???++=7?~+?++I~7?IIII~7+??~77??=I????~=+????=??:7IIII77I+?IIIIII=.,,:.............................
..............,7IIIIIIIII7777II++??++I??=????+=7II+??,I+=?+~~II?I777~~I???.I+??~=+?????+??+=?I7777IIII+?I7,:::::,,..........................
..................77IIIIII7I77777+++~I???~I??I~I.:=,~~=,,77I7+I+77I7I7~:...:?,I.+++:??????:?I+7777IIIIIIII7~,,::,,:.........................
...................IIII77777777?~+?==+I?+?+?++?+7:~=::~=~I777=+I7777I7I7=?=:::~.+::+?????+I==?~?7777777II???II7I7~::........................
....................,=+?II?=?7I???===?=+???+++=I7=~++~=?+7777?I777777777:==~~=:?=?+~===????++?+I77I?I77I77I7777777=.........................
...............................??+:=?=~???:~?+=I7II~=~~I7777777777777777~+I??+==+?+??:+=??+?=+~I77+7777:..:I777I=:..........................
...............................I??=??~??????=??I=IIIIII77777777I77777777IIII7=7+?????+:~+??=+=+?~?+~,.......................................
...............................~I????~???+???:IIII=7II777777777777777777II7+=I+I????I=+++?=+~+??............................................
..............................??+,??+~=?+:+???????I+II777I?+~~~~=I7I7777I+=I???????+=++~+??=???~............................................
.............................~?~+??+=+?:I~?:+????===7777777I=+??==777777+??7I??~=?+==?????+??==:............................................
.............................:I.+????:??:+:=~=???I,:I7I7777777777777777I~=+I++II?+?+??+??+???..,:...........................................
..............................~..?+??=?==I?~?+II?=+?=~==?=I77777777?=++=~==I===?I?++???+?++:?...............................................
...............................,..I++II:I??=+,I?~?~7III+~III?===?II?~IIII?~:=~=??I~I???+??+,.:,.............................................
....................................+I?III+I=I=?::~7777I=:~=???++==~I777I?:+~++~I~I??++?I:+,................................................
.....................................,.I++=I=~?:.I+II7I77777777777?I+7=+II=+=:.+,II?+~+=~:..................................................
....................................  7I:7?,.,,,=~=7~?7777777777777777..==?.,,,,.=II77777,..................................................
..................................I777777I~::::::7777=+77I?+====+?++I==.7~.~~::::.77777777,.................................................
.................................+ 77777II::::::,7777777  I+==+IIIIII7.~III7+++++++++++++++++++++++++++++++++++++++++=~.....................
................................=77777777I:::::::+777777777777?+?++.~7IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?................
...............................I7777777777,,:::::,+777777777777~?:~IIIII====II:7?~7IIII+=III==~+III7~~III?IIIIII~:77?,=77III7+..............
...............................III77777777.::::::::::::,:::::,.7.:IIIIII....:....,.=7......?I+7II?,:....+?..7??IIII????I+I7III+.............
..............................++II77777777.::::::::::::~?+++???I.?IIIII7,,,,:.=:.==?III.?7:::::+III7..++++??II,:==.?~=7.?IIIII7.............
............................+7777777IIIII7,:::::::::::::.?+????I.+IIIII7====?......+II.=7?.IIIIIII.=.,I:?I:.7I,=?I.?:=I.?IIIIII.............
............................777777I=7I==?I,::::::::::::=I,:,...,,.?IIII7....?.......?.~II7~,..,~7I?..=I~..=7II,+==.?I:~.?IIII7..............
.............................:777?+~7777I7,:::::::::::,??+,:::::::.,IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,...............
.................................:777+~+II~::::::::::,,,,::,,::::::,:,..=7I77777777777777777777777777777777777777777777?,...................
..................................+=IIIIIII.:::::::,,,,,,,,,,,,,,,,,,,,:::,,,,,,,.????????I,................................................
....................................7777777.,::::,,,,,,,,=.:,,,,,,,,,.??:,,,,::,,.7IIIIII77:................................................
....................................7IIIIII~.,,,,,,,,,,,==:.,,,,,,,,,,:~.,,,,,,,,.IIIIIIIII~................................................
............................................................................................................................................
............................................................................................................................................
............................................................................................................................................
                                                                      
	 */
}
	

