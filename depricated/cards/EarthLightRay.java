package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;

@Deprecated
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
  private int AMP = 2;
  public static final boolean isAmp = true;


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
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    if (ThMod.Amplified(this, AMP)) {
      if (!p.discardPile.isEmpty()) {
        AbstractDungeon.actionManager.addToBottom(new DiscardPileToHandAction(1));
      }
      AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    }
  }

  public AbstractCard makeCopy() {
    return new EarthLightRay();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_HEAL);
      AMP--;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}