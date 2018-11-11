package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod.ThMod;

public class StarDustReverieAction
    extends AbstractGameAction {

  private AbstractPlayer p;
  private boolean upgraded;

  public StarDustReverieAction(boolean upgraded) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
    this.p = AbstractDungeon.player;
  }

  public void update() {
    this.isDone = false;
    int cnt = 0;

    ThMod.logger.info("StarDustReverieAction : player hand size : " + p.hand.size());

    if (!p.hand.isEmpty()) {
      while (!p.hand.isEmpty()) {
        AbstractCard c = p.hand.getTopCard();
        ThMod.logger.info("StarDustReverieAction : moving " + c.cardID);
        p.hand.moveToDeck(c, true);
        cnt++;
        ThMod.logger.info("StarDustReverieAction : Counter : " + cnt);
      }
    } else {
      this.isDone = true;
      return;
    }

    p.drawPile.shuffle();

    for (AbstractRelic r : p.relics) {
      r.onShuffle();
    }

    for (int i = 0; i < cnt; i++) {

      AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();

      ThMod.logger.info("StarDustReverieAction : adding " + c.cardID);

      if (this.upgraded) {
        c.upgrade();
      }
      ThMod.logger.info(
          "StarDustReverieAction : checking : Exhaust : " + c.exhaust +
              " ; Ethereal : " + c.isEthereal +
              " ; Upgraded : " + c.upgraded
      );
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(c, 1)
      );
    }
		/*
	    AbstractDungeon.actionManager.addToBottom(
	    		new HandCheckAction(this.upgraded)
	    		);
	    */
    this.isDone = true;
  }
}