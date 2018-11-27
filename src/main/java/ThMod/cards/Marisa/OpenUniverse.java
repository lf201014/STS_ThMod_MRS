package ThMod.cards.Marisa;

import ThMod.action.OpenUniverseAction;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class OpenUniverse extends CustomCard {

  public static final String ID = "OpenUniverse";
  public static final String IMG_PATH = "img/cards/temp/OpenUni.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int DRAW = 2;
  private static final int UPG_DRAW = 1;
  private static final int CHANCE = 20;
  private static final int UPG_CHANCE = 10;

  public OpenUniverse() {
    super(
        ID, NAME, IMG_PATH,
        COST, DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = DRAW;
    this.damage = this.baseDamage = CHANCE;
  }

  @Override
  public void applyPowers(){
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new OpenUniverseAction(this.magicNumber,this.upgraded)
    );

  }

  public AbstractCard makeCopy() {
    return new OpenUniverse();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_DRAW);
      upgradeDamage(UPG_CHANCE);
    }
  }

}
