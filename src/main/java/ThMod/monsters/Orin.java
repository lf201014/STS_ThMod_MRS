package ThMod.monsters;

import ThMod.ThMod;
import ThMod.action.OrinsDebuffAction;
import ThMod.action.SpawnFairyAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import java.util.Iterator;
import org.apache.logging.log4j.Logger;

public class Orin extends AbstractMonster {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "Orin";
  public static final String NAME = "Orin";
  public static final String[] MOVES = {
      ""
  };
  public static final String[] DIALOG = {
      ""
  };
  private boolean form1 = true;
  private boolean firstTurn = true;
  public static final int STAGE_1_HP = 76;
  public static final int S_1_HP = 82;
  public static final int STAGE_2_HP = 208;
  public static final int S_2_HP = 215;
  private static final int STR = 4;
  private static final int STR_A = 5;
  private static final int DOUBLE_TAP = 10;
  private static final int CAT_TAP = 7;
  private static final int CAT_TAP_A = 8;
  private static final int HELL_FIRE = 5;
  private static final int HELL_FIRE_A = 6;
  private static final int DEBUFF = 2;
  private static final int DEBUFF_A = 3;
  private static final int SUMMON = 2;
  private static final int SUMMON_FIRST = 4;
  private static final int SUMMON_THRESHOLD = 2;
  private static final int EXECUTE = 8;
  private static final int EXECUTE_A = 10;
  private int doubleTap;
  private int catTap;
  private int hellFireDmg;
  private int strength;
  private int debuff;
  private int executeDmg;
  private boolean CatForm = true;

  public Orin() {
    super(NAME, "Orin", STAGE_1_HP, 0.0F, -30.0F, 220.0F, 320.0F, null, -20.0F, -10.0F);
    if (Settings.language == GameLanguage.ZHS) {
      this.name = "\u963f\u71d0";
    }
    if (AbstractDungeon.ascensionLevel >= 8) {
      setHp(S_1_HP);
    } else {
      setHp(STAGE_1_HP);
    }
    this.doubleTap = DOUBLE_TAP;
    if (AbstractDungeon.ascensionLevel >= 3) {
      this.catTap = CAT_TAP_A;
      this.hellFireDmg = HELL_FIRE_A;
      this.strength = STR_A;
      this.debuff = DEBUFF_A;
      this.executeDmg = EXECUTE_A;
    } else {
      this.catTap = CAT_TAP;
      this.hellFireDmg = HELL_FIRE;
      this.strength = STR;
      this.debuff = DEBUFF;
      this.executeDmg = EXECUTE;
    }
    this.damage.add(new DamageInfo(this, this.catTap));
    this.damage.add(new DamageInfo(this, this.hellFireDmg));
    this.damage.add(new DamageInfo(this, this.doubleTap));
    this.damage.add(new DamageInfo(this, this.executeDmg));

    this.type = AbstractMonster.EnemyType.ELITE;
    /*
    loadAnimation("images/monsters/theForest/mage/skeleton.atlas",
        "images/monsters/theForest/mage/skeleton.json", 1.0F);
    this.damage.add(new DamageInfo(this, DOUBLE_TAP));
    this.damage.add(new DamageInfo(this, HELL_FIRE));
    AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
    this.stateData.setMix("Idle", "Sumon", 0.1F);
    this.stateData.setMix("Sumon", "Idle", 0.1F);
    this.stateData.setMix("Hurt", "Idle", 0.1F);
    this.stateData.setMix("Idle", "Hurt", 0.1F);
    this.stateData.setMix("Attack", "Idle", 0.1F);
    e.setTime(e.getEndTime() * MathUtils.random());
    */
  }


  public void usePreBattleAction() {
    AbstractDungeon.getCurrRoom().cannotLose = true;
  }

