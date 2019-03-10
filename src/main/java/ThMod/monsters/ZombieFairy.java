package ThMod.monsters;

import ThMod.ThMod;
import ThMod.powers.monsters.LimboContactPower;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.Logger;

public class ZombieFairy extends AbstractMonster {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "ZombieFairy";
  public static final String NAME = "Zombie Fairy";
  private static final int HP = 12;
  private static final int HP_ = 16;
  private static final int HP_A = 14;
  private static final int HP_A_ = 18;
  private static final int DMG = 6;
  private static final int DMG_A = 8;
  private static final int DMG_MULTI = 6;
  private static final int DMG_MULTI_A = 8;
  private static final int BLOCK = 4;
  private static final int BLOCK_A = 5;
  private static final int BLOCK_UPG = 12;
  private static final int BLOCK_UPG_A = 15;
  private static final byte POWER_UP = 3;
  public int turnNum = 0;
  private int block, block_upg;
  private static final String MODEL_ATLAS = "img/monsters/ZombieFairy/ZombieFairy.atlas";
  private static final String MODEL_JSON = "img/monsters/ZombieFairy/ZombieFairy.json";

  public ZombieFairy(float x, float y) {
    super(NAME, ID, HP, 0.0F, 0.0F, 140.0F, 170.0f, null, x, y + 25.0F);
    if (AbstractDungeon.ascensionLevel >= 8) {
      this.setHp(HP_A, HP_A_);
    } else {
      this.setHp(HP, HP_);
    }
    if (AbstractDungeon.ascensionLevel >= 2) {
      this.damage.add(new DamageInfo(this, DMG_A));
      this.damage.add(new DamageInfo(this, DMG_MULTI_A));
      this.block = BLOCK_A;
      this.block_upg = BLOCK_UPG_A;
    } else {
      this.damage.add(new DamageInfo(this, DMG));
      this.damage.add(new DamageInfo(this, DMG_MULTI));
      this.block = BLOCK;
      this.block_upg = BLOCK_UPG;
    }

    loadAnimation(MODEL_ATLAS, MODEL_JSON, 3.0F);
    AnimationState.TrackEntry e = this.state.setAnimation(0, "newAnimation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
  }

  public ZombieFairy() {
    this(0.0f, 0.0f);
  }

  public void takeTurn() {
    AbstractPlayer p = AbstractDungeon.player;
    switch (this.nextMove) {
      case 1:
        logger.info(
            "ZombieFairy : take Turn : Attack : turnNum : "
                + turnNum
                + " ; damage : "
                + this.damage.get(0).base
                + " ; ActionCancel check:"
                + " ; target null : "
                + (p == null)
                + " ; source null : "
                + (this.damage.get(0).owner != null)
                + " ; source dying : "
                + (this.damage.get(0).owner.isDying)
                + " ; target dead or escaped : "
                + (p.isDeadOrEscaped())
        );

        if (this.turnNum >= POWER_UP) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(1)
              )
          );
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(1)
              )
          );
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(1)
              )
          );
        } else {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(0)
              )
          );
        }
        break;
      case 2:
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
          int block = this.block;
          if (this.turnNum >= 3) {
            block = this.block_upg;
          }
          if (!m.isDeadOrEscaped()) {
            AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(m, this, block)
            );
            AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, null, new StrengthPower(m, 1), 1)
            );
          }
        }
        break;
      default:
        logger.info(
            "ZombieFairy : takeTurn : Error : Action number "
                + this.nextMove
                + " should never be called."
        );
        break;
    }
    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
  }

  public void usePreBattleAction() {
    AbstractDungeon.actionManager.addToTop(
        new ApplyPowerAction(
            this
            , this
            , new LimboContactPower(this))
    );
    AbstractDungeon.actionManager.addToTop(
        new ApplyPowerAction(
            this
            , this
            , new FlightPower(this, 99))
    );
  }

  protected void getMove(int num) {
    ThMod.logger.info("ZombieFairy : GetMove : num : " + num + " ; turnNum : " + turnNum);
    this.turnNum++;
    if (num <= 50) {
      setAttackAction();
    } else {
      setDefendAction();
    }
  }

  private void setAttackAction() {
    if (this.turnNum < 2) {
      setMove((byte) 1, Intent.ATTACK_DEBUFF, DMG);
    } else {
      setMove((byte) 1, Intent.ATTACK_DEBUFF, DMG_MULTI, 3, true);
    }
  }

  private void setDefendAction() {
    setMove((byte) 2, Intent.DEFEND_BUFF);
  }

  public void revive() {
    logger.info("Zombie Fairy : reviving");
    loadAnimation(MODEL_ATLAS, MODEL_JSON, 3.0F);
    AnimationState.TrackEntry e = this.state.setAnimation(0, "newAnimation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
    this.turnNum = 0;
    logger.info("Zombie Fairy : done reviving ; turnCount : " + turnNum);
  }

  @Override
  public void die() {
    super.die();
    this.turnNum = 0;
  }

  /*
  public void changeState(String stateName) {

  }

  public void damage(DamageInfo info) {
  }
  */
}
