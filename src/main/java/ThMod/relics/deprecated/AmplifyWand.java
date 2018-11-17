package ThMod.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

@Deprecated
public class AmplifyWand extends CustomRelic {

  public static final String ID = "AmplifyWand";
  private static final String IMG = "img/relics/AmplifyWand_s.png";
  private static final String IMG_OTL = "img/relics/outline/AmplifyWand_s.png";

  public AmplifyWand() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.RARE,
        LandingSound.FLAT
    );
    this.counter = 0;
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new AmplifyWand();
  }

  public void onSpecificTrigger() {
    this.counter++;
    if (this.counter >= 3) {
      this.counter = 0;
      this.flash();
      AbstractDungeon.actionManager.addToBottom(
          new RelicAboveCreatureAction(AbstractDungeon.player, this)
      );
      AbstractDungeon.actionManager.addToBottom(
          new GainEnergyAction(2)
      );
    }
  }
}