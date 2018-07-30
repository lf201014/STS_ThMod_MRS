package ThMod_FnH.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod_FnH.ThMod;

import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SingualrityPower
	extends AbstractPower{
	public static final String POWER_ID = "SingualrityPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
	public SingualrityPower(AbstractCreature owner, int amount){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/darkness.png");
	}
	
	public void updateDescription(){
			this.description = (DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]);
	}
	
	public void onPlayCard(AbstractCard card, AbstractMonster m){
		if (card.costForTurn == 0) {
			ThMod.logger.info("SingualrityPower : applying upgrade :");
			for (AbstractCard c:AbstractDungeon.player.hand.group) {
				if (c.type == CardType.ATTACK) {
					ThMod.logger.info("SingualrityPower : adding "+this.amount+" base damage to "+c.cardID);
					c.baseDamage += this.amount;
					c.applyPowers();
				}
			}
		}
	}
}