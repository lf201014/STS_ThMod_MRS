package ThMod.cards.deprecated;

import ThMod.action.deprecated.StarBarrageDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

@Deprecated
public class StarBarrage
    extends CustomCard {

  public static final String ID = "StarBarrage_1";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";
  private static final int COST = 1;
  private static final int ATK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 3;

  public StarBarrage() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.ENEMY
    );
    this.baseDamage = ATK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new StarBarrageDamageAction(m, this)
    );
  }

  public AbstractCard makeCopy() {
    return new StarBarrage();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}