package ThMod.action.deprecated;

import java.util.ArrayList;
import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

@Deprecated
public class OrbitalAction extends AbstractGameAction {

  private AbstractPlayer p;
  private static final UIStrings uiStrings = CardCrawlGame.languagePack
      .getUIString("DiscardAction");
  public static final String[] TEXT = uiStrings.TEXT;
  private ArrayList<AbstractCard> exhumes = new ArrayList<AbstractCard>();
  public boolean upgraded;

  public OrbitalAction(boolean upgraded) {
    this.p = AbstractDungeon.player;
    setValues(this.p, AbstractDungeon.player, this.amount);
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = upgraded;
  }

  public void update() {
    Iterator<AbstractCard> c;
    if (this.duration == Settings.ACTION_DUR_FAST) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.createHandIsFullDialog();
        this.isDone = true;
        return;
      }
      if (this.p.exhaustPile.isEmpty()) {
        this.isDone = true;
        return;
      }
      int cnt = 1;
      if (this.upgraded) {
        cnt = 2;
      }
      if (this.p.exhaustPile.size() <= cnt) {
        boolean avail = false;
        for (int i = 0; i < cnt; i++) {
          if (!(p.exhaustPile.group.get(0)).cardID.equals("Orbital")){
            avail = true;
          }
        }
        if (avail) {
          this.isDone = true;
          return;
        }
        /*
        AbstractCard c1;
        c1 = this.p.exhaustPile.getTopCard();
        c1.unfadeOut();
        this.p.discardPile.addToRandomSpot(c1);
        this.p.exhaustPile.removeCard(c1);
        c1.unhover();
        c1.fadingOut = false;
        this.isDone = true;
        return;
        */
      }
      for (AbstractCard c11 : this.p.exhaustPile.group) {
        c11.stopGlowing();
        c11.unhover();
        c11.unfadeOut();
      }
      for (c = this.p.exhaustPile.group.iterator(); c.hasNext(); ) {
        AbstractCard derp = c.next();
        if (derp.cardID.equals("Orbital")) {
          c.remove();
          this.exhumes.add(derp);
        }
      }
      if (this.p.exhaustPile.isEmpty()) {
        this.p.exhaustPile.group.addAll(this.exhumes);
        this.exhumes.clear();
        this.isDone = true;
        return;
      }
      AbstractDungeon.gridSelectScreen.open(
          this.p.exhaustPile,
          cnt,
          TEXT[0],
          false,
          false,
          true,
          false
      );
      tickDuration();
      return;
    }
    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
      for (AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
        this.p.discardPile.addToRandomSpot(c1);
        this.p.exhaustPile.removeCard(c1);
        c1.unhover();
      }
      AbstractDungeon.gridSelectScreen.selectedCards.clear();
      this.p.hand.refreshHandLayout();

      this.p.exhaustPile.group.addAll(this.exhumes);
      this.exhumes.clear();
      for (AbstractCard c3 : this.p.exhaustPile.group) {
        c3.unhover();
        c3.target_x = CardGroup.DISCARD_PILE_X;
        c3.target_y = 0.0F;
      }
    }
    tickDuration();
  }
}