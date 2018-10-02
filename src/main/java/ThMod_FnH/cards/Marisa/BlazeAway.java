package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class BlazeAway extends CustomCard {

  public static final String ID = "BlazeAway";
  public static final String IMG_PATH = "img/cards/Defend.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int STC = 1;

  private static final int AMP = 1;
  //private static final int AMP_STC = 1;

  public BlazeAway() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF);

    this.baseMagicNumber = this.magicNumber = STC;
    //this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.lastAttack != null) {
      ThMod.logger.info("BlazeAway : last attack :" + ThMod.lastAttack.cardID);
      AbstractCard card = ThMod.lastAttack.makeStatEquivalentCopy();
      if (ThMod.Amplified(this, AMP)) {
        card.costForTurn = 0;
      } else {
        card.costForTurn = card.cost;
      }
      ThMod.logger.info(
          "BlazeAway : card :" + card.cardID
              + " ; baseD :" + card.baseDamage
              + " ; D : " + card.damage
              + " ; baseB :" + card.baseBlock
              + " ; B : " + card.block
              + " ; baseM :" + card.baseMagicNumber
              + " ; M : " + card.magicNumber
              + " ; C : " + card.cost
              + " ; CFT : " + card.costForTurn
      );
      AbstractDungeon.actionManager.addToBottom(
          new MakeTempCardInHandAction(card, 1)
      );

    } else {
      ThMod.logger.info("BlazeAway : error : last attack is null ");
    }

  }

  public AbstractCard makeCopy() {
    return new BlazeAway();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(0);
      //this.rawDescription = DESCRIPTION_UPG;
      //initializeDescription();
      //this.exhaust = false;
    }
  }
}