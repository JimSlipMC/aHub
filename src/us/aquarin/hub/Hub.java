package us.aquarin.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffectType;
import us.aquarin.hub.Inventory.*;

/**
 * Created by aidan on 5/14/2017.
 */
public class Hub extends JavaPlugin implements PluginMessageListener, Listener {

    public static Hub plugin;

    @Override
    public void onEnable() {
        plugin = this;

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ServerSelector(), this);
        getServer().getPluginManager().registerEvents(new SpeedBoost(), this);
        getServer().getPluginManager().registerEvents(new JumpBoost(), this);
        getServer().getPluginManager().registerEvents(new HidePlayers(), this);
        getServer().getPluginManager().registerEvents(new Cosmetics(), this);

    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
    }

    SpeedBoost SB = new SpeedBoost();
    JumpBoost JB = new JumpBoost();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();

        p.getInventory().clear();

        if (p.hasPotionEffect(PotionEffectType.SPEED)) {
            p.removePotionEffect(PotionEffectType.SPEED);
        }

        if (p.hasPotionEffect(PotionEffectType.JUMP)) {
            p.removePotionEffect(PotionEffectType.JUMP);
        }


        ItemStack navigation = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta nm = navigation.getItemMeta();
        nm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Navigation"));
        navigation.setItemMeta(nm);

        ItemStack visiblePlayers = new ItemStack(Material.WATCH, 1);
        ItemMeta vP = visiblePlayers.getItemMeta();
        vP.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cPlayer Visibility"));
        visiblePlayers.setItemMeta(vP);

        ItemStack cosmetics = new ItemStack(Material.HOPPER, 1);
        ItemMeta cm = cosmetics.getItemMeta();
        cm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bCosmetics &7"));
        cosmetics.setItemMeta(cm);

        ItemStack speed = new ItemStack(Material.SUGAR, 1);
        ItemMeta sm = speed.getItemMeta();
        sm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSpeed Boost"));
        speed.setItemMeta(sm);

        ItemStack jump = new ItemStack(Material.FEATHER, 1);
        ItemMeta jm = jump.getItemMeta();
        jm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aJump Boost"));
        jump.setItemMeta(jm);

        Inventory inv = p.getInventory();

        inv.setItem(0, speed);
        inv.setItem(2, visiblePlayers);
        inv.setItem(4, navigation);
        inv.setItem(6, cosmetics);
        inv.setItem(8, jump);

        Bukkit.getServer().dispatchCommand(getServer().getConsoleSender(), "spawn " + p.getName());

        if (SB.speedBoost.contains(p.getUniqueId())) {
            SB.speedBoost.remove(p.getUniqueId());
        }

        if (JB.jumpBoost.contains(p.getUniqueId())) {
            JB.jumpBoost.remove(p.getUniqueId());
        }

        for (int i = 0; i < 100; i++) {
            p.sendMessage(" ");
        }
    }

    @EventHandler
    public void creative(InventoryCreativeEvent e) { return; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e) {
        e.setCancelled(true);
    }
}