  public void takeTurn() {
    AbstractPlayer p = AbstractDungeon.player;
    switch (this.nextMove) {
      case 1:
        //catBite
        AbstractDungeon.actionManager.addToBottom(
            new WaitAction(0.3F)
        );
        AbstractDungeon.actionManager.addToBottom(
            new VFXAction(
                new BiteEffect(
                    p.hb.cX + MathUtils.random(-50.0F, 50.0F) * Settings.scale
                    , p.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale
                    , Color.ORANGE.cpy()
                )
                , 0.1F
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(
                p
                , this.damage.get(0)
                , AbstractGameAction.AttackEffect.NONE
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new VFXAction(
                new BiteEffect(
                    p.hb.cX + MathUtils.random(-50.0F, 50.0F) * Settings.scale
                    , p.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale
                    , Color.ORANGE.cpy()
                )
                , 0.1F
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(
                p
                , this.damage.get(0)
                , AbstractGameAction.AttackEffect.NONE
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new GainBlockAction(this, this, this.catTap)
        );
        break;
      case 2:
        //buff
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                this
                , this
                , new StrengthPower(this, this.strength)
                , this.strength
            )
        );
        break;
      case 3:
        //hell fire
        //missing vfx
        for (int i = 0; i < 6; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(1)
                  , AttackEffect.FIRE
              )
          );
        }
        AbstractDungeon.actionManager.addToBottom(
            new ChangeStateAction(this, "TRANSFORM")
        );
        break;
      case 4:
        //doubleTap
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(
                p
                , this.damage.get(2)
                , AttackEffect.SLASH_DIAGONAL
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(
                p
                , this.damage.get(2)
                , AttackEffect.SLASH_DIAGONAL
            )
        );
        AbstractDungeon.actionManager.addToBottom(
            new GainBlockAction(this, this, this.doubleTap)
        );
        break;
      case 5:
        //quadruple
        for (int i = 0; i < 4; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(1)
                  , AttackEffect.FIRE
              )
          );
        }
        break;
      case 6:
        //debuff
        //missing vfx
        AbstractDungeon.actionManager.addToBottom(
            new OrinsDebuffAction(this.debuff, this)
        );
        break;
      case 7:
        //spawnFairy firstTurn
        for (int i = 0; i < 4; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new SpawnFairyAction(this)
          );
        }
        break;
      case 8:
        //spawnFairy normal
        AbstractDungeon.actionManager.addToBottom(
            new SpawnFairyAction(this)
        );
        AbstractDungeon.actionManager.addToBottom(
            new SpawnFairyAction(this)
        );
      case 9:
        //execute
        //missing vfx
        for (int i = 0; i < 6; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new DamageAction(
                  p
                  , this.damage.get(3)
                  , AttackEffect.FIRE
              )
          );
        }
        break;
      default:
        logger.info(
            "Orin : takeTurn : error :action number "
                + this.nextMove
                + " should never be called."
        );
    }

  }

  protected void getMove(int num) {
    if (this.form1) {
      if (this.firstTurn) {
        setMove((byte) 1, Intent.ATTACK, DOUBLE_TAP, 2, true);
        this.firstTurn = false;
        return;
      }
      if (num < 50) {
        if (!lastMove((byte) 2)) {
          setMove((byte) 2, AbstractMonster.Intent.ATTACK, 6, 4, true);
        } else {
          setMove((byte) 1, AbstractMonster.Intent.ATTACK, 20);
        }
      } else if (!lastTwoMoves((byte) 1)) {
        setMove((byte) 1, AbstractMonster.Intent.ATTACK, 20);
      } else {
        setMove((byte) 2, AbstractMonster.Intent.ATTACK, 6, 4, true);
      }
    } else {
      if (this.firstTurn) {
        setMove((byte) 5, AbstractMonster.Intent.ATTACK, 40);
        this.firstTurn = false;
        return;
      }
      if (num < 50) {
        if (!lastTwoMoves((byte) 6)) {
          setMove((byte) 6, AbstractMonster.Intent.ATTACK_DEBUFF, 18);
        } else {
          setMove((byte) 8, AbstractMonster.Intent.ATTACK, 10, 3, true);
        }
      } else if (!lastTwoMoves((byte) 8)) {
        setMove((byte) 8, AbstractMonster.Intent.ATTACK, 10, 3, true);
      } else {
        setMove((byte) 6, AbstractMonster.Intent.ATTACK_DEBUFF, 18);
      }
    }
  }

  public void damage(DamageInfo info) {
    super.damage(info);
    /*
    if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.output > 0)) {
      this.state.setAnimation(0, "Hit", false);
      if (this.form1) {
        this.state.addAnimation(0, "Idle_1", true, 0.0F);
      } else {
        this.state.addAnimation(0, "Idle_2", true, 0.0F);
      }
    }
    */
    if ((this.currentHealth <= 0) && (!this.halfDead)) {
      if (AbstractDungeon.getCurrRoom().cannotLose) {
        this.halfDead = true;
      }
      for (AbstractPower p : this.powers) {
        p.onDeath();
      }
      for (AbstractRelic r : AbstractDungeon.player.relics) {
        r.onMonsterDeath(this);
      }
      AbstractDungeon.actionManager.addToTop(
          new ClearCardQueueAction()
      );
      setMove((byte) 3, AbstractMonster.Intent.UNKNOWN);
      createIntent();
      //AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[0]));
      AbstractDungeon.actionManager.addToBottom(
          new SetMoveAction(this, (byte) 3, AbstractMonster.Intent.UNKNOWN)
      );
      applyPowers();
      this.firstTurn = true;
      this.form1 = false;
    }
  }

  public void changeState(String key) {
    switch (key) {
      case "TRANSFORM":
        if (AbstractDungeon.ascensionLevel >= 9) {
          this.maxHealth = S_2_HP;
        } else {
          this.maxHealth = STAGE_2_HP;
        }
        if ((Settings.isEndless) && (AbstractDungeon.player.hasBlight("ToughEnemies"))) {
          float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
          this.maxHealth = ((int) (this.maxHealth * mod));
        }
        if (ModHelper.isModEnabled("MonsterHunter")) {
          this.currentHealth = ((int) (this.currentHealth * 1.5F));
        }
        //this.state.setAnimation(0, "Idle_2", true);
        this.halfDead = false;

        AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
        AbstractDungeon.actionManager.addToBottom(new CanLoseAction());
        break;
        /*
      case "ATTACK_1":
        this.state.setAnimation(0, "Attack_1", false);
        this.state.addAnimation(0, "Idle_1", true, 0.0F);
        break;
      case "ATTACK_2":
        this.state.setAnimation(0, "Attack_2", false);
        this.state.addAnimation(0, "Idle_2", true, 0.0F);
        break;
        */
    }
  }

  public void die() {
    for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
      if ((!m.isDead) && (!m.isDying)) {
        AbstractDungeon.actionManager.addToTop(
            new HideHealthBarAction(m)
        );
        AbstractDungeon.actionManager.addToTop(
            new SuicideAction(m)
        );
        AbstractDungeon.actionManager.addToTop(
            new VFXAction(
                m
                , new InflameEffect(m)
                , 0.2F
            )
        );
      }
    }
  }

}
