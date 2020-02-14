package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

@Deprecated
public class Burn_MRS extends CustomCard {
	public static final String ID = "Burn_MRS";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Burn");
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;

	public Burn_MRS() {
		super(ID, NAME, "img/cards/burn.png", COST, DESCRIPTION,
				AbstractCard.CardType.STATUS,
				AbstractCard.CardColor.COLORLESS,
				CardRarity.COMMON,
				AbstractCard.CardTarget.SELF
				);

		this.damage = 5;
		this.magicNumber = 2;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m){
		if (p != null) {
			if ((!this.dontTriggerOnUseCard) && (p.hasRelic("Medical Kit"))){
				useMedicalKit(p);
			}
		else{
			 AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Burn_MRS();
	}

	@Override
	public void upgrade() {
	      upgradeName();
	}
}


