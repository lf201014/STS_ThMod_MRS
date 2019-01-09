package ThMod.patches;

import java.util.regex.*;
import org.apache.logging.log4j.*;
import com.badlogic.gdx.graphics.*;
import basemod.*;
import com.megacrit.cardcrawl.core.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.*;
import javassist.*;
import javassist.expr.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.helpers.*;

public class EnergyTextPatch {

  private static Pattern r;
  public static final Logger logger;

  static {
    EnergyTextPatch.r = Pattern.compile("\\[([RGBE])\\](\\.?) ");
    logger = LogManager.getLogger((Class) EnergyTextPatch.class);
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "renderDescriptionCN")
  public static class RenderSmallEnergyOrb {

    private static final float CARD_ENERGY_IMG_WIDTH;

    @SpireInsertPatch(rloc = 135, localvars = {"spacing", "i", "start_x", "draw_y", "font",
        "textColor", "tmp", "gl"})
    public static void Insert(final AbstractCard __instance, final SpriteBatch sb,
        final float spacing, final int i, @ByRef final float[] start_x, final float draw_y,
        final BitmapFont font, final Color textColor, @ByRef final String[] tmp,
        final GlyphLayout gl) {
      if (tmp[0].equals("[E]")) {
        gl.width = RenderSmallEnergyOrb.CARD_ENERGY_IMG_WIDTH * __instance.drawScale;
        final float tmp2 = (__instance.description.size() - 4.0f) * spacing;
        __instance.renderSmallEnergy(sb, BaseMod.getCardSmallEnergy(__instance),
            (start_x[0] - __instance.current_x) / Settings.scale / __instance.drawScale,
            -100.0f - ((__instance.description.size() - 4.0f) / 2.0f - i + 1.0f) * spacing);
        final int n = 0;
        start_x[n] += gl.width;
        tmp[0] = "";
      }
    }

    static {
      CARD_ENERGY_IMG_WIDTH = 24.0f * Settings.scale;
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "initializeDescriptionCN")
  public static class AlterEnergyKeyword {

    static float CARD_ENERGY_IMG_WIDTH;
    static float CN_DESC_BOX_WIDTH;

    @SpireInsertPatch(rloc = 114, localvars = {"word", "currentWidth", "numLines", "currentLine"})
    public static void Insert(final AbstractCard _inst, @ByRef final String[] word,
        @ByRef final float[] currentWidth, @ByRef final int[] numLines,
        @ByRef final StringBuilder[] currentLine) {
      if (word[0].equals("[E]")) {
        if (!_inst.keywords.contains("[R]")) {
          _inst.keywords.add("[R]");
        }
        if (currentWidth[0] + AlterEnergyKeyword.CARD_ENERGY_IMG_WIDTH
            > AlterEnergyKeyword.CN_DESC_BOX_WIDTH) {
          final int n = 0;
          ++numLines[n];
          _inst.description.add(new DescriptionLine(currentLine[0].toString(), currentWidth[0]));
          currentLine[0] = new StringBuilder("");
          currentWidth[0] = AlterEnergyKeyword.CARD_ENERGY_IMG_WIDTH;
          currentLine[0].append(" ").append(word[0]).append(" ");
        } else {
          currentLine[0].append(" ").append(word[0]).append(" ");
          final int n2 = 0;
          currentWidth[n2] += AlterEnergyKeyword.CARD_ENERGY_IMG_WIDTH;
        }
        word[0] = "";
      }
    }

    static {
      AlterEnergyKeyword.CARD_ENERGY_IMG_WIDTH = 24.0f * Settings.scale;
      AlterEnergyKeyword.CN_DESC_BOX_WIDTH = AbstractCard.IMG_WIDTH * 0.72f;
    }
  }

  @SpirePatch(cls = "com.megacrit.cardcrawl.screens.SingleCardViewPopup", method = "renderDescriptionCN")
  public static class RenderDescriptionEnergy {

    public static ExprEditor Instrument() {
      return new ExprEditor() {
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getClassName().equals("java.lang.String") && m.getMethodName().equals("equals")) {
            m.replace(
                "{ $_ = thmod.patches.EnergyTextPatch.RenderDescriptionEnergy.replaceEquals(tmp, (java.lang.String)$1); }");
          }
        }

        public void edit(final FieldAccess m) throws CannotCompileException {
          if (m.getClassName().equals("com.megacrit.cardcrawl.helpers.ImageMaster") && m
              .getFieldName().equals("GREEN_ORB")) {
            m.replace(
                "{ $_ = thmod.patches.EnergyTextPatch.RenderDescriptionEnergy.replaceOrbField(tmp, this.card); }");
          }
        }
      };
    }

    public static boolean replaceEquals(final String tmp, final String originalArg) {
      return tmp.equals(originalArg) || (tmp.equals("[E]") && originalArg.equals("[G]"));
    }

    public static TextureAtlas.AtlasRegion replaceOrbField(final String tmp, final Object card) {
      if (tmp.equals("[E]")) {
        return BaseMod.getCardSmallEnergy((AbstractCard) card);
      }
      return ImageMaster.GREEN_ORB;
    }
  }
}

