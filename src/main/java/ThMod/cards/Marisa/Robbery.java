package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.action.RobberyDamageAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class Robbery
    extends CustomCard {

  public static final String ID = "Robbery";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/rob.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 7;
  private static final int UPGRADE_PLUS_DMG = 3;
  private static final int AMP = 1;

  public Robbery() {
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

    this.baseDamage = ATTACK_DMG;
    this.exhaust = true;
    this.tags.add(AbstractCard.CardTags.HEALING);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new RobberyDamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            ThMod.Amplified(this, AMP)
        )
    );
  }

  public AbstractCard makeCopy() {
    return new Robbery();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}