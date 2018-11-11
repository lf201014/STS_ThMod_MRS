package ThMod.relics.deprecated;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;

import ThMod.ThMod;
import basemod.abstracts.CustomRelic;

@Deprecated
public class Cape extends CustomRelic {

  public static final String ID = "Cape_1";
  private static final String IMG = "img/relics/vCore.png";
  private static final String IMG_USED = "img/relics/usedvCore.png";
  private static final String IMG_OTL = "img/relics/outline/vCore.png";
  private boolean avail;
  private boolean Rclick;
  private boolean RclickStart;
  private boolean JustActivated;
  Map<AbstractCard, int[]> cardPrice = new HashMap<AbstractCard, int[]>();
  Map<AbstractRelic, int[]> relicPrice = new HashMap<AbstractRelic, int[]>();
  Map<AbstractPotion, int[]> potionPrice = new HashMap<AbstractPotion, int[]>();

  public Cape() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.RARE,
        LandingSound.MAGICAL
    );
    this.counter = 2;
    this.avail = false;
    this.Rclick = false;
    this.RclickStart = false;
    this.JustActivated = false;
  }

  public void onEnterRoom(AbstractRoom room) {
    if (this.counter <= 0) {
      return;
    }
    if ((room instanceof ShopRoom)) {
      ThMod.logger.info("Cape_1 : onEnterRoom : ShopRoom detected .");
      this.avail = true;
      flash();
      this.pulse = true;
    } else {
      this.avail = false;
      this.pulse = false;
    }
  }

  public void update() {
    super.update();
    if ((this.counter <= 0) || (this.usedUp)) {
      ThMod.logger.info("Cape_1 : update : returning for being used");
      return;
    }
    if (!this.isObtained) {
      ThMod.logger.info("Cape_1 : update : returning for not obtained");
      return;
    }
    if ((this.RclickStart) && (InputHelper.justReleasedClickRight)) {
      if (this.hb.hovered) {
        ThMod.logger.info("Cape_1 : update : hovered");
        this.Rclick = true;
      }
      this.RclickStart = false;
    }
    if ((this.isObtained) && (this.hb != null) && (this.hb.hovered)
        && (InputHelper.justClickedRight)) {
      ThMod.logger.info("Cape_1 : update : right click detected");
      this.RclickStart = true;
    }
    if (this.Rclick) {
      ThMod.logger.info("Cape_1 : update : Calling OnRclick");
      this.Rclick = false;
      OnRclick();
    }
  }

  private void OnRclick() {
    if (
        (this.counter <= 0)
            || (!this.avail)
            || (!(AbstractDungeon.getCurrRoom() instanceof ShopRoom))
    ) {
      return;
    }

    this.flash();
    this.counter--;
    this.pulse = false;
    this.avail = false;
    this.JustActivated = true;
    AbstractDungeon.actionManager.addToBottom(
        new RelicAboveCreatureAction(
            AbstractDungeon.player,
            this
        )
    );
    recordPrice();
    AbstractDungeon.shopScreen.applyDiscount(0, true);
    AbstractDungeon.shopScreen.update();
    if (this.counter <= 0) {
      this.img = ImageMaster.loadImage(IMG_USED);
      usedUp();
      this.counter = -2;
    }
  }

  private void recordPrice() {
    if (!(AbstractDungeon.getCurrRoom() instanceof ShopRoom)) {
      return;
    }
  }

  @Override
  public void onSpendGold() {
    if ((this.counter <= 0) || (this.usedUp) || (!this.JustActivated)) {
      ThMod.logger.info(
          "Cape_1 : OnSpendGold : returning :"
              + " Counter : " + this.counter
              + " usedUp : " + this.usedUp
              + " JustActivated : " + this.JustActivated
      );
      return;
    }

    ShopScreen currShop = AbstractDungeon.shopScreen;

    ShopScreen.actualPurgeCost = ShopScreen.purgeCost;

    currShop.applyDiscount(1.0F, true);

    if (AbstractDungeon.ascensionLevel >= 16) {
      currShop.applyDiscount(1.15F, false);
    }
    if (AbstractDungeon.player.hasRelic("The Courier")) {
      currShop.applyDiscount(0.8F, true);
    }
    if (AbstractDungeon.player.hasRelic("Membership Card")) {
      currShop.applyDiscount(0.8F, true);
    }
    if (AbstractDungeon.player.hasRelic("Smiling Mask")) {
      ShopScreen.actualPurgeCost = 50;
    }

    AbstractDungeon.shopScreen.update();

    this.JustActivated = false;
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + this.counter + DESCRIPTIONS[1];
  }

  public AbstractRelic makeCopy() {
    return new Cape();
  }
}