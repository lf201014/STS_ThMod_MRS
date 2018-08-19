package ThMod_FnH.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;

import ThMod_FnH.ThMod;
import basemod.abstracts.CustomRelic;

public class Cape extends CustomRelic {
    public static final String ID = "Cape";
    private static final String IMG = "img/relics/vCore.png";
    private static final String IMG_USED = "img/relics/usedvCore.png";
    private static final String IMG_OTL = "img/relics/outline/vCore.png";
    private boolean avail;
	
    public Cape() {
        super(
        		ID,
        		ImageMaster.loadImage(IMG),
        		ImageMaster.loadImage(IMG_OTL),
        		RelicTier.RARE, 
        		LandingSound.MAGICAL
        		);
        this.counter = 3;
        this.avail = false;
    }
    
    public void onEnterRoom(AbstractRoom room){
    	if (this.counter <= 0) {
    		return;
    	}
    	if ((room instanceof ShopRoom)){
    		ThMod.logger.info("Cape : onEnterRoom : ShopRoom detected .");
    		this.avail = true;
    		flash();
    		this.pulse = true;
    		AbstractDungeon.shopScreen.applyDiscount(0,true);;
    	}
    	else{
    		this.avail = false;
    		this.pulse = false;
    	}
    }
    
    public void onSpendGold() {
    	if ((this.counter <= 0)||(this.usedUp)) {
    		return;
    	}
    	if (this.avail) {
    		this.counter --;
    		this.avail = false;
    		
    		ShopScreen currShop = AbstractDungeon.shopScreen;
    		
    		ShopScreen.actualPurgeCost = ShopScreen.purgeCost;
    		if (AbstractDungeon.ascensionLevel >= 16) {
    			currShop.applyDiscount(1.15F, false);
    		}
    		if (AbstractDungeon.player.hasRelic("The Courier")) {
    			currShop.applyDiscount(0.8F, true);
    		}
    		if (AbstractDungeon.player.hasRelic("Membership Card")) {
    			currShop.applyDiscount(0.8F, true);
    		}
    		if (AbstractDungeon.player.hasRelic("Smiling Mask")) {
    			ShopScreen.actualPurgeCost = 50;
    		}
    		
    		this.pulse = false;
    		
    		if (this.counter <= 0) {
    			this.img = ImageMaster.loadImage(IMG_USED);
    			usedUp();
    			this.counter = -2;
    		}
    	}
    }
    
    public String getUpdatedDescription() {
    	return DESCRIPTIONS[0]+this.counter+DESCRIPTIONS[1];
    }
    
    public AbstractRelic makeCopy() {
    	return new Cape();
    }
}