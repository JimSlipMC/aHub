package us.aquarin.hub.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import us.aquarin.hub.Hub;

import java.util.ArrayList;
import java.util.UUID;

import static us.aquarin.hub.Hub.plugin;

/**
 * Created by aidan on 5/14/2017.
 */
public class HidePlayers implements Listener {


    ArrayList<UUID> hiddenToggled = new ArrayList<>();

    private void message(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == null) { return; }
        if (p.getItemInHand().getType() == Material.WATCH) {
            if (!hiddenToggled.contains(p.getUniqueId())) {
                e.setCancelled(true);
                hiddenToggled.add(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Player Visibility.Hide"));
                for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                    p.hidePlayer(server);
                }
            } else {
                e.setCancelled(true);
                hiddenToggled.remove(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Player Visibility.Show"));
                for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                    p.showPlayer(server);
                }
            }
        }
    }
}
