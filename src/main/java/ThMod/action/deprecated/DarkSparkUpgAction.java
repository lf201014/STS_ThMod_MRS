package ThMod.action.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

@Deprecated
public class DarkSparkUpgAction
    extends AbstractGameAction {

  private static final UIStrings uiStrings = CardCrawlGame.languagePack
      .getUIString("ArmamentsAction");
  public static final String[] TEXT = uiStrings.TEXT;
  private AbstractPlayer p;
  private boolean upgraded = false;
  private int UPG = 2;

  public DarkSparkUpgAction(boolean armamentsPlus) {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.p = AbstractDungeon.player;
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgraded = armamentsPlus;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      if (this.upgraded) {
        UPG++;
      }
      for (AbstractCard c : this.p.hand.group) {
        if ((c.cardID == "Strike_MRS") || (c.cardID == "DarkSpark")
            || (c.cardID == "MachineGunSpark") || (c.cardID == "Spark")
            || (c.cardID == "DoubleSpark") || (c.cardID == "FinalSpark")
            || (c.cardID == "MasterSpark") || (c.cardID == "MusleSpark")) {
          c.baseDamage += UPG;
          c.flash();
          c.applyPowers();
        }
      }

      this.isDone = true;
      return;
    }

    tickDuration();
  }
}
