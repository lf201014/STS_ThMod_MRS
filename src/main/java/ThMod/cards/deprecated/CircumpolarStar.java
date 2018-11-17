package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

@Deprecated
public class CircumpolarStar extends CustomCard {

  public static final String ID = "CircumpolarStar";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 6;
  private static final int UPGRADE_PLUS_BLOCK = 3;
  private static final int DRAW = 1;
  private static final int UPG_DRAW = 1;


  public CircumpolarStar() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF);

    this.baseBlock = BLOCK_AMT;
    this.magicNumber = this.baseMagicNumber = DRAW;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block));

    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(p, this.magicNumber));

    this.upgradeMagicNumber(UPG_DRAW);
  }

  public AbstractCard makeCopy() {
    return new CircumpolarStar();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}