package ThMod.cards.Marisa;

import ThMod.ThMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod.patches.AbstractCardEnum;

public class AlicesGift extends CustomCard {

  public static final String ID = "AlicesGift";
  public static final String IMG_PATH = "img/cards/GiftDoll_v2.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 0;
  private static final int ATK = 5;
  private static final int ATK_UPG = 2;
  private static final int AMP = 2;

  public AlicesGift() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY
    );
    this.damage = this.baseDamage = ATK;
    this.exhaust = true;
  }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(ATK_UPG);
    }
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    this.retain = true;
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    if (ThMod.Amplified(this, AMP)) {
      this.damage *= 3;
    }
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AttackEffect.FIRE
        )
    );
  }
}
