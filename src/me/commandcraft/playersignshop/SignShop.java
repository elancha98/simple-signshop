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
	
	int x, y, z; //coordinates of the sign
	double price;
	int amount;
	Material material;
	
	public SignShop(Player player, double price) {
		this(player, player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getAmount(), price);
	}

	public SignShop(Player player, Material material, int amount, double price) {
		if (player.getInventory().contains(material, amount)) {
			player.getInventory().removeItem(new ItemStack(material, amount));
			this.price = price;
			this.amount = amount;
			
			//TODO create sign
			
		} else {
			player.sendMessage(ChatColor.RED + "error: you dont have that amount of " + material.name());
		}
	}
	
	public static Block getSignBlock(Player player) {
		//TODO get block the sign must be placed on
		return null;
	}

	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		//TODO handle click and check if clicked block matches coordinates
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
