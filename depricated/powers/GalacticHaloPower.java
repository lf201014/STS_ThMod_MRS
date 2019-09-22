package ThMod.powers.deprecated;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;

@Deprecated
public class GalacticHaloPower
    extends AbstractPower {

  public static final String POWER_ID = "GalacticHaloPower";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public int cnt;

  public GalacticHaloPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = AbstractPower.PowerType.BUFF;
    updateDescription();
    this.img = new Texture("img/powers/darkness.png");
    this.cnt = 0;
  }

  public void onPlayCard(AbstractCard card, AbstractMonster m) {
    if (card.type == CardType.ATTACK) {
      this.cnt++;
      ThMod.logger.info(
          "GalacticHaloPower : Adding counter for playing :" + card.cardID + " ; counter :"
              + this.cnt);
    }
  }

  public void atEndOfTurn(boolean isPlayer) {
    AbstractPlayer p = AbstractDungeon.player;
    ThMod.logger.info(
        "GalacticHaloPower : end of turn : counter : " + this.cnt + " ; Player turn :" + isPlayer);
    if ((isPlayer) && (this.cnt > 0)) {
      ThMod.logger.info(
          "GalacticHaloPower : Granting block ; counter : " + cnt + " ; amount :" + this.amount);
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(p, p, this.amount * this.cnt));
      this.cnt = 0;
    }
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

}