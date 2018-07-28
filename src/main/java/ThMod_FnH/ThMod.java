package ThMod_FnH;

import com.badlogic.gdx.Gdx;
//cd:E:\STSmod worktable\example_mod
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.core.Settings.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
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
	
	//For Amplify cards
	public static boolean Amplified(int AMP,int COS) {
		boolean res=false;
		if (AbstractDungeon.player.hasPower("MilliPulsaPower")) {
			res = true;
		} else
		if  (EnergyPanel.totalCount >=AMP ){
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(-COS));
			res = true;
		}
		if (res)
			AbstractDungeon.actionManager.addToTop(new GrandCrossAction());
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
		//attack£º18
		//Common: 5
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
		BaseMod.addCard(new ShootingEcho());
		UnlockTracker.unlockCard("ShootingEcho");
		//Uncommon: 9
		BaseMod.addCard(new MachineGunSpark());
		UnlockTracker.unlockCard("MachineGunSpark");
		BaseMod.addCard(new DarkSpark());
		UnlockTracker.unlockCard("DarkSpark");
		BaseMod.addCard(new BlazingStar());
		UnlockTracker.unlockCard("BlazingStar");
		BaseMod.addCard(new ShootTheMoon());
		UnlockTracker.unlockCard("ShootTheMoon");
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
		//Rare:  3
		BaseMod.addCard(new FinalSpark());
		UnlockTracker.unlockCard("FinalSpark");
		BaseMod.addCard(new JA());
		UnlockTracker.unlockCard("JA");
		BaseMod.addCard(new AbsoluteMagnitude());
		UnlockTracker.unlockCard("AbsoluteMagnitude");
		
		//skill£º17
		//Common : 4
		BaseMod.addCard(new MilkyWay());
		UnlockTracker.unlockCard("MilkyWay");
		BaseMod.addCard(new AsteroidBelt());
		UnlockTracker.unlockCard("AsteroidBelt");
		BaseMod.addCard(new ChargingUp());
		UnlockTracker.unlockCard("ChargingUp");
		BaseMod.addCard(new PowerUp());
		UnlockTracker.unlockCard("PowerUp");
		//Uncommon : 9
		BaseMod.addCard(new StarDustReverie());
		UnlockTracker.unlockCard("StarDustReverie");
		BaseMod.addCard(new MagicAbsorber());
		UnlockTracker.unlockCard("MagicAbsorber");
		BaseMod.addCard(new UltraShortWave());
		UnlockTracker.unlockCard("UltraShortWave");
		BaseMod.addCard(new Occultation());
		UnlockTracker.unlockCard("Occultation");
		BaseMod.addCard(new EarthLightRay());
		UnlockTracker.unlockCard("EarthLightRay");
		BaseMod.addCard(new SuperPerseids());
		UnlockTracker.unlockCard("SuperPerseids");
		BaseMod.addCard(new BlazeAway());
		UnlockTracker.unlockCard("BlazeAway");
		BaseMod.addCard(new IllusionStar());
		UnlockTracker.unlockCard("IllusionStar");
		BaseMod.addCard(new EnergyFlow());
		UnlockTracker.unlockCard("EnergyFlow");
		//Rare : 4
		BaseMod.addCard(new BigCrunch());
		UnlockTracker.unlockCard("BigCrunch");
		BaseMod.addCard(new OpenUniverse());
		UnlockTracker.unlockCard("OpenUniverse");
		BaseMod.addCard(new StarlightTyphoon());
		UnlockTracker.unlockCard("StarlightTyphoon");
		BaseMod.addCard(new MaximisePower());
		UnlockTracker.unlockCard("MaximisePower");
		
		//power£º7
		//common:
		//uncommon: 4
		BaseMod.addCard(new SatelliteIllusion());
		UnlockTracker.unlockCard("SatelliteIllusion");
		BaseMod.addCard(new OortCloud());
		UnlockTracker.unlockCard("OortCloud");
		BaseMod.addCard(new OrrerysSun());
		UnlockTracker.unlockCard("OrrerysSun");
		BaseMod.addCard(new EnergyFlow());
		UnlockTracker.unlockCard("EnergyFlow");
		//BaseMod.addCard(new EventHorizon());
		//UnlockTracker.unlockCard("EventHorizon");
		//rare: 3
		BaseMod.addCard(new PolarisUnique());
		UnlockTracker.unlockCard("PolarisUnique");
		BaseMod.addCard(new EscapeVelocity());
		UnlockTracker.unlockCard("EscapeVelocity");
		BaseMod.addCard(new MillisecondPulsars());
		UnlockTracker.unlockCard("MillisecondPulsars");
		
		//special£º2
		BaseMod.addCard(new Spark());
		UnlockTracker.unlockCard("Spark");
		BaseMod.addCard(new GuidingStar());
		UnlockTracker.unlockCard("GuidingStar");
		
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
		BaseMod.addKeyword(new String[] {"\u706b\u82b1"},"\u706b\u82b1\u662f\u4e00\u5f20\u6d88\u8017\u4e3a\u0030\u7684\u653b\u51fb\u724c");
		BaseMod.addKeyword(new String[] {"\u84c4\u529b"},"\u6bcf\u0036\u5c42\u84c4\u529b\u4f1a\u4f7f\u4f60\u7684\u4e0b\u4e00\u6b21\u653b\u51fb\u4f24\u5bb3\u7ffb\u4e00\u500d\u3002");
		BaseMod.addKeyword(new String[] {"\u53cd\u566c"}, "\u6218\u6597\u7ed3\u675f\u65f6\u53d7\u5230\u5bf9\u5e94\u5c42\u6570\u7684\u4f24\u5bb3\u3002");
		BaseMod.addKeyword(new String[] {"\u589e\u5e45"}, "\u5f53\u4f60\u7684\u8d39\u7528\u8db3\u591f\u4f7f\u7528\u8fd9\u5f20\u724c\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u65f6\uff0c\u4f7f\u7528\u66f4\u9ad8\u7ea7\u7684\u6548\u679c\u3002");
		BaseMod.addKeyword(new String[] {"amplify", "Amplify"}, "Pay extra energy for its effect when you have enough  [B] .");
		BaseMod.addKeyword(new String[] {"Spark","spark"},"Spark is an attack card cost 0 energy.");
		BaseMod.addKeyword(new String[] {"Charge-up", "charge-up","chargeup","Chargeup"}, "For every 6 stacks,double your damage once.");
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
}
	

