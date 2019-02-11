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
import org.apache.logging.log4j.Logger;

public class ZombieFairy extends AbstractMonster {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "ZombieFairy";
  public static final String NAME = "Zombie Fairy";
  private static final int HP = 12;
  private static final int HP_ = 16;
  private static final int HP_A = 14;
  private static final int HP_A_ = 18;
  private static final int DMG = 8;
  private static final int BLOCK = 4;
  private static final int BLOCK_UPG = 10;
  private static final byte POWER_UP = 3;
  private static final AbstractPlayer p = AbstractDungeon.player;
  public int turnNum = 0;
  private static final String MODEL_ATLAS = "img/monsters/ZombieFairy/ZombieFairy.atlas";
  private static final String MODEL_JSON = "img/monsters/ZombieFairy/ZombieFairy.json";

  public ZombieFairy(float x, float y) {
    super(NAME, ID, HP, 0.0F, 0.0F, 140.0F, 170.0f, null, x, y + 25.0F);
    if (AbstractDungeon.ascensionLevel >= 8) {
      this.setHp(HP_A, HP_A_);
    } else {
      this.setHp(HP, HP_);
    }
    this.damage.add(new DamageInfo(this, DMG));

    loadAnimation(MODEL_ATLAS, MODEL_JSON, 3.0F);
    AnimationState.TrackEntry e = this.state.setAnimation(0, "newAnimation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
  }

  public ZombieFairy() {
    this(0.0f, 0.0f);
  }

  public void takeTurn() {
    switch (this.nextMove) {
      case 1:
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(
                p
                , this.damage.get(0)
            )
        );
        if (this.turnNum >= POWER_UP) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(0)
              )
          );
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(0)
              )
          );
        }
        break;
      case 2:
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
          int block = BLOCK;
          if (this.turnNum >= 3) {
            block = BLOCK_UPG;
          }
          if (!m.isDying) {
            AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(m, this, block)
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
    if (this.turnNum < 3) {
      setMove((byte) 1, Intent.ATTACK_DEBUFF, DMG);
    } else {
      setMove((byte) 1, Intent.ATTACK_DEBUFF, DMG, 3, true);
    }
  }

  private void setDefendAction() {
    setMove((byte) 2, Intent.DEFEND);
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
