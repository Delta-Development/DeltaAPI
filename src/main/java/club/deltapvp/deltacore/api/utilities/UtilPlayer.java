package club.deltapvp.deltacore.api.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

@UtilityClass
public class UtilPlayer {

    /**
     * Resets the player to default stats
     * @param player Player
     */
    public void reset(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        player.setWalkSpeed(0.2F);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getOpenInventory().close();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setGameMode(GameMode.SURVIVAL);
        if (player.getVehicle() != null)
            player.leaveVehicle();
        if (player.getPassenger() != null)
            player.getPassenger().leaveVehicle();

    }

    /**
     * Checks if a location is in-between 2 locations
     *
     * @param loc Location 1
     * @param l1  Location 2
     * @param l2  Location 3
     * @return true or false
     */
    public boolean isInside(Location loc, Location l1, Location l2) {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        int x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        int y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        int z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        int x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        int y2 = Math.max(l1.getBlockY(), l2.getBlockY());
        int z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());

        return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
    }

    /**
     * Checks if a provided player's inventory is full
     * @param player Player
     * @return Returns true if the player's inventory is full, returns false if it is not.
     */
    public boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }

}
