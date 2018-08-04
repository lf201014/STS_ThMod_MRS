package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class EnhancedBroom extends CustomRelic {
    public static final String ID = "EnhancedBroom";
    private static final String IMG = "img/relics/test3.png";
	
    public EnhancedBroom() {
        super(ID, new Texture(IMG), RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new EnhancedBroom();
    }
    
    public void atBattleStart() {
    	this.counter = 0;
    }
    
    public void onUseCard(AbstractCard card, UseCardAction action){
    	if (card.costForTurn == 0){
    		this.counter += 1;
    		if (this.counter % 3 == 0){
    			this.counter = 0;
    			flash();
    			AbstractDungeon.actionManager.addToBottom(
    					new RelicAboveCreatureAction(AbstractDungeon.player, this)
    					);
    			AbstractDungeon.actionManager.addToBottom(
    					new DrawCardAction(AbstractDungeon.player, 1)
    					);
    		}
    	}
    }
    
    public void onVictory()
    {
      this.counter = -1;
    }
}