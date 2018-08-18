package ThMod_FnH.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class SuperNovaDiscardPatch {
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.curses.Decay", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableDecay {
		public static void Replace(boolean dontTriggerOnUseCard) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Decay detected.");
				return;
			} else {
			    dontTriggerOnUseCard = true;
			    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(new Decay(), true));
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
			    dontTriggerOnUseCard = true;
			    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(new Shame(), true));
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
			    dontTriggerOnUseCard = true;
			    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(new Regret(), true));
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
			    dontTriggerOnUseCard = true;
			    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(new Doubt(), true));
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
			    dontTriggerOnUseCard = true;
			    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(new Burn(), true));
			}
		}
	}
}
