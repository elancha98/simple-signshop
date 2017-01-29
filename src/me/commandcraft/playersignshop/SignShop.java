package me.commandcraft.playersignshop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignShop implements Listener {
	
	int x, y, z;
	double price;
	int amount;
	Material material;
	
	public SignShop(Player player, double price) {
		ItemStack item = player.getInventory().getItemInMainHand();
		new SignShop(player, item.getType(), item.getAmount(), price);
	}

	public SignShop(Player player, Material material, int amount, double price) {
		if (player.getInventory().contains(material, amount)) {
			player.getInventory().removeItem(new ItemStack(material, amount));
			this.price = price;
			this.amount = amount;
			
			
		} else {
			player.sendMessage(ChatColor.RED + "error: you dont have that amount of " + material.name());
		}
	}
	
	public static Block getSignBlock(Player player) {
		
		return null;
	}

	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (x == block.getX() && y == block.getY() && z == block.getZ()) {
			PlayerInteractEvent.getHandlerList().unregister(this);
			BlockBreakEvent.getHandlerList().unregister(this);
		}
	}
}
