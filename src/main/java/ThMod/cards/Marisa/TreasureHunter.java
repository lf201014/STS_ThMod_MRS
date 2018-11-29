package ThMod.cards.Marisa;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action.TreasureHunterDamageAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class TreasureHunter
    extends CustomCard {

  public static final String ID = "TreasureHunter";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/TreasureHunter.png";
  private static final int COST = 2;
  private static final int ATTACK_DMG = 12;
  private static final int UPGRADE_PLUS_DMG = 5;

  public TreasureHunter() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = ATTACK_DMG;
    this.exhaust = true;
    this.tags.add(CardTags.HEALING);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new TreasureHunterDamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn)
        )
    );
  }

  public AbstractCard makeCopy() {
    return new TreasureHunter();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}