package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class SimpleLauncher extends CustomRelic {
    public static final String ID = "SimpleLauncher";
    private static final String IMG = "img/relics/test8.png";
	
    public SimpleLauncher() {
        super(ID, new Texture(IMG), RelicTier.SHOP, LandingSound.HEAVY);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new SimpleLauncher();
    }
    
}