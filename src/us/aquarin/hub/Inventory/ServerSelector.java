package us.aquarin.hub.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.aquarin.hub.Hub;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

/**
 * Created by aidan on 5/14/2017.
 */
public class ServerSelector implements Listener {

    private void transfer(Player p, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try
        {
            p.sendMessage("§aSending you to " + server + "...");
            out.writeUTF("Connect");
            out.writeUTF(server);
        }
        catch (Exception e)
        {
            p.sendMessage("§cError while sending you to §e" + server + "§c. (" + e.getMessage() + ")");
        }
        p.sendPluginMessage(Hub.plugin, "BungeeCord", b.toByteArray());
    }

    private void openSelector(Player p) {
        Inventory selector = Bukkit.createInventory(null, 9, "§cNavigation");

        ItemStack opf = new ItemStack(Material.EYE_OF_ENDER, 1);
        ItemMeta opfm = opf.getItemMeta();
        opfm.setDisplayName("§cOP Factions");
        ArrayList<String> oplore = new ArrayList<>();
        oplore.add("§4* §cGapple PvP");
        oplore.add("§4* §cOptimized Raiding");
        oplore.add("§4* §cExclusive Events");
        opfm.setLore(oplore);
        opf.setItemMeta(opfm);

        ItemStack f = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
        ItemMeta fm = f.getItemMeta();
        fm.setDisplayName(" ");
        f.setItemMeta(fm);

        selector.setItem(0, f);
        selector.setItem(1, f);
        selector.setItem(2, f);
        selector.setItem(3, f);
        selector.setItem(4, opf);
        selector.setItem(5, f);
        selector.setItem(6, f);
        selector.setItem(7, f);
        selector.setItem(8, f);

        p.openInventory(selector);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.NETHER_STAR) {
            openSelector(p);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
            transfer(p, "OPFactions");
            e.setCancelled(true);
            p.closeInventory();
        }

    }


}
