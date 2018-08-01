package ThMod_FnH.characters;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.ThModClassEnum;

public class Marisa extends CustomPlayer {
	
	public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
	public static final String MY_CHARACTER_SHOULDER_2 = "img/char/MyCharacter/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "img/char/MyCharacter/shoulder.png"; // another campfire pose
	public static final String MY_CHARACTER_CORPSE = "img/char/MyCharacter/corpse.png"; // dead corpse
	public static final Logger logger = LogManager.getLogger(ThMod.class.getName());
	//private static final float[] layerSpeeds = { 20.0F, 0.0F, -40.0F, 0.0F, 0.0F, 5.0F, 0.0F, -8.0F, 0.0F, 8.0F };
    //public static final String MY_CHARACTER_SKELETON_ATLAS = "img/char/MyCharacter/idle/skeleton.atlas"; // spine animation atlas
    //public static final String MY_CHARACTER_SKELETON_JSON = "img/char/MyCharacter/idle/skeleton.json"; // spine animation json
    public static final String SPRITER_ANIM_FILEPATH = "img/char/MyCharacter/marisa_test.scml"; // spriter animation scml

	public Marisa (String name, PlayerClass setClass) {
		super(name, setClass, null, null , null ,new SpriterAnimation(SPRITER_ANIM_FILEPATH));
		//public CustomPlayer(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, AbstractAnimation animation)
		
		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
		
		logger.info("init Marisa");
		
		initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
				MY_CHARACTER_SHOULDER_1,
				MY_CHARACTER_CORPSE, 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		
		//loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); 
		// if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
		//AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
		logger.info("init finish");
		
	}

	public static ArrayList<String> getStartingDeck() { // starting deck 'nuff said
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
	
	public static ArrayList<String> getStartingRelics() { // starting relics - also simple
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("MiniHakkero");
		UnlockTracker.markRelicAsSeen("MiniHakkero");
		return retVal;
	}
	
        public static final int STARTING_HP = 75;
        public static final int MAX_HP = 75;
        public static final int STARTING_GOLD = 99;
        public static final int HAND_SIZE = 5;

	public static CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
		String title;
		String flavor;
		if (Settings.language == Settings.GameLanguage.ZHS) {
			title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
			flavor = "\u4f4f\u5728\u9b54\u6cd5\u68ee\u6797\u7684\u9b54\u6cd5\u4f7f\u3002 NL \u5584\u957f\u4e8e\u5149\u548c\u70ed\u7684\u9b54\u6cd5\u3002 \u203b\u0020\u8bf7\u4e0d\u8981\u79fb\u9664\u521d\u59cb\u9057\u7269";
		}else {
			title = "The Ordinary Magician";
			flavor = "The 'ordinay' magician lives in the magic forest. NL Specialized in light and heat magic. \u203b Please Don't remove the Starter Relic.";
		}
		return new CharSelectInfo(
				title, flavor,
				STARTING_HP, MAX_HP,0, STARTING_GOLD, HAND_SIZE,
			 ThModClassEnum.MARISA, getStartingRelics(), getStartingDeck(), false);
	}
	
}