package ThMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class SproutingBranch extends CustomRelic {

  public static final String ID = "SproutingBranch";
  private static final String IMG = "img/relics/sproutingBranch.png";
  private static final String IMG_OTL = "img/relics/outline/sproutingBranch.png";

  public SproutingBranch() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.SPECIAL,
        LandingSound.FLAT
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new SproutingBranch();
  }

  public void atBattleStart() {
    AbstractDungeon.actionManager.addToBottom(
        new RelicAboveCreatureAction(AbstractDungeon.player, this)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new RegenPower(AbstractDungeon.player, 5),
            1
        )
    );
  }
}