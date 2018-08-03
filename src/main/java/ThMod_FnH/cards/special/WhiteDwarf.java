package ThMod_FnH.cards.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class WhiteDwarf extends CustomCard {
	public static final String ID = "WhiteDwarf";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/pride.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 0;
	private float magn = 1.5f;

	public WhiteDwarf() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = ATTACK_DMG;
		
	}
	
	@Override
	public void applyPowers(){
		AbstractPlayer player = AbstractDungeon.player;
		ThMod.logger.info("WhiteDwarf : applyPowers : player discard pile size :"+player.discardPile.size());
		this.baseDamage = (int) (Math.floor(player.discardPile.size() * this.magn));
		super.applyPowers();
	}
	
	@Override
	public void calculateDamageDisplay(AbstractMonster mo){
		calculateCardDamage(mo);
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo){
		AbstractPlayer player = AbstractDungeon.player;
		ThMod.logger.info("WhiteDwarf : calculateCardDamage : player discard pile size :"+player.discardPile.size());
		this.baseDamage = (int) (Math.floor(player.discardPile.size() * this.magn));
		super.applyPowers();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m){
		if (p.hand.size() <= 3)
			return true;
		return false;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(m,
						new DamageInfo(p, this.damage, this.damageTypeForTurn),
						AttackEffect.SLASH_DIAGONAL)
				);
	}

	public AbstractCard makeCopy() {
		return new WhiteDwarf();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.magn = 2.0f;
			this.rawDescription = DESCRIPTION_UPG;
			initializeDescription();
		}
	}
}