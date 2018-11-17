package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;
import ThMod.action.DiscToHandATKOnly;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

@Deprecated
public class NebulaPower
    extends AbstractPower {

  public static final String POWER_ID = "Nebula";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private int cnt;

  public NebulaPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/defyDanger.png");
  }

  public void stackPower(int stackAmount) {
    super.stackPower(stackAmount);
    this.cnt += stackAmount;
  }

  @Override
  public int onAttacked(DamageInfo info, int damageAmount) {
    ThMod.logger.info(
        "NebulaPower : onAttacked : currentBlock : " +
            owner.currentBlock +
            " ; DamageInfo : " +
            info.base +
            " ; damageAmount : " +
            damageAmount
    );
    if (((this.owner.currentBlock > 0) && (info.base > 0) && (info.type != DamageType.HP_LOSS))) {
      flash();
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              this.owner,
              this.owner,
              new PlatedArmorPower(this.owner, this.amount),
              this.amount
          )
      );
    }
    return damageAmount;
  }

  public void updateDescription() {
    this.description = (
        DESCRIPTIONS[0]
            + this.amount
            + DESCRIPTIONS[1]
    );
  }
}

