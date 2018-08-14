package ThMod_FnH.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class AbstractRelicPatch {
	@SpirePatch(cls = "com.megacrit.cardcrawl.relics.AbstractRelic", method = "atBattleStartPreDraw")
	public static class MarkMasterDeckCards {
		public static void Prefix(Object __obj_instance) {
			ThMod.logger.info("AbstractRelic : Start Marking");
	    	AbstractPlayer p = AbstractDungeon.player;
	    	
	    	ThMod.logger.info("AbstractRelic : Checking DiscardPile");
	    	if (!p.discardPile.isEmpty())
	    		for (AbstractCard c : p.discardPile.group) {
	    			if (c.draw == 1) {
	    				return;
	    			} else {
	    				c.draw = 1;
	    			}
	    			ThMod.logger.info("AbstractRelic : Marking "+c.cardID);
	    		}
	    	
	    	ThMod.logger.info("AbstractRelic : Checking Draw Pile");
	    	if (!p.drawPile.isEmpty())
	    		for (AbstractCard c : p.drawPile.group) {
	    			if (c.draw == 1) {
	    				return;
	    			} else {
	    				c.draw = 1;
	    			}
	    			ThMod.logger.info("AbstractRelic : Marking "+c.cardID);
	    		}

	    	ThMod.logger.info("AbstractRelic : Checking Hand");
	    	if (!p.hand.isEmpty())
	    		for (AbstractCard c : p.hand.group) {
	    			if (c.draw == 1) {
	    				return;
	    			} else {
	    				c.draw = 1;
	    			}
	    			ThMod.logger.info("AbstractRelic : Marking "+c.cardID);
	    		}
		}
	}
}
