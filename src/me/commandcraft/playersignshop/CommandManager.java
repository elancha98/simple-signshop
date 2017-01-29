package me.commandcraft.playersignshop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {

	public static void process(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "error: you are not a player");
			return;
		}
		process((Player) sender, args);
	}

	public static void process(Player player, String[] args) {
		if (!player.hasPermission("psign.create")) {
			player.sendMessage(ChatColor.RED + "You don't have permission to use /PSign");
			return;
		}
		if (args.length > 1) {
			if (args[0].equals("hand")) {
				double d;
				try {
					d = Double.parseDouble(args[1]);
					new SignShop(player, d);
				} catch (Exception e) {
					player.sendMessage(ChatColor.RED + "error: you must enter a valid price");
				}
			} else {
				if (args.length > 2) {
					Material material = Material.getMaterial(args[0]);
					if (material == null) {
						player.sendMessage(ChatColor.RED + "error: that's not an item");
						return;
					}
					int amount;
					try {
						amount = Integer.parseInt(args[1]);
					} catch (Exception e) {
						player.sendMessage(ChatColor.RED + "error: you must enter a valid amount");
						return;
					}
					double price;
					try {
						price = Double.parseDouble(args[2]);
					} catch (Exception e) {
						player.sendMessage(ChatColor.RED + "error: you must enter a valid price");
						return;
					}
					new SignShop(player, material, amount, price);
				} else usage(player);
			}
		} else {
			usage(player);
		}
	}

	public static void usage(Player sender) {
		sender.sendMessage(ChatColor.RED + "usage:");
		sender.sendMessage(ChatColor.GREEN + " - /PSign hand <price> " + ChatColor.YELLOW
				+ "to create a shop of your hand item");
		sender.sendMessage(ChatColor.GREEN + " - /PSign <item> <amount> <price> " + ChatColor.YELLOW
				+ "to create a shop of the specified item");

	}

}
