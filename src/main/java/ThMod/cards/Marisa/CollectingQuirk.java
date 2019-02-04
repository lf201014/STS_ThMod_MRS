package ThMod.cards.Marisa;

import ThMod.ThMod;
import ThMod.action.UnstableBombAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class CollectingQuirk
    extends CustomCard {

  public static final String ID = "CollectingQuirk";
  private static final CardStrings cardStrings =
      CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/collec.png";
  private static final int COST = 2;
  private static final int DIVIDER = 4;
  private static final int UPG_DIVIDER = 3;
  private static final int ATK_DMG = 9;
  private int counter;

  public CollectingQuirk() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.ALL_ENEMY
    );
    this.baseDamage = ATK_DMG;
    this.magicNumber = this.baseMagicNumber = DIVIDER;
    this.block = this.baseBlock = 0;
    this.counter = 0;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    getCounter();
    modifyBlock();
    this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
    initializeDescription();
    ThMod.logger.info(
        "CollectingQuirk : applyPowers : damage :"
            + this.damage
            + " ; counter : " + this.counter
            + " ; block :" + this.block
            + " ; magic number :" + this.magicNumber
    );
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    //super.calculateCardDamage(mo);
    getCounter();
    modifyBlock();
    this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
    initializeDescription();
    ThMod.logger.info(
        "CollectingQuirk : applyPowers : damage :"
            + this.damage
            + " ; counter : " + this.counter
            + " ; block :" + this.block
            + " ; magic number :" + this.magicNumber
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    getCounter();
    if (counter > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new UnstableBombAction(
              AbstractDungeon.getMonsters().getRandomMonster(true),
              this.damage,
              this.damage,
              this.counter
          )
      );
    }
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  public AbstractCard makeCopy() {
    return new CollectingQuirk();
  }

  private void getCounter() {
    AbstractPlayer p = AbstractDungeon.player;
    int divider = DIVIDER;
    if (this.upgraded) {
      divider = UPG_DIVIDER;
    }
    counter = p.relics.size();
    if (p.hasRelic("Circlet")) {
      counter += p.getRelic("Circlet").counter - 1;
    }
    if (p.hasRelic("Red Circlet")) {
      counter += p.getRelic("Red Circlet").counter - 1;
    }
    counter /= divider;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.magicNumber = this.baseMagicNumber = UPG_DIVIDER;
      this.upgradedMagicNumber = true;
    }
  }

  private void modifyBlock() {
    if (this.counter > 0) {
      this.isBlockModified = true;
      this.block = this.baseBlock = this.counter;
    } else {
      this.isBlockModified = false;
      this.block = this.baseBlock = 0;
    }
  }
}