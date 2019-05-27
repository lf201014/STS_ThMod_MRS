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
  AbstractPlayer p;
  boolean upgraded;

  public ManaRampageAction(int amount, boolean upgraded, boolean freeToPlay) {
    this.duration = Settings.ACTION_DUR_FAST;
    this.amount = amount;
    this.f2p = freeToPlay;
    this.p = AbstractDungeon.player;
    this.upgraded = upgraded;
    ThMod.logger.info(
        "ManaRampageAction : Initialize complete ; card number :" +
            amount +
            " ; upgraded : " +
            upgraded
    );
  }

  public void update() {

    for (int i =0;i<amount;i++){
      AbstractDungeon.actionManager.addToBottom(
          new PlayManaRampageCardAction(upgraded)
      );
    }

    if (!this.f2p) {
      p.energy.use(EnergyPanel.totalCount);
    }
    this.isDone = true;
  }

}