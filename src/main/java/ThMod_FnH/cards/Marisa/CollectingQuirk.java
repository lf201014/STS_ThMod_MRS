package ThMod_FnH.cards.Marisa;

import ThMod_FnH.ThMod;
import ThMod_FnH.action.UnstableBombAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class CollectingQuirk
    extends CustomCard {

  public static final String ID = "CollectingQuirk";
  private static final CardStrings cardStrings =
      CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/collec.png";
  private static final int COST = 2;
  private static final int DIVIDER = 4;
  private static final int UPG_DIVIDER = -1;
  private static final int ATK_DMG = 7;
  private int cnt;

  public CollectingQuirk() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.RARE,
        AbstractCard.CardTarget.ALL_ENEMY
    );
    this.baseDamage = ATK_DMG;
    this.magicNumber = this.baseMagicNumber = DIVIDER;
    this.cnt = 0;
    this.block = this.baseBlock = 0;
  }

  @Override
  public void applyPowers() {
    super.applyPowers();
    getCounter();
    this.block = this.baseBlock = this.cnt;
    this.isBlockModified = (this.block == 0);
    ThMod.logger.info(
        "CollectingQuirk : applyPowers : block :" + this.block
            + " ; baseBlock : " + this.baseBlock
            + " ; counter : " + this.cnt
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    getCounter();
    /*
    if (cnt > 0) {
      for (int i = 0; i < cnt; i++) {
        AbstractDungeon.actionManager.addToBottom(
            new DamageRandomEnemyAction(
                new DamageInfo(
                    p,
                    this.damage,
                    this.damageTypeForTurn
                ),
                AttackEffect.FIRE
            )
        );
      }
    }
    */
    if (cnt > 0) {
      AbstractDungeon.actionManager.addToBottom(
          new UnstableBombAction(
              AbstractDungeon.getMonsters().getRandomMonster(true),
              this.damage,
              this.damage,
              this.cnt
          )
      );
    }
  }

  public AbstractCard makeCopy() {
    return new CollectingQuirk();
  }

  private void getCounter() {
    AbstractPlayer p = AbstractDungeon.player;
    cnt = p.relics.size() / this.magicNumber;
    if (p.hasRelic("Circlet")) {
      cnt += p.getRelic("Circlet").counter - 1;
    }
    if (p.hasRelic("Red Circlet")) {
      cnt += p.getRelic("Red Circlet").counter - 1;
    }
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      //upgradeDamage(UPG_DMG);
      upgradeMagicNumber(UPG_DIVIDER);
    }
  }
}