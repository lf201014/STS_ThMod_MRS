package ThMod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.ArtOfWar;
import com.megacrit.cardcrawl.relics.Orichalcum;

import ThMod.ThMod;
import ThMod.powers.Marisa.PropBagPower;
import ThMod.relics.AmplifyWand;

public class PropBagAction
	extends AbstractGameAction{
  
	public PropBagAction(){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
  
	public void update(){
		this.isDone = false;
		
		AbstractPlayer p = AbstractDungeon.player;
		Boolean hasOri = false,
				hasWand = false,
				hasArt = false;
		ThMod.logger.info("PropBagAction : Checking for relics");
		
		for (AbstractRelic r:p.relics) {
			switch (r.relicId) {
			case "Orichalcum":
				hasOri = true;
				break;
			case "Art of War":
				hasArt = true;
				break;
			case "AmplifyWand":
				hasWand = true;
				break;
			}
		}
		ThMod.logger.info(
				"PropBagAction : done checking :"
				+ "has Orichalcum :"+hasOri
				+ " ; has ArtOfWar : "+hasArt
				+ " ; has AmplifyWand : "+hasWand
				);
		ArrayList<AbstractRelic> rs = new ArrayList<AbstractRelic>();
		AbstractRelic r;
		if (!hasOri) {
			r = new Orichalcum();
			rs.add(r);
		}
		if (!hasWand) {
			r = new AmplifyWand();
			rs.add(r);
		}
		if (!hasArt) {
			r = new ArtOfWar();
			rs.add(r);
		}
		if (rs.size()<=0) {
			ThMod.logger.info("PropBagAction : No relic to give,returning");
			this.isDone =true;
			return;
		}
		if (rs.size()==1) {
			r = rs.get(0);
			ThMod.logger.info("PropBagAction : Only one relic to give : "+r.relicId);
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(
							p,p, 
							new PropBagPower(p,r)
							)
					);
			this.isDone = true;
			return;
		}
		if (rs.size()>1) {
			int index = AbstractDungeon.miscRng.random(0,rs.size()-1);
			r = rs.get(index);
			ThMod.logger.info(
					"PropBagAction : random relic : "+r.relicId
					+" ; random index : "+index
					);
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(
							p,p, 
							new PropBagPower(p,r)
							)
					);
			this.isDone = true;
			return;
		}
		
	    
		this.isDone = true;
	}
}