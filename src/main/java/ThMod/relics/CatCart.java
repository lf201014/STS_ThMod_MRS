package ThMod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CatCart extends CustomRelic {

  public static final String ID = "CatCart";
  private static final String IMG = "img/relics/test6.png";
  private static final String IMG_OTL = "img/relics/outline/test6.png";
  private static final int HEAL_PER_CHARGE = 5;

  public CatCart() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.SPECIAL,
        LandingSound.FLAT
    );
    this.counter = 0;
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public void onEnterRoom(AbstractRoom room) {
    flash();
    this.counter++;
  }

  public void onTrigger() {
    if (this.counter > 0) {
      flash();
      AbstractDungeon.actionManager.addToTop(
          new RelicAboveCreatureAction(AbstractDungeon.player, this)
      );
      int healAmt = this.counter * HEAL_PER_CHARGE;
      AbstractDungeon.player.heal(healAmt, true);
      this.counter = 0;
    }
  }

  public AbstractRelic makeCopy() {
    return new CatCart();
  }
}
