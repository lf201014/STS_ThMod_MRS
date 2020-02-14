package ThMod.cards.deprecated;

import ThMod.action.DamageUpAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

@Deprecated
public class ManaRampage_1 extends CustomCard {

  public static final String ID = "ManaRampage_1";
  public static final String IMG_PATH = "img/cards/ManaRampage.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = -1;
  private static final int DMG_UP = 2;
  private static final int DMG_UP_PLUS = 1;

  public ManaRampage_1() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = DMG_UP;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = EnergyPanel.totalCount;
    if (p.hasRelic("Chemical X")) {
      cnt += 2;
    }
    for (AbstractCard c : p.hand.group) {
      if ((!c.isEthereal) && (c != this) && (c.type == CardType.ATTACK)) {
        c.retain = true;
      }
    }
    if (cnt > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new DamageUpAction(this.magicNumber * cnt)
      );
    }
    if (!this.freeToPlayOnce) {
      p.energy.use(EnergyPanel.totalCount);
    }
  }

  public AbstractCard makeCopy() {
    return new ManaRampage_1();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(DMG_UP_PLUS);
      //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      //initializeDescription();
    }
  }
}