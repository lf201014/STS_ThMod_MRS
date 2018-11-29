package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.abstracts.AmplifiedAttack;
import ThMod.action.UnstableBombAction;
import ThMod.patches.AbstractCardEnum;

public class UnstableBomb
    extends AmplifiedAttack {

  public static final String ID = "UnstableBomb";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/UnstableBomb.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 1;
  private static final int UPG_DMG = 1;
  private static final int AMP_DMG = 3;
  private static final int UPG_AMP = 0;


  public UnstableBomb() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ALL_ENEMY
    );

    this.baseDamage = ATK_DMG;
    this.ampNumber = AMP_DMG;
    this.baseBlock = this.baseDamage + this.ampNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new UnstableBombAction(
            AbstractDungeon.getMonsters().getRandomMonster(true),
            this.damage,
            this.block,
            4
        )
    );
  }

  public AbstractCard makeCopy() {
    return new UnstableBomb();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.ampNumber += UPG_AMP;
      this.block = this.baseBlock = this.baseDamage + this.ampNumber;
      this.isBlockModified = true;
    }
  }
}