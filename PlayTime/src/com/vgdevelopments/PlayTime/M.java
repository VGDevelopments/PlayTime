package com.vgdevelopments.PlayTime;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class M extends JavaPlugin{
	
	public FileConfiguration config; 
	
	public void onEnable() {
		saveDefaultConfig();
	}
	private final DecimalFormat df = new DecimalFormat("00");
	
	public String getTimePlayed(Player player) {
		long time = player.getStatistic(org.bukkit.Statistic.PLAY_ONE_TICK);
        long days = time / (24 * 60 * 60 * 20);
        time -= days * 24 * 60 * 60 * 20;
        long hours = time / (60 * 60 * 20);
        time -= hours * 60 * 60 * 20;
        time = time / (60 * 20);
        return days + ":" + df.format(hours) + ':' + df.format(time);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender; 
		
		if(cmd.getName().equalsIgnoreCase("playtime")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlaytimeMessage").replace(getTimePlayed(p), "%time%")));
		}
		return false;
	}

}
