package cn.jiongjionger.neverlag.tweaker;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import cn.jiongjionger.neverlag.config.ConfigManager;
import cn.jiongjionger.neverlag.utils.EntityUtils;

public class RemoveEntityWhenChunkUnload implements Listener {

	private final ConfigManager cm = ConfigManager.getInstance();

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onChunkUnload(ChunkUnloadEvent e) {
		if (!cm.removeEntityWhenChunkUnload) {
			return;
		}
		for (Entity entity : e.getChunk().getEntities()) {
			if (EntityUtils.checkCustomNpc(entity)) {
				continue;
			}
			if (entity instanceof Monster && cm.removeMonsterWhenChunkUnload) {
				entity.remove();
			} else if (entity instanceof Animals && cm.removeAnimalsWhenChunkUnload) {
				entity.remove();
			} else if (entity instanceof Item && cm.removeDropItemWhenChunkUnload) {
				entity.remove();
			} else if (entity instanceof Arrow && cm.removeArrowWhenChunkUnload) {
				entity.remove();
			} else if (entity instanceof Squid && cm.removeSquidWhenChunkUnload) {
				entity.remove();
			}
		}
	}
}
