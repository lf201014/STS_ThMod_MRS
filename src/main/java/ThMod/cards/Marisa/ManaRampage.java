package ThMod.cards.Marisa;

import ThMod.action.ManaRampageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class ManaRampage extends CustomCard {

  public static final String ID = "ManaRampage";
  public static final String IMG_PATH = "img/cards/ManaRampage.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = -1;
  private static final int DMG_UP = 2;
  private static final int DMG_UP_PLUS = 1;

  public ManaRampage() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.RARE,
        CardTarget.ALL_ENEMY
    );
    this.magicNumber = this.baseMagicNumber = DMG_UP;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = EnergyPanel.totalCount;
    if (p.hasRelic("Chemical X")) {
      cnt += 2;
    }

    AbstractDungeon.actionManager.addToBottom(
        new ManaRampageAction(cnt,this.upgraded)
    );

    if (!this.freeToPlayOnce) {
      p.energy.use(EnergyPanel.totalCount);
    }
  }

  public AbstractCard makeCopy() {
    return new ManaRampage();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(DMG_UP_PLUS);
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}