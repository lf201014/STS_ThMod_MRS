package ThMod.cards.deprecated;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.deprecated.TalismanPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class FiveColoredTalisman extends CustomCard {

  public static final String ID = "FiveColoredTalisman";
  public static final String IMG_PATH = "img/cards/feelNoPain.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int STACK = 1;

  public FiveColoredTalisman() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.MARISA_DERIVATIONS,
        CardRarity.BASIC,
        CardTarget.SELF
    );
    setBannerTexture(
        "images/cardui/512/banner_uncommon.png",
        "images/cardui/1024/banner_uncommon.png"
    );
    this.magicNumber = this.baseMagicNumber = STACK;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new TalismanPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new FiveColoredTalisman();
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
