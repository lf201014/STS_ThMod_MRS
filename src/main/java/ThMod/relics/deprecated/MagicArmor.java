package ThMod.relics.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

@Deprecated
public class MagicArmor extends CustomRelic {

  public static final String ID = "MagicArmor";
  private static final String IMG = "img/relics/test4.png";

  public MagicArmor() {
    super(ID, new Texture(IMG), RelicTier.UNCOMMON, LandingSound.FLAT);
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new MagicArmor();
  }

  public void onCardDraw(AbstractCard drawnCard) {
    if ((drawnCard.type == CardType.STATUS) || (drawnCard.type == CardType.CURSE)) {
      AbstractDungeon.actionManager.addToBottom(
          new RelicAboveCreatureAction(AbstractDungeon.player, this)
      );
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 5)
      );
    }
  }
}