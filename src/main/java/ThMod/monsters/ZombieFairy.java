package ThMod.monsters;

import ThMod.ThMod;
import ThMod.powers.monsters.LimboContactPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.Logger;

public class ZombieFairy extends AbstractMonster {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "ZombieFairy";
  public static final String NAME = "Zombie Fairy";
  private static final int HP = 18;
  private static final int HP_A = 20;
  private static final int DMG = 8;
  private static final int BLOCK = 4;
  private static final int BLOCK_UPG = 10;
  private static final byte POWER_UP = 3;
  private static final AbstractPlayer p = AbstractDungeon.player;
  private int turnNum = 0;

  public ZombieFairy(float x, float y) {
    super(
        NAME
        , "Dagger"
        , HP
        , 0.0F
        , -50.0F
        , 140.0F
        , 130.0F
        , null
        , x
        , y + 25.0F
    );
    if (AbstractDungeon.ascensionLevel >= 8) {
      this.setHp(HP_A);
    }
    this.damage.add(new DamageInfo(this, DMG));
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
  }

  public void usePreBattleAction() {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            this
            , this
            , new LimboContactPower(this))
    );
  }

  protected void getMove(int num) {
    if (num <= 50) {
      if (!lastMove((byte) 1)) {
        setAttackAction();
      } else {
        setDefendAction();
      }
    } else {
      if (!lastMove((byte) 2)) {
        setDefendAction();
      } else {
        setAttackAction();
      }
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

  public void changeState(String stateName) {

  }

  public void damage(DamageInfo info) {

  }

}
