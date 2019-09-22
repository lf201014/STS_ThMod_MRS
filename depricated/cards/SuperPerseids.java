package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

@Deprecated
public class SuperPerseids extends CustomCard {
	public static final String ID = "SuperPerseids";
	public static final String IMG_PATH = "img/cards/SuperPerseids.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DMG = 18;
	private static final int UPG_DMG = 6;
	private static final int BLC = 4;
	private static final int UPG_BLC = 2;
	
	public SuperPerseids() {
		super(
				ID,
				NAME,
				IMG_PATH, 
				COST,
				DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF
				);

		this.baseDamage = DMG;
		this.block = this.baseBlock = BLC;
		this.damageType = DamageType.THORNS;
		this.damageTypeForTurn = DamageType.THORNS;
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
	}
	  
	public void use(AbstractPlayer p, AbstractMonster m) {
		int cnt = 0;
		for (AbstractCard c : p.hand.group) {
			if ((c.type == CardType.CURSE)||(c.type == CardType.STATUS)) {
				cnt++;
			}
		}
		if (cnt>0) {
			AbstractDungeon.actionManager.addToBottom(
					new GainBlockAction(
							p,
							p,
							cnt*this.block
							)
					);
		}
	}
	
	public void triggerOnExhaust() {
		AbstractDungeon.actionManager.addToBottom(
				new DamageRandomEnemyAction(
						new DamageInfo(
							AbstractDungeon.player,
							this.damage,
							DamageType.THORNS
							), 
						AttackEffect.FIRE
						)
				);
	}
	
	public AbstractCard makeCopy() {
		return new SuperPerseids();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
			upgradeBlock(UPG_BLC);
		}
	}
}