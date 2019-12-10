package ThMod.cards.Marisa;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GravityBeat extends CustomCard {

  public static final String ID = "GravityBeat";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String[] EX_DESC = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/GravityBeat.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 2;
  private static final int DIVIDER = 12;
  private static final int DIV_UPG = 10;

  public GravityBeat() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.COMMON,
        CardTarget.ENEMY
    );

    //this.isMultiDamage = true;
    this.baseDamage = this.damage = ATTACK_DMG;
    this.baseBlock = this.block = DIVIDER;
    //this.magicNumber = this.baseMagicNumber = WK;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    if (AbstractDungeon.player != null) {
      this.block = this.baseBlock;
      this.isBlockModified = false;
      this.magicNumber = this.baseMagicNumber = AbstractDungeon.player.masterDeck.size() / this.block;
      this.rawDescription = DESCRIPTION + EX_DESC[0] + this.magicNumber + EX_DESC[1];
      initializeDescription();
    }
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

/*
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(
            p,
            this.multiDamage,
            this.damageTypeForTurn,
            AttackEffect.SLASH_DIAGONAL
        )
    );
    if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
      for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                mo,
                p,
                new WeakPower(mo, this.magicNumber, false),
                this.magicNumber,
                true,
                AttackEffect.NONE
            )
        );
      }
    }
    */

    for (int i = 0; i < this.magicNumber; i++) {
      if (!m.isDeadOrEscaped()) {
        addToBot(
            new DamageAction(
                m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AttackEffect.BLUNT_LIGHT
            )
        );
      }
      addToBot(
          new DrawCardAction(1)
      );
    }
  }

  public AbstractCard makeCopy() {
    return new GravityBeat();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(DIV_UPG);
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}