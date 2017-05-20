package us.aquarin.hub.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.aquarin.hub.Hub;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static us.aquarin.hub.Hub.plugin;

/**
 * Created by aidan on 5/14/2017.
 */
public class JumpBoost implements Listener {


    private void message(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public List<UUID> jumpBoost = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == null) { return; }
        if (p.getItemInHand().getType() == Material.FEATHER) {
            if (!jumpBoost.contains(p.getUniqueId())) {
                jumpBoost.add(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Jump Boost.Enable"));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 4));
            } else {
                jumpBoost.remove(p.getUniqueId());
                message(p, plugin.getConfig().getString("Messages.Jump Boost.Disable"));
                if (p.hasPotionEffect(PotionEffectType.JUMP)) {
                    p.removePotionEffect(PotionEffectType.JUMP);
                }
            }
        }
    }
}
