package ThMod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import basemod.abstracts.CustomRelic;

public class BigShroomBag extends CustomRelic {

  public static final String ID = "BigShroomBag";
  private static final String IMG = "img/relics/BigShroomBag.png";
  private static final String IMG_OTL = "img/relics/outline/BigShroomBag.png";

  public BigShroomBag() {
    super(
        ID,
        new Texture(IMG),
        new Texture(IMG_OTL),
        RelicTier.SPECIAL,
        LandingSound.FLAT
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new BigShroomBag();
  }

  public void onEquip() {
    AbstractDungeon.player.loseRelic("ShroomBag");
  }
}