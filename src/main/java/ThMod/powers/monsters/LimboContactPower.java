package ThMod.powers.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LimboContactPower
    extends AbstractPower {

  public static final String POWER_ID = "LimboContact";
  private static final PowerStrings powerStrings =
      CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public LimboContactPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    updateDescription();
    this.img = new Texture("img/powers/poison.png");
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0];
  }

  @Override
  public void stackPower(int amount){
  }

  public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
    AbstractPlayer p = AbstractDungeon.player;
    if (damageAmount > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              p
              , p
              , new WraithPower(p, 1)
              , 1
          )
      );
    }
  }
}
