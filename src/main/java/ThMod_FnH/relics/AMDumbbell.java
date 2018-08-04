package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class AMDumbbell extends CustomRelic {
    public static final String ID = "AMDumbbell";
    private static final String IMG = "img/relics/test5.png";
	
    public AMDumbbell() {
        super(ID, new Texture(IMG), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new AMDumbbell();
    }
    
}