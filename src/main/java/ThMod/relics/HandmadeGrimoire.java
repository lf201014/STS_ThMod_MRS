package ThMod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class HandmadeGrimoire extends CustomRelic {

  public static final String ID = "HandmadeGrimoire";
  private static final String IMG = "img/relics/Grimoire.png";
  private static final String IMG_OTL = "img/relics/outline/Grimoire.png";

  public HandmadeGrimoire() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.UNCOMMON,
        LandingSound.FLAT
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new HandmadeGrimoire();
  }

  public void atBattleStart() {
    int cnt = AbstractDungeon.player.masterDeck.size() / 15;
    flash();
    if (cnt > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new RelicAboveCreatureAction(AbstractDungeon.player, this)
      );
      AbstractDungeon.player.gainEnergy(cnt);
      AbstractDungeon.actionManager.addToBottom(
          new DrawCardAction(AbstractDungeon.player, cnt)
      );
    }
  }
}