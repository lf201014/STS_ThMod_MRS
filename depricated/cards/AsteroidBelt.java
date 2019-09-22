package ThMod.cards.deprecated;

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
import ThMod.powers.deprecated.AsteroidBeltPower;

@Deprecated
public class AsteroidBelt extends CustomCard {

  public static final String ID = "AsteroidBelt";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 7;
  private static final int UPGRADE_PLUS_BLOCK = 3;


  public AsteroidBelt() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.SELF);

    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AsteroidBeltPower(p)));
  }

  public AbstractCard makeCopy() {
    return new AsteroidBelt();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}