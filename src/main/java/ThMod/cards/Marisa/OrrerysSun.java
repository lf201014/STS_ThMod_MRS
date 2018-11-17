package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.OrrerysSunPower;

public class OrrerysSun extends CustomCard {

  public static final String ID = "OrrerysSun";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Orrey.png";
  private static final int COST = 1;
  private static final int STACKS = 8;
  private static final int UPG_STC = 3;

  public OrrerysSun() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.baseMagicNumber = this.magicNumber = STACKS;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new OrrerysSunPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new OrrerysSun();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_STC);
    }
  }
}