package ThMod_FnH.cards.Marisa;


import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.DiasporaPower;
import basemod.abstracts.CustomCard;

public class FairyDestructionRay extends CustomCard {
	public static final String ID = "FairyDestructionRay";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int DIAPORA = 15;

	public FairyDestructionRay() {
		super(
				ID, 
				NAME,
				IMG_PATH,
				COST, 
				DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY
				);
		
		this.isMultiDamage = true;
		this.baseDamage = this.damage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DIAPORA;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(
			new DamageAllEnemiesAction(
					p,
					this.multiDamage,
					this.damageTypeForTurn,
					AttackEffect.SLASH_DIAGONAL
					)
			);
		
		if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(
								mo, 
								p,
								new DiasporaPower(mo, this.magicNumber), 
								this.magicNumber, 
								true,
								AttackEffect.NONE
								)
						);
		}
	}

	public AbstractCard makeCopy() {
		return new FairyDestructionRay();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}