package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ManaConvectionAction extends AbstractGameAction {

  private static final UIStrings uiStrings = CardCrawlGame.languagePack
      .getUIString("ExhaustAction");
  private static final String[] TEXT = uiStrings.TEXT;
  private AbstractPlayer p;
  private int num;

  public ManaConvectionAction(int number) {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.p = AbstractDungeon.player;
    this.duration = Settings.ACTION_DUR_FAST;
    this.num = number;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      if (this.p.hand.size() == 0) {
        this.isDone = true;
        return;
      }
      AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.num, true, true);
      tickDuration();
      return;
    }
    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      int cnt = 0;
      for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
        cnt += 1;
        /*
        if ((c instanceof Burn)) {
          cnt++;
        }
        */
        this.p.hand.moveToExhaustPile(c);
      }
      AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
      AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
      if (cnt > 0) {
        AbstractDungeon.actionManager.addToTop(
            new GainEnergyAction(cnt)
        );
      }
    }

    tickDuration();
  }

}
