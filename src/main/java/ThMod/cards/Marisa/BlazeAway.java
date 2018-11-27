package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;

public class BlazeAway extends CustomCard {

  public static final String ID = "BlazeAway";
  public static final String IMG_PATH = "img/cards/temp/BlazeAway.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  private static final int COST = 1;
  private static final int NUM = 1;
  private static final int UPG_NUM = 1;
  //private static final int AMP = 1;
  public static AbstractCard lastAttack = null;

  public BlazeAway() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.SELF
    );

    this.baseMagicNumber = this.magicNumber = NUM;
  }

  @Override
  public void applyPowers() {
    lastAttack = null;
    for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
      if (c.type == CardType.ATTACK) {
        lastAttack = c;
      }
    }
    if (lastAttack != null) {
      this.rawDescription =
          DESCRIPTION + EXTENDED_DESCRIPTION[0] + lastAttack.name + EXTENDED_DESCRIPTION[1];
      initializeDescription();
    } else {
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[2];
      initializeDescription();
    }
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (lastAttack != null) {
      ThMod.logger.info("BlazeAway : last attack :" + lastAttack.cardID);
      AbstractCard card = lastAttack.makeStatEquivalentCopy();
      /*
      if (ThMod.Amplified(this, AMP)) {
        card.costForTurn = 0;
      } else {
        card.costForTurn = card.cost;
      }
      */
      if (card.costForTurn>=0){
        card.setCostForTurn(0);
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
          new MakeTempCardInHandAction(card, this.magicNumber)
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
      upgradeMagicNumber(UPG_NUM);
      upgradeName();
      //upgradeBaseCost(0);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
      //this.exhaust = false;
    }
  }
}