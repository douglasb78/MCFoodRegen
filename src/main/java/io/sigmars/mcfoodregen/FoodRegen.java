package io.sigmars.mcfoodregen;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public final class FoodRegen extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic

    }
    // Replace food mechanics
    @EventHandler
    public void onFoodConsume(PlayerItemConsumeEvent event) {
        ImmutableMap<Material, Double> foodHealMap = new ImmutableMap.Builder<Material, Double>()
                .put(Material.APPLE, 4.0)
                .put(Material.BAKED_POTATO, 5.0)
                .put(Material.BEETROOT, 1.0)
                .put(Material.BEETROOT_SOUP, 6.0)
                .put(Material.BREAD, 5.0)
                .put(Material.CAKE, 14.0)
                .put(Material.CARROT, 3.0)
                .put(Material.CHORUS_FRUIT, 4.0)
                .put(Material.TROPICAL_FISH, 1.0)
                .put(Material.COOKED_CHICKEN, 6.0)
                .put(Material.COOKED_COD, 5.0)
                .put(Material.COOKED_MUTTON, 6.0)
                .put(Material.COOKED_PORKCHOP, 8.0)
                .put(Material.COOKED_RABBIT, 5.0)
                .put(Material.COOKED_SALMON, 6.0)
                .put(Material.COOKIE, 2.0)
                .put(Material.DRIED_KELP, 1.0)
                .put(Material.ENCHANTED_GOLDEN_APPLE, 4.0)
                .put(Material.GOLDEN_APPLE, 4.0)
                .put(Material.GOLDEN_CARROT, 6.0)
                .put(Material.MELON_SLICE, 2.0)
                .put(Material.MUSHROOM_STEW, 6.0)
                .put(Material.POISONOUS_POTATO, 2.0)
                .put(Material.POTATO, 1.0)
                .put(Material.PUFFERFISH, 1.0)
                .put(Material.PUMPKIN_PIE, 8.0)
                .put(Material.RABBIT_STEW, 10.0)
                .put(Material.BEEF, 3.0)
                .put(Material.CHICKEN, 2.0)
                .put(Material.COD, 2.0)
                .put(Material.MUTTON, 2.0)
                .put(Material.PORKCHOP, 3.0)
                .put(Material.RABBIT, 3.0)
                .put(Material.SALMON, 2.0)
                .put(Material.ROTTEN_FLESH, 4.0)
                .put(Material.SPIDER_EYE, 2.0)
                .put(Material.COOKED_BEEF, 8.0)
                .build();
        ItemStack food = event.getItem();
        Player player = event.getPlayer();
        event.setCancelled(true);
        if(player.getHealth() < 20){
            // Remove item
            player.getInventory().removeItem(new ItemStack(food.getType(), 1));
            // Poisoning
            float chance = 0.0F;
            if (food.getType().equals(Material.CHICKEN)) {
                chance = 0.3F;
            }

            if (food.getType().equals(Material.SPIDER_EYE)) {
                chance = 1.0F;
            }

            if (food.getType().equals(Material.ROTTEN_FLESH)) {
                chance = 0.8F;
            }
            Random poison = new Random();
            if(poison.nextFloat() < chance){
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 1));
            }
            // Enchanted golden apple and golden apple effects
            if(food.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 16));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4000, 2));
            }
            if(food.getType().equals(Material.GOLDEN_APPLE)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
            }
            // Regeneration
            if((player.getHealth() + foodHealMap.get(food.getType())) < 20){
                player.setHealth(player.getHealth() + foodHealMap.get(food.getType()));
            }
            else{
                player.setHealth(20);
            }
        }
    }

    // Disable natural health regeneration
    @EventHandler (ignoreCancelled = true)
    public void onRegen(EntityRegainHealthEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)){
            if (event.getRegainReason().toString().equals("REGEN") || event.getRegainReason().toString().equals("SATIATED")){
                event.setCancelled(true);
            }
        }
    }

    // Disables hunger
    @EventHandler (ignoreCancelled = true)
    public void onFeelHungry(FoodLevelChangeEvent event) {
        event.setFoodLevel(18);
        event.setCancelled(true);
    }

    // Set hunger to 18 on join
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setFoodLevel(18);
    }

    // Set hunger to 18 on respawn
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                event.getPlayer().setFoodLevel(18);
            }
        }, 20L); // wait 20 ticks after respawn to set food level.
    }
}
