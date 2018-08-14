package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.cards.special.Burn_MRS;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class SuperPerseids extends CustomCard {
	public static final String ID = "SuperPerseids";
	public static final String IMG_PATH = "img/cards/Strike.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int DMG = 5;
	private static final int UPG_DMG = 2;
	
	public SuperPerseids() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

		this.baseDamage = DMG;
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		this.retain = true;
	}
	  
	public void use(AbstractPlayer p, AbstractMonster m) {
		int cnt = 0;
		for (AbstractCard c : p.hand.group) {
			if ((c instanceof Burn)||(c instanceof Burn_MRS)) {
				cnt++;
				AbstractDungeon.actionManager.addToBottom(
						new ExhaustSpecificCardAction(c, p.hand)
						);
			}
		}
		if (cnt>0) {
			AbstractDungeon.actionManager.addToBottom(
					new DamageAction(
							m,
							new DamageInfo(
									p,
									cnt*this.damage,
									damageTypeForTurn
									),
							AttackEffect.FIRE
							)
					);
		}
	}
	
	public AbstractCard makeCopy() {
		return new SuperPerseids();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
		}
	}
}