package us.aquarin.hub.Inventory;

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
public class SpeedBoost implements Listener {

    public ArrayList<UUID> speedBoost = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == Material.SUGAR) {
            if (!speedBoost.contains(p.getUniqueId())) {
                speedBoost.add(p.getUniqueId());
                p.sendMessage("§aSpeed Boost §7has been §2enabled§7.");
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 4));
            } else {
                speedBoost.remove(p.getUniqueId());
                p.sendMessage("§aSpeed Boost §7has been §cdisabled§7.");
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
            }
        }
    }
}
