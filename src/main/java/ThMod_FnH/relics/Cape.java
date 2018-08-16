package ThMod_FnH.relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import basemod.abstracts.CustomRelic;

public class Cape extends CustomRelic {
    public static final String ID = "Cape";
    private static final String IMG = "img/relics/test3.png";
    private static final String IMG_OTL = "img/relics/outline/test3.png";
    private boolean avail;
	
    public Cape() {
        super(
        		ID,
        		ImageMaster.loadImage(IMG),
        		ImageMaster.loadImage(IMG_OTL),
        		RelicTier.RARE, 
        		LandingSound.MAGICAL
        		);
        this.counter = 0;
        this.avail = false;
    }
    
    public void onEnterRoom(AbstractRoom room){
    	if ((room instanceof ShopRoom)){
    		this.avail = true;
    		flash();
    		this.pulse = true;
    	}
    	else{
    		this.pulse = false;
    	}
    }
    
    public String getUpdatedDescription() {
    	return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
    	return new Cape();
    }
    
    private void applyDiscount() {
    }
}