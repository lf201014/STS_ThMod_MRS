package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class BreadOfAWashokuLover extends CustomRelic {
    public static final String ID = "BreadOfAWashokuLover";
    private static final String IMG = "img/relics/vCore.png";
    private static final String USED_IMG = "img/relics/usedvCore.png";
	
    public BreadOfAWashokuLover(){
        super(
        		ID,
        		new Texture(IMG),
        		RelicTier.UNCOMMON,
        		LandingSound.FLAT
        		);
    }
    
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new BreadOfAWashokuLover();
    }
    
    public void onEquip() {
    	this.counter = 0;
    }
    
    public void onExhaust(AbstractCard card){
    	if (this.usedUp)
    		return;
    	if ((card.type == CardType.CURSE)||(card.type == CardType.STATUS)) {
    		this.counter++;
    		this.flash();
    		AbstractDungeon.actionManager.addToBottom(
    				new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1)
    				);
    	}
    	if (counter >= 13){
    		this.flash();
    		setTexture(new Texture(USED_IMG));
    		AbstractDungeon.player.increaseMaxHp(13, true);
    		this.counter = -2;
    		usedUp();
    	}
    }
}