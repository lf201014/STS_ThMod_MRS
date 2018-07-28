package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class IllusionStar extends CustomCard{
    public static final String ID = "IllusionStar";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Defend.png";
    private static final int COST = 0;

    public IllusionStar() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.MARISA_COLOR,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber =	2;
    }
  
    public void use(AbstractPlayer p, AbstractMonster m){
    	for (int i=0;i<this.magicNumber;i++) {

			AbstractCard c = AbstractDungeon.getCard(AbstractDungeon.rollRarity(AbstractDungeon.miscRng), AbstractDungeon.miscRng).makeStatEquivalentCopy();

			c.misc = 1;
        	
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
		}
    }
  
    public AbstractCard makeCopy(){
    	return new IllusionStar();
    }
  
    public void upgrade(){
    	if (!this.upgraded){
    		upgradeName();
    		upgradeMagicNumber(1);
    	}
    }
}