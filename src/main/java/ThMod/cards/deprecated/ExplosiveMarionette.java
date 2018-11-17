package ThMod.cards.deprecated;

import ThMod.ThMod;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class ExplosiveMarionette extends CustomCard {

  public static final String ID = "ExplosiveMarionette";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 9;
  private static final int UPG_DMG = 3;
  private static final int AMP = 1;

  public ExplosiveMarionette() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_DERIVATIONS,
        CardRarity.SPECIAL,
        AbstractCard.CardTarget.ENEMY
    );
    setBannerTexture(
        "images/cardui/512/banner_uncommon.png",
        "images/cardui/1024/banner_uncommon.png"
    );
    this.exhaust = true;
    this.damage = this.baseDamage = ATK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      this.damage *= 2;
    }
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.FIRE
        )
    );
  }

  public AbstractCard makeCopy() {
    return new ExplosiveMarionette();
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
    }
  }
}
