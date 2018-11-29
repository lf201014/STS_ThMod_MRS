package ThMod.cards.Marisa;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.SuperNovaPower;
import basemod.abstracts.CustomCard;

public class SuperNova extends CustomCard {

  public static final String ID = "SuperNova";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/temp/SuperNova.png";
  private static final int COST = 3;

  public SuperNova() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.POWER,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.SELF
    );
    this.tags.add(BaseModCardTags.FORM);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if ((this.upgraded) && (p.hasPower("SuperNovaPower"))) {
      SuperNovaPower po = (SuperNovaPower) p.getPower("SuperNovaPower");
      po.upgraded = true;
    }
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new SuperNovaPower(p, 1, this.upgraded),
            1
        )
    );
  }

  public AbstractCard makeCopy() {
    return new SuperNova();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}