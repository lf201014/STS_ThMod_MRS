package ThMod_FnH.patches;

/*BaseMod图像优化补丁
 * 说明：BaseMod使用的图片加载功能比原作游戏里漏了个滤镜，导致低分辨率下的图片显示被压缩，产生锯齿状效果。
 * 		本补丁为其添加了相应滤镜以消除锯齿。
 * 		补丁尚未完善，目前涉及的修复范围：角色选择界面，卡牌上各种图片元素，游戏内能量计数器。
 * 		如有发现其他显示锯齿问题，请联系作者（QQ:1315659893)。
 * 		对于Texture类型的锯齿问题，也可直接使用 ImageMaster.loadImage(<String 图片地址>)方式解决。
 * 使用方法：将该文件加入你做的mod的源代码中（包路径随意安排一下）即可
 * 注意事项：不建议游戏中同时开启多个使用了该补丁的mod
 * 
 * 最近一次更新内容：增加一个批量修复。解决8.3BaseMod的卡牌显示锯齿问题
 */

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomPlayer;

public class TextureFilterPatch {
	
	
	
	  @SpirePatch(cls="basemod.abstracts.CustomPlayer", 
			  method="buildCustomOrb"
	  )
	
	public static class buildCustomOrbFix{
		
		  @SpireInsertPatch(
					rloc=5,
					localvars={"energyLayers","orbVfx"}
				)
		  
		  public static void Insert(CustomPlayer _inst, String[] orbTextures, String orbVfxPath, ArrayList<Texture> energyLayers, Texture orbVfx) {
			  
			  for(Texture t : energyLayers) {
				  t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			  }
			  orbVfx.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);			  
		  }		  
	}
	
	  @SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption", 
			  method="updateHitbox"
	  )
	  
	  public static class UpdateHitboxBgImgFix{

		  @SpireInsertPatch(
					rloc=40
				)
		  
		  public static void Insert(CharacterOption _inst) {
			  CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
		  }
		  
	  }
	  
	  @SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption", 
			  method="ctor", 
			  paramtypes={"java.lang.String", "com.megacrit.cardcrawl.characters.AbstractPlayer$PlayerClass", "java.lang.String", "java.lang.String"}
	  )
	  
	  public static class ButtonImageFix{
		  @SpireInsertPatch(
					rloc=32
				)
		  
		  public static void Insert(Object __obj_instance, String optionName, Object cObj, String buttonUrl, String portraiImg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
		  {
		        Field buttonImgField = CharacterOption.class.getDeclaredField("buttonImg");
		        buttonImgField.setAccessible(true);
		        ((Texture)buttonImgField.get(__obj_instance)).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
		  }
	  }
	  
	  @SpirePatch(cls="basemod.abstracts.CustomCard", 
			  method="loadTextureFromString"
	  )
	  
	  public static class loadTextureFromStringFix{
		  
		  /*
		  @SpireInsertPatch(
					rloc=2,
					localvars={"imgMap"}
				)
				
		  
		  public static void Insert(String textureString, HashMap<String, Texture> imgMap) {
			  imgMap.get(textureString).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
			  */
		  
		  public static void Postfix(String textureString) {
			  Texture t = CustomCard.imgMap.get(textureString);
			  if(t==null)return;
			  t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			  CustomCard.imgMap.put(textureString, t);
		}
		  
	  }
	  
	  @SpirePatch(cls="basemod.abstracts.CustomCard", 
			  method="getPortraitImage"
	  )
	  
	  public static class getPortraitImageFix{
		  
		  @SpireInsertPatch(
					rloc=13,
					localvars={"portraitTexture"}
				)
		  
		  public static void Insert(CustomCard card, Texture portraitTexture) {
			  if(portraitTexture==null)return;
			  portraitTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
		  }
	  }
	  
	  @SpirePatch(cls="basemod.BaseMod", 
			  method="saveEnergyOrbTexture"
	  )
	  
	  public static class saveEnergyOrbFix{
		  @SpireInsertPatch(
					rloc=0
				)
		  
		  public static void Insert(final String color, final Texture tex) {
			  tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
		  }
	  }
	  
	  @SpirePatch(cls="basemod.BaseMod", 
			  method="saveEnergyOrbPortraitTexture"
	  )
	  
	  public static class saveEnergyOrbPortraitFix{
		  @SpireInsertPatch(
					rloc=0
				)
		  
		  public static void Insert(final String color, final Texture tex) {
			  tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);	
		  }
	  }
	 
}
