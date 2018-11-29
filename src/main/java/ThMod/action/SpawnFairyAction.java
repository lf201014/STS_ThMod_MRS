package ThMod.action;


import ThMod.monsters.ZombieFairy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReviveMonsterAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.beyond.SnakeDagger;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SpawnFairyAction
    extends AbstractGameAction {

  public static final float pos0X = 210.0F;
  public static final float pos0Y = 50.0F;
  public static final float pos1X = -220.0F;
  public static final float pos1Y = 90.0F;
  private static final float pos2X = 180.0F;
  private static final float pos2Y = 320.0F;
  private static final float pos3X = -250.0F;
  private static final float pos3Y = 310.0F;
  private static final float COORDINATE[][] = {
      {pos0X, pos0Y},
      {pos1X, pos1Y},
      {pos2X, pos2Y},
      {pos3X, pos3Y}
  };

  public SpawnFairyAction(AbstractMonster monster) {
    this.source = monster;
    this.duration = Settings.ACTION_DUR_XFAST;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_XFAST) {
      int count = 0;
      for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
        if (m != this.source) {
          if (m.isDying) {
            AbstractDungeon.actionManager.addToTop(
                new ApplyPowerAction(m, m, new MinionPower(this.source))
            );
            AbstractDungeon.actionManager.addToTop(
                new ReviveMonsterAction(m, this.source, false)
            );
            if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
              m.addPower(new StrengthPower(m, 1));
            }
            if (ModHelper.isModEnabled("Lethality")) {
              AbstractDungeon.actionManager.addToBottom(
                  new ApplyPowerAction(m, m, new StrengthPower(m, 3), 3)
              );
            }
            if (ModHelper.isModEnabled("Time Dilation")) {
              AbstractDungeon.actionManager.addToBottom(
                  new ApplyPowerAction(m, m, new SlowPower(m, 0))
              );
            }
            tickDuration();
            return;
          }
          count++;
        }
      }
      if (count < 4) {
        AbstractDungeon.actionManager.addToTop(
            new SpawnMonsterAction(
                new ZombieFairy(COORDINATE[count][0], COORDINATE[count][1]),
                true
            )
        );
      }
    }
    tickDuration();
  }
}

