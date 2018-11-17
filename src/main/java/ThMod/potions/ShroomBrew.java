package ThMod.potions;

import ThMod.action.FungusSplashAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ShroomBrew
    extends AbstractPotion {

  public static final String POTION_ID = "ShroomBrew";
  private static final PotionStrings potionStrings =
      CardCrawlGame.languagePack.getPotionString(POTION_ID);
  public static final String NAME = potionStrings.NAME;
  public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

  public ShroomBrew() {
    super(
        NAME,
        POTION_ID,
        PotionRarity.UNCOMMON,
        PotionSize.FAIRY,
        PotionColor.SMOKE
    );
    this.potency = getPotency();
    this.description = DESCRIPTIONS[0];
    this.isThrown = true;
    this.targetRequired = true;
    this.tips.add(
        new PowerTip(this.name, this.description)
    );
    this.tips.add(
        new PowerTip(DESCRIPTIONS[1], DESCRIPTIONS[2])
    );
  }

  public void use(AbstractCreature target) {
    if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
      AbstractDungeon.actionManager.addToBottom(
          new FungusSplashAction(target)
      );
    }
  }

  public AbstractPotion makeCopy() {
    return new ShroomBrew();
  }

  public int getPotency(int ascensionLevel) {
    return 1;
  }
}
