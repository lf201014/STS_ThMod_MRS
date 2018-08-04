package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.ThMod;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomRelic;

public class EnhancedHakkero extends CustomRelic {
    public static final String ID = "EnhancedHakkero";
    private static final String IMG = "img/relics/test2.png";
	
    public EnhancedHakkero() {
        super(ID, new Texture(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new EnhancedHakkero();
    }
    
    public void obtain(){
    	if (AbstractDungeon.player.hasRelic("MiniHakkero")) {
    		instantObtain(AbstractDungeon.player, 0, false);
    	} else {
    		super.obtain();
    	}
    }
    
    public void onUseCard(AbstractCard card, UseCardAction action) {
    	flash();
    	ThMod.logger.info("Applying ChargeUpPower");
		AbstractDungeon.actionManager.addToTop(
				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ChargeUpPower(AbstractDungeon.player,2),2));
    	AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    
    public void atBattleStartPreDraw() {
    	
    	ThMod.logger.info("EnhancedHakkero : Start Marking");
    	AbstractPlayer p = AbstractDungeon.player;
    	
    	ThMod.logger.info("EnhancedHakkero : Checking DiscardPile");
    	if (!p.discardPile.isEmpty())
    		for (AbstractCard c : p.discardPile.group) {
    			if (c.cardID != "RitualDagger")
    				c.misc = 1;
    			ThMod.logger.info("EnhancedHakkero : Marking "+c.cardID);
    		}
    	
    	ThMod.logger.info("EnhancedHakkero : Checking Draw Pile");
    	if (!p.drawPile.isEmpty())
    		for (AbstractCard c : p.drawPile.group) {
    			if (c.cardID != "RitualDagger")
    				c.misc = 1;
    			ThMod.logger.info("EnhancedHakkero : Marking "+c.cardID);
    		}

    	ThMod.logger.info("EnhancedHakkero : Checking Hand");
    	if (!p.hand.isEmpty())
    		for (AbstractCard c : p.hand.group) {
    			if (c.cardID != "RitualDagger")
    				c.misc = 1;
    			ThMod.logger.info("EnhancedHakkero : Marking "+c.cardID);
    		}
    }
}