package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod_FnH.patches.AbstractCardEnum;

public class AbsoluteMagnitude 
	extends CustomCard {
	
	public static final String ID = "AbsoluteMagnitude";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_PLUS_DMG = 1;

	public AbsoluteMagnitude() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);

		this.magicNumber = this.baseMagicNumber = this.baseDamage = ATTACK_DMG;
	}
	
	public void applyPowers(){
		AbstractPlayer p = AbstractDungeon.player;
		if (p.hasPower("ChargeUpPower"))
			this.baseDamage = p.getPower("ChargeUpPower").amount*this.magicNumber;
		super.applyPowers();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}

	public AbstractCard makeCopy() {
		return new AbsoluteMagnitude();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_DMG);
		}
	}
}