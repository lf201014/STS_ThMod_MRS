package ThMod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import basemod.abstracts.CustomRelic;

public class ShroomBag extends CustomRelic {

  public static final String ID = "ShroomBag";
  private static final String IMG = "img/relics/ShroomBag.png";
  private static final String IMG_OTL = "img/relics/outline/ShroomBag.png";

  public ShroomBag() {
    super(
        ID,
        new Texture(IMG),
        new Texture(IMG_OTL),
        RelicTier.COMMON,
        LandingSound.FLAT
    );
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new ShroomBag();
  }

  public void onEquip() {
    AbstractDungeon.effectList.add(
        new ShowCardAndObtainEffect(
            new Parasite(),
            Settings.WIDTH / 2.0F,
            Settings.HEIGHT / 2.0F)
    );
    AbstractDungeon.effectList.add(
        new ShowCardAndObtainEffect(
            new Parasite(),
            Settings.WIDTH / 2.0F,
            Settings.HEIGHT / 2.0F)
    );
  }
    /*
    @Override
    public void onDrawOrDiscard() {
    	ThMod.logger.info("ShroomBag : onDrawOrDiscard : replaceParasite");
    	replaceParasite();
    }
    
    @Override
    public void onRefreshHand() {
    	ThMod.logger.info("ShroomBag : onRefreshHand : replaceParasite");
    	replaceParasite();    	
    }
    
    @Override
    public void atTurnStartPostDraw() {
    	ThMod.logger.info("ShroomBag : atTurnStartPostDraw : replaceParasite");
    	replaceParasite();    	
    }
    
	private void replaceParasite() {
		ArrayList<AbstractCard> temp = new ArrayList<AbstractCard>();
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c instanceof Parasite) {
				temp.add(c);
			}
		}
		while (temp.size() > 0){
	    	ThMod.logger.info("ShroomBag : replaceParasite : Replacing");
			this.flash();
			AbstractCard c = temp.remove(0);
			AbstractDungeon.player.hand.removeCard(c);
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Parasite_MRS(), 1));
		}
	}
	*/
}