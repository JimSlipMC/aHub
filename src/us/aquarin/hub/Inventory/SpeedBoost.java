package us.aquarin.hub.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static us.aquarin.hub.Hub.plugin;


/**
 * Created by aidan on 5/14/2017.
 */
public class SpeedBoost implements Listener {


    private void message(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public List<UUID> speedBoost = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == null) { return; }
        if (p.getItemInHand().getType() == Material.SUGAR) {
            if (!speedBoost.contains(p.getUniqueId())) {
                speedBoost.add(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Speed Boost.Enable"));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 4));
            } else {
                speedBoost.remove(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Speed Boost.Disable"));
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
            }
        }
    }
}
