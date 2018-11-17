package ThMod.action;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import java.util.Iterator;

public class OrbitalAction
    extends AbstractGameAction {

  private AbstractPlayer p;
  private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
  private static final String[] TEXT = uiStrings.TEXT;
  private ArrayList<AbstractCard> orbitals = new ArrayList<>();

  public OrbitalAction() {
    this.p = AbstractDungeon.player;
    setValues(this.p, AbstractDungeon.player, this.amount);
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
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
      AbstractCard car;
      if (this.p.exhaustPile.size() == 1) {
        if ((this.p.exhaustPile.group.get(0)).cardID.equals("Orbital")) {
          this.isDone = true;
          return;
        }
        car = this.p.exhaustPile.getTopCard();
        car.unfadeOut();
        this.p.hand.addToHand(car);
        if ((AbstractDungeon.player.hasPower("Corruption")) && (car.type
            == AbstractCard.CardType.SKILL)) {
          car.setCostForTurn(-9);
        }
        this.p.exhaustPile.removeCard(car);
        car.unhover();
        car.fadingOut = false;
        this.isDone = true;
        return;
      }
      for (AbstractCard ca : this.p.exhaustPile.group) {
        ca.stopGlowing();
        ca.unhover();
        ca.unfadeOut();
      }
      for (c = this.p.exhaustPile.group.iterator(); c.hasNext(); ) {
        AbstractCard derp = c.next();
        if (derp.cardID.equals("Orbital")) {
          c.remove();
          this.orbitals.add(derp);
        }
      }
      if (this.p.exhaustPile.isEmpty()) {
        this.p.exhaustPile.group.addAll(this.orbitals);
        this.orbitals.clear();
        this.isDone = true;
        return;
      }
      AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
      tickDuration();
      return;
    }
    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
      for (AbstractCard ca : AbstractDungeon.gridSelectScreen.selectedCards) {
        this.p.hand.addToHand(ca);
        if ((AbstractDungeon.player.hasPower("Corruption")) && (ca.type
            == AbstractCard.CardType.SKILL)) {
          ca.setCostForTurn(-9);
        }
        this.p.exhaustPile.removeCard(ca);
        ca.unhover();
      }
      AbstractDungeon.gridSelectScreen.selectedCards.clear();
      this.p.hand.refreshHandLayout();

      this.p.exhaustPile.group.addAll(this.orbitals);
      this.orbitals.clear();
      for (AbstractCard car : this.p.exhaustPile.group) {
        car.unhover();
        car.target_x = CardGroup.DISCARD_PILE_X;
        car.target_y = 0.0F;
      }
    }
    tickDuration();
  }
}

