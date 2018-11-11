package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;
import ThMod.cards.derivations.GuidingStar;

import com.megacrit.cardcrawl.localization.PowerStrings;

public class PolarisUniquePower extends AbstractPower {

  public static final String POWER_ID = "PolarisUniquePower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private AbstractPlayer p;
  public boolean Gain;

  public PolarisUniquePower(AbstractCreature owner) {
    ThMod.logger.info("PolarisUniquePower : Init");
    this.name = NAME;
    this.ID = POWER_ID;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/transmute.png");
    this.p = AbstractDungeon.player;
    this.Gain = false;
    this.owner = owner;

    ThMod.logger.info("PolarisUniquePower : Done initing");
  }


  public void stackPower(int stackAmount) {
    ThMod.logger.info("PolarisUniquePower : StackPower");
  }

  public void atStartOfTurnPostDraw() {
    ThMod.logger.info("PolarisUniquePower : Checking");

    for (AbstractCard c : p.drawPile.group) {
      if (c instanceof GuidingStar) {
        this.Gain = true;
      }
    }
    ThMod.logger.info("PolarisUniquePower : Result : " + Gain);
    if (Gain) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }
    this.Gain = false;

    ThMod.logger.info("PolarisUniquePower : Done Checking");
  }

  public void updateDescription() {
    ThMod.logger.info("PolarisUniquePower : updating Description");
    this.description = (DESCRIPTIONS[0]);
    ThMod.logger.info("PolarisUniquePower : Done updating Description");
  }
}