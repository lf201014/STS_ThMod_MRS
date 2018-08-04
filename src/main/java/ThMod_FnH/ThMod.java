package ThMod_FnH;

import com.badlogic.gdx.Gdx;
//cd:E:\STSmod worktable\example_mod
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.core.Settings.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

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
import ThMod_FnH.characters.*;
import ThMod_FnH.action.GrandCrossAction;
import ThMod_FnH.cards.Marisa.*;
import ThMod_FnH.cards.special.*;
import ThMod_FnH.patches.*;
import ThMod_FnH.relics.*;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ThMod implements PostExhaustSubscriber,
	PostBattleSubscriber, PostDungeonInitializeSubscriber,
	EditCharactersSubscriber, PostInitializeSubscriber, 
	EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, 
	OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber {
	
	public static final Logger logger = LogManager.getLogger(ThMod.class.getName());
	 
	//card backgrounds
	private static final String ATTACK_CC = "512/bg_attack.png";
	private static final String SKILL_CC = "512/bg_attack.png";
	private static final String POWER_CC = "512/bg_attack.png";
	private static final String ENERGY_ORB_CC = "512/card_orb.png";
  
	private static final String ATTACK_CC_PORTRAIT = "1024/bg_attack.png";
	private static final String SKILL_CC_PORTRAIT = "1024/bg_attack.png";
  	private static final String POWER_CC_PORTRAIT = "1024/bg_attack.png";
  	private static final String ENERGY_ORB_CC_PORTRAIT = "1024/card_orb.png";
  
  	private static final String ASSETS_FOLDER = "img";
  
  	private static final String FPC_RELIC = "relics/blueberries.png";

  	private static final Color STARLIGHT = CardHelper.getColor(0f, 0f, 255.0f);
 
  	private static final String MY_CHARACTER_BUTTON = "img/charSelect/testButton.png";
	

  	private static final String MARISA_PORTRAIT = "img/charSelect/marisaPortrait.jpg";
	
	public static Texture getFPCRelicTexture() {
		return new Texture(makePath(FPC_RELIC));
	}
	
	/**
   * Makes a full path for a resource path
   * @param resource the resource, must *NOT* have a leading "/"
   * @return the full path
   */
	public static final String makePath(String resource) {
	  return ASSETS_FOLDER + "/" + resource;
  	}
	//For Generate random Card Rarity
	public static CardRarity RollRarity () {
		double rd = Math.random() * 75;
		if (rd <= 20)
			return CardRarity.COMMON;
		if (rd >= 55)
			return CardRarity.RARE;
		return CardRarity.UNCOMMON;
	}
	//For Amplify cards
	public static boolean Amplified(int COS,int AMP) {
		AbstractPlayer p = AbstractDungeon.player;
		if (p.hasPower("MoraleDepletionPower"))
			return false;
		boolean res=false;
		if ((p.hasPower("MilliPulsaPower"))||(p.hasPower("PulseMagicPower"))) {
			res = true;
		} else
		if  (EnergyPanel.totalCount >=COS ){
			AbstractDungeon.actionManager.addToBottom(
					new GainEnergyAction(-AMP)
					);
			res = true;
		}
		if (res) {
			AbstractDungeon.actionManager.addToTop(
					new GrandCrossAction()
					);
			if (p.hasPower("EventHorizonPower"))
				p.getPower("EventHorizonPower").onSpecificTrigger();
			if (p.hasRelic("AMDumbbell")) {
				AbstractRelic r = p.getRelic("AMDumbbell");
				AbstractDungeon.actionManager.addToTop(
						new RelicAboveCreatureAction(AbstractDungeon.player, r) 
						);
				AbstractDungeon.actionManager.addToTop(
						new ApplyPowerAction(
								p, p,
								new StrengthPower(p, 1),
								1)
						);
			}
		}
			
		return res;
	}
	
	public ThMod() {

      BaseMod.subscribe(this);

      logger.info("creating the color : MARISA_COLOR");
      BaseMod.addColor(AbstractCardEnum.MARISA_COLOR.toString(),
    		  	STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT,
        	    makePath(ATTACK_CC), makePath(SKILL_CC),
        	    makePath(POWER_CC), makePath(ENERGY_ORB_CC),
        	    makePath(ATTACK_CC_PORTRAIT), makePath(SKILL_CC_PORTRAIT),
        	    makePath(POWER_CC_PORTRAIT), makePath(ENERGY_ORB_CC_PORTRAIT));
		
	}

	public void receiveEditCharacters() {
		logger.info("begin editting characters");
				
		logger.info("add " + ThModClassEnum.MARISA.toString());
		
		if (Settings.language == Settings.GameLanguage.ZHS) {
			BaseMod.addCharacter(Marisa.class, "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f", "character class string",
					AbstractCardEnum.MARISA_COLOR.toString(), "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f",
					MY_CHARACTER_BUTTON , MARISA_PORTRAIT,
					ThModClassEnum.MARISA.toString());
		}
        else {
        	BaseMod.addCharacter(Marisa.class, "The Ordinary Magician", "character class string",
				AbstractCardEnum.MARISA_COLOR.toString(), "The Ordinary Magician",
				MY_CHARACTER_BUTTON , MARISA_PORTRAIT,
				ThModClassEnum.MARISA.toString());
        }
		logger.info("done editting characters");
	}
	
	public void receiveEditRelics() {
		logger.info("Begin editting relics.");
  	
		BaseMod.addRelicToCustomPool(new MiniHakkero(), AbstractCardEnum.MARISA_COLOR.toString());
		BaseMod.addRelicToCustomPool(new EnhancedHakkero(), AbstractCardEnum.MARISA_COLOR.toString());
		BaseMod.addRelicToCustomPool(new EnhancedBroom(), AbstractCardEnum.MARISA_COLOR.toString());
		BaseMod.addRelicToCustomPool(new MagicArmor(), AbstractCardEnum.MARISA_COLOR.toString());
		BaseMod.addRelicToCustomPool(new AMDumbbell(), AbstractCardEnum.MARISA_COLOR.toString());
  	
		logger.info("Relics editting finished.");
  	}
	
	public void receiveEditCards() {
		logger.info("begin editting cards");

		logger.info("add cards for MARISA");
		//starter£º4
		BaseMod.addCard(new Strike_MRS());
		UnlockTracker.unlockCard("Strike_MRS");
		BaseMod.addCard(new Defend_MRS());
		UnlockTracker.unlockCard("Defend_MRS");
		BaseMod.addCard(new MasterSpark());
		UnlockTracker.unlockCard("MasterSpark");
		BaseMod.addCard(new UpSweep());
		UnlockTracker.unlockCard("UpSweep");
		//attack£º30
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
		BaseMod.addCard(new FluorensentBeam());
		UnlockTracker.unlockCard("FluorensentBeam");
		BaseMod.addCard(new _6A());
		UnlockTracker.unlockCard("6A");
		BaseMod.addCard(new UnstableBomb());
		UnlockTracker.unlockCard("UnstableBomb");
		BaseMod.addCard(new StarBarrage());
		UnlockTracker.unlockCard("StarBarrage");
		//Uncommon: 13
		BaseMod.addCard(new MachineGunSpark());
		UnlockTracker.unlockCard("MachineGunSpark");
		BaseMod.addCard(new DarkSpark());
		UnlockTracker.unlockCard("DarkSpark");
		BaseMod.addCard(new DeepEcologicalBomb());
		UnlockTracker.unlockCard("DeepEcologicalBomb");
		BaseMod.addCard(new MeteonicShower());
		UnlockTracker.unlockCard("MeteonicShower");
		BaseMod.addCard(new GravityBeat());
		UnlockTracker.unlockCard("GravityBeat");
		BaseMod.addCard(new GrandCross());
		UnlockTracker.unlockCard("GrandCross");
		BaseMod.addCard(new DragonMeteor());
		UnlockTracker.unlockCard("DragonMeteor");
		BaseMod.addCard(new PulseMagic());
		UnlockTracker.unlockCard("PulseMagic");
		BaseMod.addCard(new ShootingEcho());
		UnlockTracker.unlockCard("ShootingEcho");
		BaseMod.addCard(new MuscleSpark());
		UnlockTracker.unlockCard("MuscleSpark");
		BaseMod.addCard(new Robbery());
		UnlockTracker.unlockCard("Robbery");
		BaseMod.addCard(new ChargeUpSpray());
		UnlockTracker.unlockCard("ChargeUpSpray");
		BaseMod.addCard(new AFriendsGift());
		UnlockTracker.unlockCard("AFriendsGift");
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
		
		//skill£º28
		//Common : 7
		BaseMod.addCard(new MilkyWay());
		UnlockTracker.unlockCard("MilkyWay");
		BaseMod.addCard(new AsteroidBelt());
		UnlockTracker.unlockCard("AsteroidBelt");
		BaseMod.addCard(new PowerUp());
		UnlockTracker.unlockCard("PowerUp");
		BaseMod.addCard(new SporeBomb());
		UnlockTracker.unlockCard("SporeBomb");
		BaseMod.addCard(new GasGiant());
		UnlockTracker.unlockCard("GasGiant");
		BaseMod.addCard(new IllusionStar());
		UnlockTracker.unlockCard("IllusionStar");
		BaseMod.addCard(new EnergyRecoil());
		UnlockTracker.unlockCard("EnergyRecoil");
		//Uncommon : 14
		BaseMod.addCard(new StarDustReverie());
		UnlockTracker.unlockCard("StarDustReverie");
		BaseMod.addCard(new MagicAbsorber());
		UnlockTracker.unlockCard("MagicAbsorber");
		BaseMod.addCard(new Occultation());
		UnlockTracker.unlockCard("Occultation");
		BaseMod.addCard(new EarthLightRay());
		UnlockTracker.unlockCard("EarthLightRay");
		BaseMod.addCard(new SuperPerseids());
		UnlockTracker.unlockCard("SuperPerseids");
		BaseMod.addCard(new BlazeAway());
		UnlockTracker.unlockCard("BlazeAway");
		BaseMod.addCard(new ChargingUp());
		UnlockTracker.unlockCard("ChargingUp");
		BaseMod.addCard(new CircumpolarStar());
		UnlockTracker.unlockCard("CircumpolarStar");
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
		
		//power£º13
		//common:1
		BaseMod.addCard(new WitchOfGreed());
		UnlockTracker.unlockCard("WitchOfGreed");
		//uncommon: 8
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
		BaseMod.addCard(new Singualrity());
		UnlockTracker.unlockCard("Singualrity");
		BaseMod.addCard(new CasketOfStar());
		UnlockTracker.unlockCard("CasketOfStar");
		BaseMod.addCard(new GalacticHalo());
		UnlockTracker.unlockCard("GalacticHalo");
		//rare: 4
		BaseMod.addCard(new PolarisUnique());
		UnlockTracker.unlockCard("PolarisUnique");
		BaseMod.addCard(new EscapeVelocity());
		UnlockTracker.unlockCard("EscapeVelocity");
		BaseMod.addCard(new MillisecondPulsars());
		UnlockTracker.unlockCard("MillisecondPulsars");
		BaseMod.addCard(new SuperNova());
		UnlockTracker.unlockCard("SuperNova");
		
		//special£º5
		BaseMod.addCard(new Spark());
		UnlockTracker.unlockCard("Spark");
		BaseMod.addCard(new GuidingStar());
		UnlockTracker.unlockCard("GuidingStar");
		BaseMod.addCard(new Burn_MRS());
		UnlockTracker.unlockCard("Burn_MRS");
		BaseMod.addCard(new BlackFlareStar());
		UnlockTracker.unlockCard("BlackFlareStar");
		BaseMod.addCard(new WhiteDwarf());
		UnlockTracker.unlockCard("WhiteDwarf");
		
		logger.info("done editting cards");
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
		
		BaseMod.addKeyword(new String[] {"\u53d6\u51b3\u4e8e\u6240\u6d88\u8017\u5361\u7684\u79cd\u7c7b"},
				"\u653b\u51fb\uff1a\u6bd2\u7d20\u836f\u6c34\uff1b\u6280\u80fd\uff1a\u865a\u5f31\u836f\u6c34\uff1b\u80fd\u529b\uff1a\u6050\u60e7\u836f\u6c34\uff1b\u72b6\u6001\uff1a\u7130\u836f\u6c34\uff1b\u8bc5\u5492\uff1a\u70df\u96fe\u0020\u5f39\u0020\u3002");
		BaseMod.addKeyword(new String[] {"\u706b\u82b1"},
				"\u706b\u82b1\u662f\u4e00\u5f20\u6d88\u8017\u4e3a\u0030\u7684\u653b\u51fb\u724c");
		BaseMod.addKeyword(new String[] {"\u84c4\u529b"},
				"\u6bcf8\u5c42\u84c4\u529b\u4f1a\u4f7f\u4f60\u7684\u4e0b\u4e00\u6b21\u653b\u51fb\u4f24\u5bb3\u7ffb\u4e00\u500d\u3002");
		BaseMod.addKeyword(new String[] {"\u589e\u5e45"},
				"\u5f53\u4f60\u7684\u8d39\u7528\u8db3\u591f\u4f7f\u7528\u8fd9\u5f20\u724c\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u65f6\uff0c\u4f7f\u7528\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u3002");
		BaseMod.addKeyword(new String[] {"amplify", "Amplify"},
				"Pay extra energy for its effect when you have enough  [B] .");
		BaseMod.addKeyword(new String[] {"Spark","spark"},
				"Spark is an attack card cost 0 energy.");
		BaseMod.addKeyword(new String[] {"Charge-up", "charge-up","chargeup","ChargeUp"},
				"For every 8 stacks,double your damage.");
		BaseMod.addKeyword(new String[] {
				"depends on the type of the card exhausted",
				"Depends On The Type Of The Card Exhausted"
				}, "Attack : Poison Potion ; NL Skill : Weak Potion ; NL Power : Fear Potion ; Status : Fire Potion ; Curse : Smoke Bomb .");
		logger.info("Keywords setting finished.");
	}

	@Override
	public void receivePowersModified() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void receiveCardUsed(AbstractCard arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveEditStrings() {
		// TODO Auto-generated method stublogger.info("begin editing strings");
		logger.info("start editing strings");
		
		String relicStrings,cardStrings,powerStrings;
		
        if (Settings.language == Settings.GameLanguage.ZHS) {
        	logger.info("lang == zhs");
        	
            relicStrings = Gdx.files.internal("localization/ThMod_Fnh_relics-zh.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)RelicStrings.class, relicStrings);
            
            cardStrings = Gdx.files.internal("localization/ThMod_Fnh_cards-zh.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)CardStrings.class, cardStrings);
            
            powerStrings = Gdx.files.internal("localization/ThMod_Fnh_powers-zh.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)PowerStrings.class, powerStrings);
        }
        else {
        	logger.info("lang == eng");
        	
            relicStrings = Gdx.files.internal("localization/ThMod_Fnh_relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)RelicStrings.class, relicStrings);
            
            cardStrings = Gdx.files.internal("localization/ThMod_Fnh_cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)CardStrings.class, cardStrings);
            
            powerStrings = Gdx.files.internal("localization/ThMod_Fnh_powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
            BaseMod.loadCustomStrings((Class)PowerStrings.class, powerStrings);
        }

        logger.info(("relics :"+ relicStrings.length()));
        logger.info(("powers :"+ cardStrings.length()));
        logger.info(("cards :"+ powerStrings.length()));

        logger.info("done editting strings");
	}

	@Override
	public void receivePostInitialize() {
		// TODO Auto-generated method stub
		
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
	

