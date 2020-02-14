package ThMod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Cape extends CustomRelic {
  public static final String ID = "MarisaMod:Cape";
  private static final String IMG = "img/relics/test7.png";
  private static final String IMG_OTL = "img/relics/outline/test7.png";
  public Cape() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.RARE,
        LandingSound.MAGICAL
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new Cape();
  }


}
