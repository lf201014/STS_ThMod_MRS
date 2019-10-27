package ThMod.action;

import ThMod.ThMod;
import ThMod.powers.Marisa.PropBagPower;
import ThMod.relics.AmplifyWand;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.relics.GremlinHorn;
import com.megacrit.cardcrawl.relics.Kunai;
import com.megacrit.cardcrawl.relics.LetterOpener;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;
import com.megacrit.cardcrawl.relics.MercuryHourglass;
import com.megacrit.cardcrawl.relics.MummifiedHand;
import com.megacrit.cardcrawl.relics.OrnamentalFan;
import com.megacrit.cardcrawl.relics.Shuriken;
import com.megacrit.cardcrawl.relics.Sundial;
import java.util.ArrayList;

public class PropBagAction
	extends AbstractGameAction{
  
	public PropBagAction(){
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
  
	public void update(){
		
		AbstractPlayer p = AbstractDungeon.player;
		ThMod.logger.info("PropBagAction : Checking for relics");

		ArrayList<AbstractRelic> rs = new ArrayList<AbstractRelic>();
		AbstractRelic r;

		if (!p.hasRelic("Meat on the Bone")){
      r = new MeatOnTheBone();
      rs.add(r);
    }

    if (!p.hasRelic("Mummified Hand")){
      r = new MummifiedHand();
      rs.add(r);
    }

    if (!p.hasRelic("Letter Opener")){
      r = new LetterOpener();
      rs.add(r);
    }

    if (!p.hasRelic("Shuriken")){
      r = new Shuriken();
      rs.add(r);
    }

		if (!p.hasRelic("Gremlin Horn")){
			r = new GremlinHorn();
			rs.add(r);
		}
		if (!p.hasRelic("Sundial")){
			r = new Sundial();
			rs.add(r);
		}
		if (!p.hasRelic("Mercury Hourglass")){
			r = new MercuryHourglass();
			rs.add(r);
		}
		if (!p.hasRelic("Ornamental Fan")){
			r = new OrnamentalFan();
			rs.add(r);
		}
		if (!p.hasRelic("Kunai")){
			r = new Kunai();
			rs.add(r);
		}
		if (!p.hasRelic("Blue Candle")){
			r = new BlueCandle();
			rs.add(r);
		}
		if (!p.hasRelic("AmplifyWand")){
			r = new AmplifyWand();
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
		rs.size();
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

	}
}