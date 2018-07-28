package ThMod_FnH.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.ThMod;
import ThMod_FnH.powers.Marisa.ChargeUpPower;

public class MiniHakkero extends CustomRelic {
    public static final String ID = "MiniHakkero";
    private static final String IMG = "img/relics/test1.png";
	
    public MiniHakkero() {
        super(ID, new Texture(IMG), RelicTier.STARTER, LandingSound.MAGICAL);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new MiniHakkero();
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
    	flash();
    	ThMod.logger.info("Applying ChargeUpPower");
		AbstractDungeon.actionManager.addToTop(
				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ChargeUpPower(AbstractDungeon.player,1),1));
    	AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}