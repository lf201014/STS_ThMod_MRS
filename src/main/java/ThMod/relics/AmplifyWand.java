package ThMod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class AmplifyWand extends CustomRelic {

  public static final String ID = "AmplifyWand";
  private static final String IMG = "img/relics/AmplifyWand_s.png";
  private static final String IMG_OTL = "img/relics/outline/AmplifyWand_s.png";
  private static final int BLOCK_AMT = 4;

  public AmplifyWand() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.RARE,
        LandingSound.FLAT
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new AmplifyWand();
  }

  public void onSpecificTrigger() {
    this.flash();
    AbstractDungeon.actionManager.addToBottom(
        new RelicAboveCreatureAction(AbstractDungeon.player, this)
    );
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(AbstractDungeon.player,AbstractDungeon.player,BLOCK_AMT)
    );
  }
}