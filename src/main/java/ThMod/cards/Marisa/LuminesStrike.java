package ThMod.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;

public class LuminesStrike
    extends AmplifiedAttack {

  public static final String ID = "LuminesStrike";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/LumiStrike.png";

  private static final int COST = 0;
  private static final int D0 = 2;
  private static final int D1 = 3;
  private static final int A0 = 4;
  private static final int A1 = 5;
  private static final int AMP = 1;

  public LuminesStrike() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        AbstractCard.CardTarget.ENEMY
    );
    this.baseMagicNumber = D0;
    this.baseBlock = A0;
    this.baseDamage = 0;
    this.isException = true;
  }

  @Override
  public void applyPowers() {
    AbstractPlayer player = AbstractDungeon.player;
    this.damage = player.hand.size() * this.baseMagicNumber + this.baseDamage;
    this.block = EnergyPanel.totalCount * this.baseBlock + this.baseDamage;
    super.applyPowers();
  }

  @Override
  public void calculateDamageDisplay(AbstractMonster mo) {
    calculateCardDamage(mo);
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    AbstractPlayer player = AbstractDungeon.player;
    ThMod.logger
        .info("LuminesStrike : calculateCardDamage : player hand size :" + player.hand.size());
    this.damage = player.hand.size() * this.baseMagicNumber + this.baseDamage;
    this.block = EnergyPanel.totalCount * this.baseBlock + this.baseDamage;
    super.calculateCardDamage(mo);
  }


  public void use(AbstractPlayer p, AbstractMonster m) {

    if (ThMod.Amplified(this, AMP)) {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.block, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    }

  }

  public AbstractCard makeCopy() {
    return new LuminesStrike();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.baseBlock = A1;
      this.baseMagicNumber = D1;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}