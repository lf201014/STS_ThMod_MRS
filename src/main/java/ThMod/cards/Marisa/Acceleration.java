package ThMod.cards.Marisa;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Acceleration extends CustomCard {
  public static final String ID = "Acceleration";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/GuidingStar.png";
  private static final int COST = 1;
  private static final int DRAW = 2;
  private static final int DRAW_UPG = 1;

  public Acceleration(){
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.COMMON,
        AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = DRAW;
    this.magicNumber = this.baseMagicNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m){
    AbstractDungeon.actionManager.addToTop(
        new DrawCardAction(p,this.magicNumber)
    );
    AbstractDungeon.actionManager.addToTop(
        new MakeTempCardInHandAction(new Burn())
    );
  }

  public AbstractCard makeCopy(){
    return new Acceleration();
  }

  public void upgrade(){
    if (!this.upgraded){
      upgradeName();
      upgradeMagicNumber(DRAW_UPG);
    }
  }

}
