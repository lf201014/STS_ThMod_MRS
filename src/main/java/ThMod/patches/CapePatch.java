package ThMod.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class CapePatch {
/*
  @SpirePatch(clz = RewardItem.class, method = "applyGoldBonus",paramtypez = {Boolean.class})
  public static class CapeGoldRewardPatch {

    @SpireInsertPatch(rloc = 11)
    public static SpireReturn insert(RewardItem _inst, Boolean _isTheft) {
      if (AbstractDungeon.player.hasRelic("MarisaMod:Cape")) {
        _inst.bonusGold += MathUtils.round(_inst.goldAmt * 1.0F);
      }

      return SpireReturn.Return(null);
    }

  }
*/
}
