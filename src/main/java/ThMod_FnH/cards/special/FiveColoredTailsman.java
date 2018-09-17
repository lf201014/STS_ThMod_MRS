package ThMod_FnH.cards.special;

import ThMod_FnH.patches.AbstractCardEnum;
import ThMod_FnH.powers.Marisa.FiveColorTailsmanPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FiveColoredTailsman extends CustomCard {

  public static final String ID = "FiveColoredTailsman";
  public static final String IMG_PATH = "img/cards/Defend_MRS.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;

  public FiveColoredTailsman() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.BASIC,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new FiveColorTailsmanPower(p, 1),
            1
        )
    );
  }

  public AbstractCard makeCopy() {
    return new FiveColoredTailsman();
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
    }
  }
}
