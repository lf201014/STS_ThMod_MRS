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
import ThMod.powers.Marisa.EscapeVelocityPower;

public class EscapeVelocity extends CustomCard {

  public static final String ID = "EscapeVelocity";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/EscapeVelocity.png";
  private static final int COST = 1;

  public EscapeVelocity() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = 1;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new EscapeVelocityPower(p, this.magicNumber),
            this.magicNumber
        )
    );
  }

  public AbstractCard makeCopy() {
    return new EscapeVelocity();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.isInnate = true;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}