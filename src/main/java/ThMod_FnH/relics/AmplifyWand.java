package ThMod_FnH.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class AmplifyWand extends CustomRelic {
    public static final String ID = "AmplifyWand";
    private static final String IMG = "img/relics/AmplifyWand_s.png";
    private static final String IMG_OTL = "img/relics/outline/AmplifyWand_s.png";
	
    public AmplifyWand() {
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
        return new AmplifyWand();
    }
    
}