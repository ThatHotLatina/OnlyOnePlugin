package org.ThatHotLatina.oOP;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class CraftingUtilold {
   //TODO: Fix recipe initializer, just look it up


//        ItemStack netherConduit = getItem(new ItemStack(Material.ENCHANTING_TABLE),"§cNether §3Conduit",2,"   §kPEEPEEPEEPEEPEEPEEPEE   ","§lThis feels like a bad Idea","   §kPEEPEEPEEPEEPEEPEEPEE   ");
//
//        ShapedRecipe netherConduitRecipe = new ShapedRecipe(new NamespacedKey(plugin,"netherconduit"),netherConduit);
//        netherConduitRecipe.shape("OCO","BFB","NEN");
//        netherConduitRecipe.setIngredient('O',Material.OBSIDIAN);
//        netherConduitRecipe.setIngredient('C',Material.CONDUIT);
//        netherConduitRecipe.setIngredient('B',Material.BLAZE_ROD);
//        netherConduitRecipe.setIngredient('F',Material.FIRE_CHARGE);
//        netherConduitRecipe.setIngredient('N',Material.NETHERITE_SCRAP);
//        netherConduitRecipe.setIngredient('E',Material.ENCHANTING_TABLE);
//        Bukkit.addRecipe(netherConduitRecipe);


//        ItemStack essenceFire = getItem(new ItemStack(Material.BLAZE_POWDER,2), "§6Essence of Fire", 3, "§eHot Hot HOT");
//
//        FurnaceRecipe essenceFireRecipe = new FurnaceRecipe(new NamespacedKey(plugin, "essencefire"), essenceFire, Material.BLAZE_POWDER, 1.0f, 200);
//        Bukkit.addRecipe(essenceFireRecipe);


//        ItemStack blisteringBoots = getItem(new ItemStack(Material.GOLDEN_BOOTS),"§6Blistering Boots",13);
//        ItemMeta bbmeta = blisteringBoots.getItemMeta();
//        bbmeta.addEnchant(Enchantment.DURABILITY,3,true);
//        bbmeta.addEnchant(Enchantment.SOUL_SPEED,2,true);
//        bbmeta.addEnchant(Enchantment.PROTECTION_FIRE,2,true);
//        blisteringBoots.setItemMeta(bbmeta);


//        CampfireRecipe warped = new CampfireRecipe(new NamespacedKey(plugin,"warpedtocharch"),new ItemStack(Material.CHARCOAL),Material.WARPED_STEM,1.0f,300);
//        Bukkit.addRecipe(warped);



    private static ItemStack getItem(ItemStack item, String name, int modelnumber, String... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        //meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(modelnumber);
        List<String> lores = new ArrayList<>();

        for (String s : lore) {
            lores.add(s);
        }

        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;

    }
}
//432 -> 64
