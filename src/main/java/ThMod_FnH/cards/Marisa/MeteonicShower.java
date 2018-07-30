package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class MeteonicShower 
	extends CustomCard {
	
	public static final String ID = "MeteonicShower";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 1;
	private static final int ATK_DMG = 3;
	private static final int UPG_DMG = 1;
	

	public MeteonicShower() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY);

		this.baseDamage = ATK_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		ThMod.logger.info("MeteonicShower : get count");
		
		int count = (AbstractDungeon.player.hand.size()-1) * 2;

		ThMod.logger.info("MeteonicShower : count : "+count);
		ThMod.logger.info("MeteonicShower : looking for burn");
				
		for (AbstractCard c:p.hand.group)
			if (c.cardID == "Burn")
				count++;

		ThMod.logger.info("MeteonicShower : count : "+count);
		
		for (int i = 0; i < count; i++) {
			AbstractDungeon.actionManager.addToTop(
					new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
		}
		
		for (int i = 0; i < count; i++) {
			AbstractDungeon.actionManager.addToTop(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
		}
	}

	public AbstractCard makeCopy() {
		return new MeteonicShower();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
		}
	}
}