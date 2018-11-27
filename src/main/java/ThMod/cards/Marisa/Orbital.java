package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.action.OrbitalAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class Orbital extends CustomCard {

  public static final String ID = "Orbital";
  public static final String IMG_PATH = "img/cards/temp/Orbital.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = -2;
  private static final int UPG_DRAW = 1;
  private static final int DRAW = 1;

  public Orbital() {
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
    this.magicNumber = this.baseMagicNumber = DRAW;
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_DRAW);
    }
  }

  public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    return false;
  }

  public void triggerOnExhaust() {
    AbstractDungeon.actionManager.addToBottom(
        new OrbitalAction()
    );
    if (this.upgraded){
      AbstractDungeon.actionManager.addToBottom(
          new OrbitalAction()
      );
    }
  }

  public void triggerWhenDrawn() {
    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(AbstractDungeon.player, this.magicNumber)
    );
  }

  @Override
  public void use(AbstractPlayer arg0, AbstractMonster arg1) {
  }
}
