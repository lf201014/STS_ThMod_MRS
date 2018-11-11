package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ParasitePatch {

  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Parasite", method = "use")
  public static class ParasiteUse {

    @SuppressWarnings("rawtypes")
    @SpirePrefixPatch
    public static SpireReturn Prefix(
        AbstractCard _inst,
        AbstractPlayer p,
        AbstractMonster m
    ) {
      if (AbstractDungeon.player.hasRelic("ShroomBag")) {
        AbstractRelic r = p.getRelic("ShroomBag");

        r.flash();
        AbstractDungeon.actionManager.addToBottom(
            new RelicAboveCreatureAction(p, r)
        );
        _inst.exhaust = true;
        AbstractDungeon.actionManager.addToBottom(
            new HealAction(p, p, 3)
        );
        return SpireReturn.Return(null);
      }
      return SpireReturn.Continue();
    }
  }


  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "canUse")
  public static class ParasiteCanUse {

    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix(
        AbstractCard _inst,
        AbstractPlayer p,
        AbstractMonster m
    ) {
      if ((_inst.cardID.equals("Parasite")) && (p.hasRelic("ShroomBag"))) {
        return SpireReturn.Return(true);
      }
      return SpireReturn.Continue();
    }
  }
}
