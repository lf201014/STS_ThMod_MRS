package ThMod_FnH;

import static ThMod_FnH.patches.AbstractCardEnum.MARISA_COLOR;

import ThMod_FnH.cards.Marisa.AlicesGift;
import ThMod_FnH.cards.Marisa.EnergyRecoil;
import ThMod_FnH.cards.Marisa.ManaRampage;
import ThMod_FnH.cards.derivations.Exhaustion_MRS;
import ThMod_FnH.event.Mushrooms_MRS;
import com.megacrit.cardcrawl.dungeons.Exordium;
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

import ThMod_FnH.cards.Marisa.AbsoluteMagnitude;
import ThMod_FnH.cards.Marisa.AsteroidBelt;
import ThMod_FnH.cards.Marisa.BigCrunch;
import ThMod_FnH.cards.Marisa.BinaryStars;
import ThMod_FnH.cards.Marisa.BlazeAway;
import ThMod_FnH.cards.Marisa.BlazingStar;
import ThMod_FnH.cards.Marisa.CasketOfStar;
import ThMod_FnH.cards.Marisa.ChargeUpSpray;
import ThMod_FnH.cards.Marisa.ChargingUp;
import ThMod_FnH.cards.Marisa.CollectingQuirk;
import ThMod_FnH.cards.Marisa.D6C;
import ThMod_FnH.cards.Marisa.DarkMatter;
import ThMod_FnH.cards.Marisa.DarkSpark;
import ThMod_FnH.cards.Marisa.DeepEcologicalBomb;
import ThMod_FnH.cards.Marisa.Defend_MRS;
import ThMod_FnH.cards.Marisa.DoubleSpark;
import ThMod_FnH.cards.Marisa.DragonMeteor;
import ThMod_FnH.cards.Marisa.EnergyFlow;
import ThMod_FnH.cards.Marisa.EscapeVelocity;
import ThMod_FnH.cards.Marisa.EventHorizon;
import ThMod_FnH.cards.Marisa.FairyDestructionRay;
import ThMod_FnH.cards.Marisa.FinalSpark;
import ThMod_FnH.cards.Marisa.FungusSplash;
import ThMod_FnH.cards.Marisa.GalacticHalo;
import ThMod_FnH.cards.Marisa.GasGiant;
import ThMod_FnH.cards.Marisa.GrandCross;
import ThMod_FnH.cards.Marisa.GravityBeat;
import ThMod_FnH.cards.Marisa.IllusionStar;
import ThMod_FnH.cards.Marisa.JA;
import ThMod_FnH.cards.Marisa.LuminesStrike;
import ThMod_FnH.cards.Marisa.MachineGunSpark;
import ThMod_FnH.cards.Marisa.MagicAbsorber;
import ThMod_FnH.cards.Marisa.MagicChant;
import ThMod_FnH.cards.Marisa.ManaConvection;
import ThMod_FnH.cards.Marisa.MasterSpark;
import ThMod_FnH.cards.Marisa.MaximisePower;
import ThMod_FnH.cards.Marisa.MeteoricShower;
import ThMod_FnH.cards.Marisa.MilkyWay;
import ThMod_FnH.cards.Marisa.MillisecondPulsars;
import ThMod_FnH.cards.Marisa.MoraleDepletion;
import ThMod_FnH.cards.Marisa.MysteriousBeam;
import ThMod_FnH.cards.Marisa.NonDirectionalLaser;
import ThMod_FnH.cards.Marisa.Occultation;
import ThMod_FnH.cards.Marisa.OortCloud;
import ThMod_FnH.cards.Marisa.OpenUniverse;
import ThMod_FnH.cards.Marisa.Orbital;
import ThMod_FnH.cards.Marisa.OrrerysSun;
import ThMod_FnH.cards.Marisa.PolarisUnique;
import ThMod_FnH.cards.Marisa.PowerUp;
import ThMod_FnH.cards.Marisa.PropBag;
import ThMod_FnH.cards.Marisa.PulseMagic;
import ThMod_FnH.cards.Marisa.RefractionSpark;
import ThMod_FnH.cards.Marisa.Robbery;
import ThMod_FnH.cards.Marisa.SatelliteIllusion;
import ThMod_FnH.cards.Marisa.ShootTheMoon;
import ThMod_FnH.cards.Marisa.ShootingEcho;
import ThMod_FnH.cards.Marisa.Singularity;
import ThMod_FnH.cards.Marisa.SporeBomb;
import ThMod_FnH.cards.Marisa.StarBarrage;
import ThMod_FnH.cards.Marisa.StarDustReverie;
import ThMod_FnH.cards.Marisa.StarlightTyphoon;
import ThMod_FnH.cards.Marisa.Strike_MRS;
import ThMod_FnH.cards.Marisa.SuperNova;
import ThMod_FnH.cards.Marisa.SuperPerseids;
import ThMod_FnH.cards.Marisa.TreasureHunter;
import ThMod_FnH.cards.Marisa.UltraShortWave;
import ThMod_FnH.cards.Marisa.UnstableBomb;
import ThMod_FnH.cards.Marisa.UpSweep;
import ThMod_FnH.cards.Marisa.WitchLeyline;
import ThMod_FnH.cards.Marisa.WitchOfGreed;
import ThMod_FnH.cards.Marisa._6A;
import ThMod_FnH.cards.Marisa.EarthLightRay;
import ThMod_FnH.cards.derivations.BlackFlareStar;
import ThMod_FnH.cards.derivations.GuidingStar;
import ThMod_FnH.cards.derivations.Spark;
import ThMod_FnH.cards.derivations.WhiteDwarf;
import ThMod_FnH.characters.Marisa;
import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.patches.ThModClassEnum;
import ThMod_FnH.powers.Marisa.GrandCrossPower;
import ThMod_FnH.relics.AmplifyWand;
import ThMod_FnH.relics.BreadOfAWashokuLover;
import ThMod_FnH.relics.EnhancedBroom;
import ThMod_FnH.relics.EnhancedHakkero;
import ThMod_FnH.relics.ExperimentalFamiliar;
import ThMod_FnH.relics.HandmadeGrimoire;
import ThMod_FnH.relics.MiniHakkero;
import ThMod_FnH.relics.RampagingMagicTools;
import ThMod_FnH.relics.ShroomBag;
import ThMod_FnH.relics.SimpleLauncher;
import ThMod_FnH.relics.SproutingBranch;
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

    logger.info("add " + ThModClassEnum.MARISA.toString());
    BaseMod.addCharacter(
        new Marisa("Marisa"),
        MY_CHARACTER_BUTTON,
        MARISA_PORTRAIT,
        ThModClassEnum.MARISA
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
    BaseMod.addCard(new FungusSplash());
    UnlockTracker.unlockCard("FungusSplash");
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
    // TODO Auto-generated method stub
    logger.info("ThMod : PostBattle");
  }

  @Override
  public void receiveCardUsed(AbstractCard card) {
    ThMod.logger.info("ThMod : Card used : " + card.cardID);
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

  @Override
  public void receiveEditKeywords() {
    logger.info("Setting up custom keywords");

    BaseMod.addKeyword(new String[]{"\u529b\u7aed"},
        "\u529b\u7aed\u4f1a\u4f7f\u4f60\u65e0\u6cd5\u83b7\u5f97\u6216\u4f7f\u7528 \u84c4\u529b \u3002");
    BaseMod.addKeyword(new String[]{"\u53d6\u51b3\u4e8e\u6240\u6d88\u8017\u5361\u7684\u79cd\u7c7b"},
        "\u653b\u51fb\uff1a\u6050\u60e7\u836f\u6c34\uff1b\u6280\u80fd\uff1a\u865a\u5f31\u836f\u6c34\uff1b\u80fd\u529b\uff1a\u6bd2\u7d20\u836f\u6c34\uff1b\u72b6\u6001\uff1a\u706b\u7130\u836f\u6c34\uff1b\u8bc5\u5492\uff1a\u70df\u96fe\u0020\u5f39\u0020\u3002");
    BaseMod.addKeyword(new String[]{"\u706b\u82b1"},
        "\u706b\u82b1\u662f\u4e00\u5f20\u6d88\u8017\u4e3a\u0030\u7684\u653b\u51fb\u724c");
    BaseMod.addKeyword(new String[]{"\u84c4\u529b"},
        "\u6bcf8\u5c42\u84c4\u529b\u4f1a\u4f7f\u4f60\u7684\u4e0b\u4e00\u6b21\u653b\u51fb\u4f24\u5bb3\u7ffb\u4e00\u500d\u3002");
    BaseMod.addKeyword(new String[]{"\u589e\u5e45"},
        "\u5f53\u4f60\u7684\u8d39\u7528\u8db3\u591f\u4f7f\u7528\u8fd9\u5f20\u724c\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u65f6\uff0c\u4f7f\u7528\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u3002");

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

    logger.info("Keywords setting finished.");
  }

  @Override
  public void receiveEditStrings() {
    logger.info("start editing strings");

    String relicStrings, cardStrings, powerStrings;

    if (Settings.language == Settings.GameLanguage.ZHS) {
      logger.info("lang == zhs");

      relicStrings = Gdx.files.internal("localization/ThMod_Fnh_relics-zh.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

      cardStrings = Gdx.files.internal("localization/ThMod_Fnh_cards-zh.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

      powerStrings = Gdx.files.internal("localization/ThMod_Fnh_powers-zh.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    } else {
      logger.info("lang == eng");

      relicStrings = Gdx.files.internal("localization/ThMod_Fnh_relics.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

      cardStrings = Gdx.files.internal("localization/ThMod_Fnh_cards.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

      powerStrings = Gdx.files.internal("localization/ThMod_Fnh_powers.json")
          .readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    }
    logger.info("done editing strings");
  }

  @Override
  public void receivePostInitialize() {
    // TODO Auto-generated method stub
    BaseMod.addEvent(Mushrooms_MRS.ID, Mushrooms_MRS.class, Exordium.ID);
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
	

