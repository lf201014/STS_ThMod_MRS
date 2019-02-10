package ThMod.monsters;

import ThMod.ThMod;
import ThMod.action.OrinsDebuffAction;
import ThMod.action.SummonFairyAction;
import ThMod.powers.monsters.InfernoClaw;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import org.apache.logging.log4j.Logger;

public class Orin extends AbstractMonster/* implements BaseMod.GetMonster */ {

  private static final Logger logger = ThMod.logger;
  public static final String ID = "Orin";
  public static final String NAME = "Orin";
  /*
  public static final String[] MOVES = {
      ""
  };
  public static final String[] DIALOG = {
      ""
  };
  */
  private boolean form1 = true;
  private boolean att = true;
  private boolean firstTurn = true;
  private static final int STAGE_1_HP = 76;
  private static final int S_1_HP = 82;
  private static final int STAGE_2_HP = 208;
  private static final int S_2_HP = 215;
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
  private int turnCount = 0;
  private static final String tempImgUrl = "img/monsters/Orin/Orin_.png";
  private static final String MODEL_HUMANOID_ATLAS = "img/monsters/Orin/Orin.atlas";
  private static final String MODEL_HUMANOID_JSON = "img/monsters/Orin/Orin.json";
  private static final String MODEL_CAT_ATLAS = "img/monsters/Orin/rincat.atlas";
  private static final String MODEL_CAT_JSON = "img/monsters/Orin/rincat.json";

