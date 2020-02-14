package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.DuplicationPower;

public class StupidDupliPotionPatch {
  @SpirePatch(clz = DuplicationPower.class, method = "onUseCard")
  public static class CapeGoldRewardPatch {

    @SpireInsertPatch(rloc = 15,localvars = {"tmp"})
    public static SpireReturn insert(DuplicationPower _inst, AbstractCard card, UseCardAction action,AbstractCard tmp) {
      tmp.applyPowers();
      return SpireReturn.Continue();
    }

  }
}
