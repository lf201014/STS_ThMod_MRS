package ThMod_FnH.cards.Marisa;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.abstracts.CustomCard;
import ThMod_FnH.patches.AbstractCardEnum;

public class MagicAbsorber extends CustomCard {
	public static final String ID = "MagicAbsorber";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_PLUS_BLOCK = 4;
	
	public MagicAbsorber() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK_AMT;
		this.magicNumber = this.baseMagicNumber = 1;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		if (!p.powers.isEmpty()){
			
			ArrayList<AbstractPower> pows = new ArrayList<AbstractPower>();
			for (AbstractPower pow : p.powers)
				if (pow.type == AbstractPower.PowerType.DEBUFF)
					pows.add(pow);
			
			if (!pows.isEmpty()) {
				AbstractPower po = pows.get((int)Math.random()*pows.size());
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,po));
			}
		}
	}

	public AbstractCard makeCopy() {
		return new MagicAbsorber();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}