package ThMod.cards.deprecated;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Marisa.ManaRampagePower;
import basemod.abstracts.CustomCard;

@Deprecated
public class ManaRampage extends CustomCard {

  public static final String ID = "ManaRampage_1";
  public static final String IMG_PATH = "img/cards/ManaRampage.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = -1;

  public ManaRampage() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    int cnt = EnergyPanel.totalCount;
    if (this.upgraded) {
      cnt++;
    }
    if (cnt > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(p, p, new ManaRampagePower(p, cnt), cnt));
    }

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
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      ;
      initializeDescription();
    }
  }
}