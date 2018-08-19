package ThMod_FnH.patches;
/*
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import ThMod_FnH.ThMod;

public class SuperNovaDiscardPatch {
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Decay", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableDecay {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Decay detected.");
				return;
			} else {
				AbstractDungeon.actionManager.addToTop(
						new DamageAction(
								AbstractDungeon.player,
								new DamageInfo(
										AbstractDungeon.player,
										2,
										DamageInfo.DamageType.THORNS
										),
								AttackEffect.FIRE
								)
						);
			}
		}
	}
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Shame", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableShame {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Shame detected.");
				return;
			} else {
				AbstractDungeon.actionManager.addToTop(
						new ApplyPowerAction(
								AbstractDungeon.player,
								AbstractDungeon.player, 
								new FrailPower(
										AbstractDungeon.player,
										1,
										true
										),
								1
								)
						);
			}
		}
	}
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Regret", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableRegret {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Regret detected.");
				return;
			} else {
				AbstractDungeon.actionManager.addToTop(
						new LoseHPAction(
								AbstractDungeon.player,
								AbstractDungeon.player,
								1 * AbstractDungeon.player.hand.size(), 
								AttackEffect.FIRE
								)
						);
			}
		}
	}
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Doubt", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableDoubt {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Doubt detected.");
				return;
			} else {
				AbstractDungeon.actionManager.addToTop(
						new ApplyPowerAction(
								AbstractDungeon.player, 
								AbstractDungeon.player,
								new WeakPower(
										AbstractDungeon.player,
										1,
										true
										),
								1
								)
						);
			}
		}
	}
	
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.status.Burn", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableBurn {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Burn detected.");
				return;
			} else {
				AbstractDungeon.actionManager.addToBottom(
						new DamageAction(
								AbstractDungeon.player, 
								new DamageInfo(
										AbstractDungeon.player,
										2, 
										DamageInfo.DamageType.THORNS
										),
								AttackEffect.FIRE
								)
						);
			}
		}
	}
}
*/