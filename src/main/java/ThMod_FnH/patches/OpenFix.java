package ThMod_FnH.patches;

import java.lang.reflect.Field;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import basemod.abstracts.CustomCard;

public class OpenFix
{
  
  @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="open",
		  paramtypes={"com.megacrit.cardcrawl.cards.AbstractCard"})
  public static class OpenTextureFix
  {
    public static void Postfix(Object __obj_instance, Object cardObj)
    {
      AbstractCard card = (AbstractCard)cardObj;
      SingleCardViewPopup popup = (SingleCardViewPopup)__obj_instance;
      try
      {
        Field portraitImageField = popup.getClass().getDeclaredField("portraitImg");
        portraitImageField.setAccessible(true);
        if ((portraitImageField.get(popup) == null) && ((card instanceof CustomCard))) {
          portraitImageField.set(popup, CustomCard.getPortraitImage((CustomCard)card));
        }
      }
      catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
      {
        e.printStackTrace();
      }
    }
  }
}
