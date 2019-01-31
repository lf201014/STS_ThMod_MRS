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
      if ((AbstractDungeon.player.hasRelic("ShroomBag")) || (AbstractDungeon.player.hasRelic("BigShroomBag"))) {
        AbstractRelic r;
        int heal_amt;
        if (AbstractDungeon.player.hasRelic("BigShroomBag")) {
          r = p.getRelic("BigShroomBag");
          heal_amt = 5;
        } else {
          r = p.getRelic("ShroomBag");
          heal_amt = 3;
        }

        r.flash();
        AbstractDungeon.actionManager.addToBottom(
            new RelicAboveCreatureAction(p, r)
        );
        _inst.exhaust = true;
        AbstractDungeon.actionManager.addToBottom(
            new HealAction(p, p, heal_amt)
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
      if ((_inst.cardID.equals("Parasite")) && ((p.hasRelic("ShroomBag"))||(p.hasRelic("BigShroomBag")))) {
        return SpireReturn.Return(true);
      }
      return SpireReturn.Continue();
    }
  }
}
