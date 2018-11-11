package ThMod.powers.Marisa;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod.ThMod;

public class PropBagPower extends AbstractPower {

  public static final String POWER_ID = "PropBagPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private AbstractRelic r;
  private static int IdOffset;
  private AbstractPlayer p;
  private String rName;

  public PropBagPower(AbstractCreature owner, AbstractRelic r) {
    this.name = NAME;
    this.ID = POWER_ID + IdOffset;
    this.owner = owner;
    IdOffset++;
    this.amount = -1;
    this.type = AbstractPower.PowerType.BUFF;
    this.img = new Texture("img/powers/diminish.png");
    this.r = r;
    this.p = AbstractDungeon.player;
    this.rName = r.name;
    ThMod.logger.info("PropBagPower : Granting relic : " + this.rName);
    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
        Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r
    );
    r.atBattleStart();
    updateDescription();
  }

  @Override
  public void stackPower(int stackAmount) {

  }

  public void onVictory() {
    p.loseRelic(r.relicId);
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.rName + DESCRIPTIONS[1]);
  }
}