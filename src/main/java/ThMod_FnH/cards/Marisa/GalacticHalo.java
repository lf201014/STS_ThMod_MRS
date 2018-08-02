package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.GalacticHaloPower;
import basemod.abstracts.CustomCard;

public class GalacticHalo extends CustomCard{
    public static final String ID = "GalacticHalo";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/feelNoPain.png";
    private static final int COST = 1;
    private static final int STC = 2;
    private static final int UPG_STC = 1;

    public GalacticHalo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.MARISA_COLOR,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = STC;
    }
  
    public void use(AbstractPlayer p, AbstractMonster m){
    	AbstractDungeon.actionManager.addToBottom(
    		new ApplyPowerAction(p , p , 
    				new GalacticHaloPower(p,this.magicNumber), this.magicNumber)
    		);
    }
  
    public AbstractCard makeCopy(){
    	return new GalacticHalo();
    }
  
    public void upgrade(){
    	if (!this.upgraded){
    		upgradeName();
    		upgradeMagicNumber(UPG_STC);
    	}
    }
}