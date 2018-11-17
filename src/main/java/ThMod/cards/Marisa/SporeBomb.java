package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class SporeBomb extends CustomCard {

  public static final String ID = "SporeBomb";
  public static final String IMG_PATH = "img/cards/SporeCrump.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 0;
  private static final int AMP = 1;
  private static final int STC = 2;
  private static final int UPG_STC = 1;

  public SporeBomb() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY
    );
    this.magicNumber = this.baseMagicNumber = STC;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                mo,
                p,
                new VulnerablePower(mo, this.magicNumber, false),
                this.magicNumber,
                true
            )
        );
      }
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              m,
              p,
              new VulnerablePower(m, this.magicNumber, false),
              this.magicNumber,
              true
          )
      );
    }
  }

  public AbstractCard makeCopy() {
    return new SporeBomb();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_STC);
    }
  }
}