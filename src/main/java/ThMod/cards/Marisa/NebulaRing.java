package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;

public class NebulaRing extends CustomCard {

  public static final String ID = "NebulaRing";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 4;
  private static final int BLOCK_AMT = 18;
  private static final int UPGRADE_PLUS_BLOCK = 6;

  public NebulaRing() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.BASIC,
        AbstractCard.CardTarget.SELF
    );
    this.baseBlock = BLOCK_AMT;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    if ((this.costForTurn != this.costForTurn)||(this.costForTurn <= 0)){
      return;
    }
    int count = 0;
    for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
      if (!mon.isDeadOrEscaped()) {
        count++;
      }
    }
    if (count >= this.costForTurn){
      this.costForTurn = 0;
    } else {
      this.costForTurn -= count;
    }
    this.isCostModified = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
  }

  public AbstractCard makeCopy() {
    return new NebulaRing();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}

