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
public class JumpBoost implements Listener {

    public ArrayList<UUID> jumpBoost = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getItemInHand().getType() == Material.FEATHER) {
            if (!jumpBoost.contains(p.getUniqueId())) {
                jumpBoost.add(p.getUniqueId());
                p.sendMessage("§aJump Boost §7has been §2enabled§7.");
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 4));
            } else {
                jumpBoost.remove(p.getUniqueId());
                p.sendMessage("§aJump Boost §7has been §cdisabled§7.");
                if (p.hasPotionEffect(PotionEffectType.JUMP)) {
                    p.removePotionEffect(PotionEffectType.JUMP);
                }
            }
        }
    }
}
