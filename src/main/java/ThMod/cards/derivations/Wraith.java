package ThMod.cards.derivations;

//public class Wraith


import ThMod.powers.monsters.WraithPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Wraith
    extends CustomCard {

  public static final String ID = "Wraith";
  private static final CardStrings cardStrings =
      CardCrawlGame.languagePack.getCardStrings("Wraith");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = -2;
  private static final String imgUrl = "img/cards/wraith.png";

  public Wraith() {
    super(
        ID,
        NAME,
        imgUrl,
        COST,
        DESCRIPTION,
        CardType.CURSE,
        CardColor.CURSE,
        CardRarity.SPECIAL,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (this.dontTriggerOnUseCard) {
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(
              AbstractDungeon.player,
              AbstractDungeon.player,
              new WraithPower(AbstractDungeon.player, 1),
              1
          )
      );
    }
  }

  public void triggerWhenDrawn() {
    AbstractDungeon.actionManager.addToBottom(
        new SetDontTriggerAction(this, false)
    );
  }

  public void triggerOnEndOfTurnForPlayingCard() {
    this.dontTriggerOnUseCard = true;
    AbstractDungeon.actionManager.cardQueue.add(
        new CardQueueItem(this, true)
    );
  }

  public AbstractCard makeCopy() {
    return new Wraith();
  }

  public void upgrade() {
  }
}
