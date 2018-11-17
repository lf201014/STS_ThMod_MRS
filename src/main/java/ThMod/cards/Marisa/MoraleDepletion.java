package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.MoraleDepletionPlusPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class MoraleDepletion extends CustomCard {

  public static final String ID = "MoraleDepletion";
  public static final String IMG_PATH = "img/cards/MoraleDelpletion.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 5;
  private static final int UPGRADE_PLUS_BLOCK = 2;
  private static final int DRAW = 1;
  private static final int UPGRADE_PLUS_DRAW = 1;

  public MoraleDepletion() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );
    this.baseBlock = BLOCK_AMT;
    this.magicNumber = this.baseMagicNumber = DRAW;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new DrawCardNextTurnPower(p,this.magicNumber),
            this.magicNumber
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new MoraleDepletionPlusPower(p)
        )
    );
  }

  public AbstractCard makeCopy() {
    return new MoraleDepletion();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
      upgradeMagicNumber(UPGRADE_PLUS_DRAW);
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
    }
  }
}