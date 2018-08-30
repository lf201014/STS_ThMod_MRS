package ThMod_FnH.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.powers.Marisa.GiftOfLifePower;
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
    
    public void atTurnStartPostDraw() {
    	AbstractDungeon.actionManager.addToBottom(
      		  new RelicAboveCreatureAction(AbstractDungeon.player, this)
      		  );
    	AbstractPlayer p = AbstractDungeon.player;
    	AbstractDungeon.actionManager.addToBottom(
				new ApplyPowerAction(
						p,
						p,
						new GiftOfLifePower(p, 1),
						1
						)
				);
    }
}