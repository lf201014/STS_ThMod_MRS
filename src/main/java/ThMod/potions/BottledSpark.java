package ThMod.potions;

import ThMod.cards.derivations.Spark;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BottledSpark extends AbstractPotion {

  public static final String POTION_ID = "BottledSpark";
  private static final PotionStrings potionStrings =
      CardCrawlGame.languagePack.getPotionString(POTION_ID);
  public static final String NAME = potionStrings.NAME;
  public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

  public BottledSpark() {
    super(
        NAME,
        POTION_ID,
        PotionRarity.COMMON,
        PotionSize.FAIRY,
        PotionColor.SWIFT
    );
    this.potency = getPotency();
    this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
    this.tips.clear();
    this.tips.add(new PowerTip(this.name,this.description));
  }

  @Override
  public void use(AbstractCreature abstractCreature) {

    AbstractCard shiv = new Spark();
    if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
      addToBot(new MakeTempCardInHandAction(shiv.makeStatEquivalentCopy(), this.potency));
    }
  }

  @Override
  public int getPotency(int i) {
    return 3;
  }

  @Override
  public AbstractPotion makeCopy() {
    return new BottledSpark();
  }
}
