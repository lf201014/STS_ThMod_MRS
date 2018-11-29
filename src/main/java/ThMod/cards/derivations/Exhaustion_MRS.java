package ThMod.cards.derivations;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class Exhaustion_MRS extends CustomCard {

  public static final String ID = "Exhaustion_MRS";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack
      .getCardStrings("Exhaustion_MRS");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = -2;

  public Exhaustion_MRS() {
    super(
        ID,
        NAME,
        "img/cards/temp/Exhaustion.png",
        COST,
        DESCRIPTION,
        CardType.CURSE,
        CardColor.CURSE,
        CardRarity.CURSE,
        CardTarget.SELF
    );
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (p.hasRelic("Blue Candle")) {
      useBlueCandle(p);
    } else {
      AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
    }
  }

  public AbstractCard makeCopy() {
    return new Exhaustion_MRS();
  }

  public void upgrade() {
  }
}
