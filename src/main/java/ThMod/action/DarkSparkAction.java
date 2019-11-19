package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DarkSparkAction extends AbstractGameAction {
  private int[] multiDamage;

  public DarkSparkAction(int[] multiDamage, DamageType damageType){
    this.actionType = ActionType.EXHAUST;
    this.duration = Settings.ACTION_DUR_FAST;
    this.multiDamage = multiDamage;
    this.damageType = damageType;
  }

  @Override
  public void update() {
    this.isDone = true;
    AbstractPlayer p = AbstractDungeon.player;
    if (p.drawPile.isEmpty()){
      return;
    }
    AbstractCard card = p.drawPile.getTopCard();
    p.drawPile.moveToExhaustPile(card);
    if (card.type == CardType.ATTACK){
      addToTop(new DamageAllEnemiesAction(p,multiDamage,damageType,AttackEffect.FIRE));
    }
  }
}