  public Orin() {
    super(NAME, "Orin", STAGE_1_HP, 0.0F, -30.0F, 220.0F, 320.0F, tempImgUrl, -20.0F, -10.0F);
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

    loadAnimation(MODEL_CAT_ATLAS, MODEL_CAT_JSON, 2.5F);
    AnimationState.TrackEntry e = this.state.setAnimation(0, "newAnimation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
    /*
    this.stateData.setMix("Idle", "Sumon", 0.1F);
    this.stateData.setMix("Sumon", "Idle", 0.1F);
    this.stateData.setMix("Hurt", "Idle", 0.1F);
    this.stateData.setMix("Idle", "Hurt", 0.1F);
    this.stateData.setMix("Attack", "Idle", 0.1F);
    */
  }

  @Override
  public void usePreBattleAction() {
    AbstractDungeon.getCurrRoom().cannotLose = true;
    AbstractDungeon.actionManager.addToTop(
        new ApplyPowerAction(
            this, this, new InfernoClaw(this)
        )
    );
  }

  @Override
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

        AbstractDungeon.actionManager.addToBottom(
            new VFXAction(
                this,
                new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true)
        );

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
        for (int i = 0; i < SUMMON_FIRST; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new SummonFairyAction(this)
          );
        }
        break;
      case 8:
        //spawnFairy normal
        for (int i = 0; i < SUMMON; i++) {
          AbstractDungeon.actionManager.addToBottom(
              new SummonFairyAction(this)
          );
        }
        break;
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
        AbstractDungeon.actionManager.addToBottom(
            new RemoveSpecificPowerAction(
                p, this, "Wraith"
            )
        );
        break;
      default:
        logger.info(
            "Orin : takeTurn : error :action number "
                + this.nextMove
                + " should never be called."
        );
        break;
    }
    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
  }

  private void setDoubleTapAction() {
    logger.info("Orin : setDoubleTapAction : form1 : " + form1);
    if (this.form1) {
      setMove((byte) 1, Intent.ATTACK_DEFEND, this.catTap, 2, true);
    } else {
      setMove((byte) 4, Intent.ATTACK_DEFEND, this.doubleTap, 2, true);
    }
  }

  private void setMultiAttackAction() {
    logger.info("Orin : setMultiAttackAction : form1 : " + form1);
    if (this.form1) {
      setMove((byte) 3, Intent.ATTACK_BUFF, this.hellFireDmg, 6, true);
    } else {
      setMove((byte) 5, Intent.ATTACK, this.hellFireDmg, 4, true);
    }
  }

  private void setBuffAction() {
    logger.info("Orin : setBuffAction : form1 : " + form1);
    if (this.form1) {
      setMove((byte) 2, Intent.BUFF);
    } else {
      setMove((byte) 6, Intent.DEBUFF);
    }
  }

  private void setSummonAction() {
    logger.info("Orin : setSummonAction : firstTurn : ");/*
    if (this.firstTurn) {
      setMove((byte) 7, Intent.UNKNOWN);
    } else
      */
    {
      setMove((byte) 8, Intent.UNKNOWN);
    }
  }

  private void setExecuteAction() {
    setMove((byte) 9, Intent.ATTACK, this.executeDmg, 6, true);
  }

  private int fairyCount() {
    int aliveCount = 0;

    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
      if (m != this && !m.isDying) {
        ++aliveCount;
      }
    }

    return aliveCount;
  }

  private boolean canExecute() {
    if (!AbstractDungeon.player.hasPower("Wraith")) {
      return false;
    } else {
      return AbstractDungeon.player.getPower("Wraith").amount >= 10;
    }
  }

  @Override
  protected void getMove(int num) {
    logger.info(
        "Orin : GetMove : turnCount : " +
            turnCount +
            " ; num : " +
            num
    );
    if (this.form1) {
      turnCount++;
      switch (turnCount) {
        case 1:
          setBuffAction();
          return;
        case 2:
          setDoubleTapAction();
          return;
        case 3:
          setMultiAttackAction();
          return;
        default:
          logger.info("Orin : form 1 :getMove : error : turnCount :" + turnCount);
          break;
      }
      /*
      if (this.firstTurn) {
        setBuffAction();
        this.firstTurn = false;
        return;
      }
      */
      if (turnCount >= 4) {
        setMultiAttackAction();
        return;
      }
      if (num > 50) {
        setDoubleTapAction();
      } else {
        setBuffAction();
      }
    } else {
      int fairyCount = fairyCount();
      ThMod.logger.info("Orin : getMove : fairyCount : " + fairyCount);
      /*
      if (this.firstTurn) {
        setSummonAction();
        return;
      }
      */
      if (canExecute()) {
        setExecuteAction();
        return;
      }
      if (!firstTurn) {
        if (fairyCount < SUMMON_THRESHOLD) {
          setSummonAction();
          return;
        }
      } else {
        firstTurn = false;
      }
      logger.info("Orin : getMove : roll Action phase");
      int[] actions = new int[4];
      int act_cnt = 0;
      if ((fairyCount == SUMMON_THRESHOLD) && (!lastMove((byte) 7) && (!lastMove((byte) 8)))) {
        logger.info("Orin : getMove : roll action : adding summon action");
        actions[act_cnt] = 0;//summon : 0
        act_cnt++;
      }
      if (!lastMove((byte) 4)) {
        logger.info("Orin : getMove : roll action : adding double attack action");
        actions[act_cnt] = 1;//double : 1
        act_cnt++;
      }
      if (!lastMove((byte) 5)) {
        logger.info("Orin : getMove : roll action : adding multi attack action");
        actions[act_cnt] = 2;//multi : 2
        act_cnt++;
      }
      if (!lastMove((byte) 6)) {
        logger.info("Orin : getMove : roll action : adding debuff action");
        actions[act_cnt] = 3;//debuff : 3
        act_cnt++;
      }
      logger.info(
          "Orin : getMove : roll action : action count : " +
              act_cnt +
              " ; res code: " +
              num * act_cnt / 100 +
              " ; res : " +
              actions[num * act_cnt / 100]
      );
      switch (actions[num * act_cnt / 100]) {
        case 0:
          setSummonAction();
          break;
        case 1:
          setDoubleTapAction();
          break;
        case 2:
          setMultiAttackAction();
          break;
        case 3:
          setBuffAction();
          break;
        default:
          logger.info(
              "Orin : getMove : error : " +
                  actions[num / 100 * act_cnt] +
                  " is not a valid action code"
          );
          setBuffAction();
          break;
      }
    }
  }

  public void damage(DamageInfo info) {
    super.damage(info);
    if ((this.currentHealth <= 0) && (!this.halfDead)) {
      if (AbstractDungeon.getCurrRoom().cannotLose) {
        this.halfDead = true;
      }
      /*
      for (AbstractPower p : this.powers) {
        p.onDeath();
      }
      */
      for (AbstractRelic r : AbstractDungeon.player.relics) {
        r.onMonsterDeath(this);
      }
      AbstractDungeon.actionManager.addToTop(
          new ClearCardQueueAction()
      );
      att = false;
      setMove((byte) 3, Intent.UNKNOWN);
      createIntent();
      //AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[0]));
      setMove((byte) 3, Intent.UNKNOWN);
      applyPowers();
      //this.firstTurn = true;
    }
  }

  public void changeState(String key) {
    if (key.equals("TRANSFORM")) {
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
      this.form1 = false;

      AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
      AbstractDungeon.actionManager.addToBottom(new CanLoseAction());

      loadAnimation(MODEL_HUMANOID_ATLAS, MODEL_HUMANOID_JSON, 2.5F);
      AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
      e.setTime(e.getEndTime() * MathUtils.random());

      this.updateHitbox(0.0F, -30.0f, 220.0F, 450.0F);

      for (int i = 0; i < SUMMON_FIRST; i++) {
        AbstractDungeon.actionManager.addToBottom(
            new SummonFairyAction(this)
        );
      }

      if (att) {
        for (int i = 0; i < 6; i++) {
          AbstractDungeon.actionManager.addToTop(
              new DamageAction(
                  AbstractDungeon.player
                  , this.damage.get(1)
                  , AttackEffect.FIRE
                  , true
              )
          );
        }
      }

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
    if (!AbstractDungeon.getCurrRoom().cannotLose) {
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
/*
  @Override
  public AbstractMonster get() {
    return new Orin();
  }
  */
}
