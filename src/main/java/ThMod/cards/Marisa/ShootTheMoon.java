package ThMod.cards.Marisa;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ThMod.ThMod;
import ThMod.abstracts.AmplifiedAttack;
import ThMod.patches.AbstractCardEnum;

public class ShootTheMoon
    extends AmplifiedAttack {

  public static final String ID = "ShootTheMoon";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/ShootTheMoon_v1.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 8;
  private static final int UPG_DMG = 3;
  private static final int AMP_DMG = 5;
  private static final int UPG_AMP = 2;
  private static final int AMP = 1;

  public ShootTheMoon() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION, AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.ENEMY
    );

    this.baseDamage = ATK_DMG;
    this.ampNumber = AMP_DMG;
    this.baseBlock = this.baseDamage + this.ampNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractPower po;

    boolean fightingBoss = (m.type == AbstractMonster.EnemyType.BOSS);

    if (ThMod.Amplified(this, AMP)) {

      if (!fightingBoss) {
        for (AbstractPower pow : m.powers) {
          if (pow.type == AbstractPower.PowerType.BUFF) {
            AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(m, p, pow)
            );
          }
        }
      }

      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.block, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    } else {
      if ((!m.powers.isEmpty()) && (!fightingBoss)) {

        ArrayList<AbstractPower> pows = new ArrayList<>();
        for (AbstractPower pow : m.powers) {
          if (pow.type == AbstractPower.PowerType.BUFF) {
            pows.add(pow);
          }
        }
        if (!pows.isEmpty()) {
          po = pows.get((int) (Math.random() * pows.size()));
          AbstractDungeon.actionManager.addToBottom(
              new RemoveSpecificPowerAction(m, p, po)
          );
        }

      }

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
    return new ShootTheMoon();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.ampNumber += UPG_AMP;
      this.block = this.baseBlock = this.baseDamage + this.ampNumber;
      this.isBlockModified = true;
    }
  }
}