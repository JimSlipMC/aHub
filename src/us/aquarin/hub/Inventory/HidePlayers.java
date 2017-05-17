package us.aquarin.hub.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by aidan on 5/14/2017.
 */
public class HidePlayers implements Listener {

    ArrayList<UUID> hiddenToggled = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == Material.WATCH) {
            if (!hiddenToggled.contains(p.getUniqueId())) {
                e.setCancelled(true);
                hiddenToggled.add(p.getUniqueId());
                p.sendMessage("§aPlayers §7have been hidden.");
                for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                    p.hidePlayer(server);
                }
            } else {
                e.setCancelled(true);
                hiddenToggled.remove(p.getUniqueId());
                p.sendMessage("§aPlayers §7have been shown.");
                for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                    p.showPlayer(server);
                }
            }
        }
    }
}
