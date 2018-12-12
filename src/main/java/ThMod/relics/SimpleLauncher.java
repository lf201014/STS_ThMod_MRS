package ThMod.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class SimpleLauncher extends CustomRelic {

  public static final String ID = "SimpleLauncher";
  private static final String IMG = "img/relics/FlashLight.png";
  private static final String IMG_OTL = "img/relics/outline/FlashLight.png";

  public SimpleLauncher() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.SHOP,
        LandingSound.HEAVY
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new SimpleLauncher();
  }

}