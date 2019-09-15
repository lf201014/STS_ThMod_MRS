package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import ThMod.ThMod;
import java.util.ArrayList;

public class MagicChantAction extends AbstractGameAction {

  private static final UIStrings uiStrings = CardCrawlGame.languagePack
      .getUIString("BetterToHandAction");
  public static final String[] TEXT = uiStrings.TEXT;
  private AbstractPlayer player;
  private int numberOfCards;

  public MagicChantAction() {
    this.player = AbstractDungeon.player;
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = (this.startDuration = Settings.ACTION_DUR_FAST);
    this.numberOfCards = 1;
  }

  public void update() {
    CardGroup temp;
    if (this.duration == this.startDuration) {
      if (this.player.drawPile.isEmpty()) {
        this.isDone = true;
        return;
      }

      if (this.player.drawPile.size() <= this.numberOfCards) {
        ArrayList<AbstractCard> cardsToMove = new ArrayList();
        for (AbstractCard c : this.player.drawPile.group) {
          if (c.canUpgrade()){
            c.upgrade();
          }
          cardsToMove.add(c);
        }
        for (AbstractCard c : cardsToMove) {
          if (this.player.hand.size() == 10) {
            this.player.drawPile.moveToDiscardPile(c);
            this.player.createHandIsFullDialog();
          } else {
            this.player.drawPile.moveToHand(c, this.player.drawPile);
          }
        }
        this.isDone = true;
        return;
      }

      temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : this.player.drawPile.group) {
        temp.addToTop(c);
      }
      temp.sortAlphabetically(true);
      temp.sortByRarityPlusStatusCardType(false);

      AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], true);

      tickDuration();
      return;
    }

    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
      for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
        if (c.canUpgrade()){
          c.upgrade();
        }
        if (this.player.hand.size() == 10) {
          this.player.drawPile.moveToDiscardPile(c);
          this.player.createHandIsFullDialog();
        } else {
          this.player.drawPile.moveToHand(c, this.player.drawPile);
        }
      }
      AbstractDungeon.gridSelectScreen.selectedCards.clear();
      AbstractDungeon.player.hand.refreshHandLayout();
    }
    tickDuration();
  }
}
