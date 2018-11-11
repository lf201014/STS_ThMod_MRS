package ThMod.cards.deprecated;


import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class OpticalCamouflage extends CustomCard {

  public static final String ID = "OpticalCamouflage";
  public static final String IMG_PATH = "img/cards/Defend_MRS.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 8;
  private static final int UPGRADE_PLUS_BLOCK = 3;
  private static final int AMP = 1;

  public OpticalCamouflage() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.MARISA_DERIVATIONS,
        AbstractCard.CardRarity.BASIC,
        AbstractCard.CardTarget.SELF
    );
    this.baseBlock = BLOCK_AMT;
    setBannerTexture(
        "images/cardui/512/banner_uncommon.png",
        "images/cardui/1024/banner_uncommon.png"
    );
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, this.AMP)) {
      this.block *= 2;
    }
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
  }

  public AbstractCard makeCopy() {
    return new OpticalCamouflage();
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}