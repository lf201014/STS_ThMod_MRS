package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import java.util.ArrayList;

public class ManaRampageAction
    extends AbstractGameAction {

  private boolean f2p;
  private ArrayList<AbstractCard> list = new ArrayList<>();
  AbstractPlayer p;

  public ManaRampageAction(int amount, boolean upgraded, boolean freeToPlay) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.amount = amount;
    this.f2p = freeToPlay;
    this.p = AbstractDungeon.player;
    for (int i = 0; i < this.amount; i++) {
      AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
      if (upgraded) {
        tmp.upgrade();
      }
      list.add(tmp);
    }
    ThMod.logger.info(
        "ManaRampageAction : Initialize complete ; card number :" +
            amount +
            " ; upgraded : " +
            upgraded
    );
  }

  public void update() {

    int amount;
    AbstractMonster target;

    if (list.isEmpty()) {
      this.isDone = true;
      return;
    }

    AbstractCard card = list.get(0);
    list.remove(0);
    target = AbstractDungeon.getMonsters().getRandomMonster(true);
/*
    if (target == null) {
      this.isDone = true;
      ThMod.logger.info("ManaRampageAction : done");
      return;
    }
    */
    AbstractDungeon.player.limbo.group.add(card);
    card.current_x = (Settings.WIDTH / 2.0F);
    card.current_y = (Settings.HEIGHT / 2.0F);
    card.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
    card.target_y = (Settings.HEIGHT / 2.0F);
    card.freeToPlayOnce = true;
    card.purgeOnUse = true;
    card.targetAngle = 0.0F;
    card.drawScale = 0.12F;
    ThMod.logger.info(
        "ManaRampageAction : card : " +
            card.cardID +
            " ; target : " +
            target.id
    );
    /*
    if (!card.canUse(AbstractDungeon.player, this.target)) {
      AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));
    } else */
    {
      card.applyPowers();
      AbstractDungeon.actionManager.currentAction = null;
      AbstractDungeon.actionManager.addToTop(this);
      AbstractDungeon.actionManager.cardQueue.add(
          new CardQueueItem(card, target)
      );
      if (!Settings.FAST_MODE) {
        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
      } else {
        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
      }
    }

    if (!this.f2p) {
      p.energy.use(EnergyPanel.totalCount);
    }
  }

}