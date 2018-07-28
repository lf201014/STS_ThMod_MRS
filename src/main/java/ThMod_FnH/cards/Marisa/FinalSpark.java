package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import ThMod_FnH.ThMod;
import ThMod_FnH.abstracts.AmplifiedAttack;
import ThMod_FnH.patches.AbstractCardEnum;

public class FinalSpark 
	extends AmplifiedAttack {
	
	public static final String ID = "FinalSpark";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 3;
	private static final int ATK_DMG = 20;
	private static final int UPG_DMG = 8;
	private static final int AMP_DMG = 15;
	private static final int UPG_AMP = 6;
	private static final int AMP = 2;
	

	public FinalSpark() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ALL_ENEMY);

		this.isMultiDamage = true;
		this.baseDamage = ATK_DMG;
		this.ampNumber = AMP_DMG;
		this.baseBlock = this.baseDamage + this.ampNumber;
	}

	public void triggerOnOtherCardPlayed(AbstractCard c) {
		if ((c.cardID=="Strike_MRS")||(c.cardID=="DarkSpark")
      		  ||(c.cardID=="MachineGunSpark")||(c.cardID=="Spark")
      		  ||(c.cardID=="DoubleSpark")||(c.cardID=="FinalSpark")
      		  ||(c.cardID=="MasterSpark"))
			if (this.costForTurn>0)
			 this.setCostForTurn(this.costForTurn-1);
	}

	public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
		
	    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
	    AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY), 0.1F));
		
		if (ThMod.Amplified(AMP+this.costForTurn,AMP))
			AbstractDungeon.actionManager.addToBottom(
					new DamageAllEnemiesAction(p, this.multiAmpDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		else
			AbstractDungeon.actionManager.addToBottom(
					new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
	}

	public AbstractCard makeCopy() {
		return new FinalSpark();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
			this.ampNumber += UPG_AMP;
			this.block = this.baseDamage + this.ampNumber;
			this.isBlockModified = true;
		}
	}
}