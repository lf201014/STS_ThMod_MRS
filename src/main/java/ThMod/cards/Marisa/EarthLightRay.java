package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.ThMod;
import ThMod.action.DiscToHandRandAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class EarthLightRay extends CustomCard {

  public static final String ID = "EarthLightRay";
  public static final String IMG_PATH = "img/cards/EarthLightRay.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 0;
  private static final int HEAL_AMT = 4;
  private static final int UPG_HEAL = 2;
  private int AMP = 1;

  public EarthLightRay() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF
    );

    this.baseMagicNumber = this.magicNumber = HEAL_AMT;
    this.exhaust = true;
    this.tags.add(AbstractCard.CardTags.HEALING);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (!p.discardPile.isEmpty()) {
      if (ThMod.Amplified(this, AMP)) {
        if (this.upgraded) {
          AbstractDungeon.actionManager.addToBottom(
              new DiscardPileToHandAction(1)
          );
        } else {
          AbstractDungeon.actionManager.addToBottom(
              new DiscToHandRandAction()
          );
        }
      }
    }
    AbstractDungeon.actionManager.addToBottom(
        new HealAction(p, p, this.magicNumber)
    );

  }

  public AbstractCard makeCopy() {
    return new EarthLightRay();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_HEAL);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}