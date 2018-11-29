package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action._6AAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class _6A
    extends CustomCard {

  public static final String ID = "6A";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/temp/Butt.png";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 3;
  private static final int UPGRADE_PLUS_DMG = 1;

  public _6A() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY);

    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new _6AAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn)
        )
    );
  }

  public AbstractCard makeCopy() {
    return new _6A();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}