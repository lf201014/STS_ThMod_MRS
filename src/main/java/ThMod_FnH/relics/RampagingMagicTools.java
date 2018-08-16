package ThMod_FnH.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomRelic;

public class RampagingMagicTools extends CustomRelic {
    public static final String ID = "RampagingMagicTools";
    private static final String IMG = "img/relics/test1.png";
    private static final int STCS = 3;
    private static final int STCS_H = 8;
	
    public RampagingMagicTools() {
        super(ID, new Texture(IMG), RelicTier.BOSS, LandingSound.FLAT);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new RampagingMagicTools();
    }
    
    public void onEquip(){
      AbstractDungeon.player.energy.energyMaster += 1;
    }
    
    public void onUnequip(){
      AbstractDungeon.player.energy.energyMaster -= 1;
    }
    
    public void atBattleStart() {
    	int rng = AbstractDungeon.relicRng.random(0, 4);
    	AbstractPower pow = null;
    	int stc = STCS;
    	AbstractPlayer p = AbstractDungeon.player;
    	switch (rng) {
    	case 0:
    		pow = new FrailPower(p, stc, false);
    		break;
    	case 1:
    		pow = new WeakPower(p, stc, false);
    		break;
    	case 2:
    		pow = new VulnerablePower(p, stc, false);
    		break;
    	case 3:
    		pow = new PoisonPower(p, p, stc);
    		break;
    	case 4:
    		stc = STCS_H;
    		pow = new ChargeUpPower(p, stc);
    		break;
    	default:
    		break;
    	}
    	if (pow != null) {
    		AbstractDungeon.actionManager.addToBottom(
    				new RelicAboveCreatureAction(AbstractDungeon.player, this)
    				);
    		AbstractDungeon.actionManager.addToBottom(
    				new ApplyPowerAction(p,p,pow,STCS)
    				);
    	}
    }
}