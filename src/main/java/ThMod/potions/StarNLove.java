package ThMod.potions;

import ThMod.powers.Marisa.ChargeUpPower;
import ThMod.powers.Marisa.PulseMagicPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class StarNLove extends AbstractPotion {

  public static final String POTION_ID = "StarNLove";
  private static final PotionStrings potionStrings =
      CardCrawlGame.languagePack.getPotionString(POTION_ID);
  public static final String NAME = potionStrings.NAME;
  public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

  public StarNLove() {
    super(
        NAME,
        POTION_ID,
        PotionRarity.RARE,
        PotionSize.HEART,
        PotionColor.SWIFT
    );
    this.potency = getPotency();
    this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
    this.tips.clear();
    this.tips.add(new PowerTip(this.name,this.description));
  }

  @Override
  public void use(AbstractCreature abstractCreature) {
    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
        new PulseMagicPower(AbstractDungeon.player), 1));
    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
        new ChargeUpPower(AbstractDungeon.player, this.getPotency()), this.getPotency()));
  }

  @Override
  public int getPotency(int i) {
    return 8;
  }

  @Override
  public AbstractPotion makeCopy() {
    return null;
  }
}
