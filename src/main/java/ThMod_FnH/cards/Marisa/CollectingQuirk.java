package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class CollectingQuirk 
	extends CustomCard {
	
	public static final String ID = "CollectingQuirk";
	private static final CardStrings cardStrings = 
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	private static final int COST = 2;
	private static final int DVID = 4;
	private static final int UPG_DVID = -1;
	private static final int ATK_DMG = 7;
	public CollectingQuirk() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATK_DMG;
		this.magicNumber = this.baseMagicNumber = DVID;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int cnt = p.relics.size()/this.magicNumber;
		if (cnt > 0) {
			for (int i = 0;i < cnt ;i++) {
				AbstractDungeon.actionManager.addToBottom(
					new DamageRandomEnemyAction(
							new DamageInfo(p, this.damage, this.damageTypeForTurn),
							AbstractGameAction.AttackEffect.SLASH_DIAGONAL)
					);
			}
		}
	}

	public AbstractCard makeCopy() {
		return new CollectingQuirk();
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPG_DMG);
			upgradeMagicNumber(UPG_DVID);
		}
	}
}