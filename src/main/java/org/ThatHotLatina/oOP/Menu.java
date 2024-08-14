package org.ThatHotLatina.oOP;

import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;


public class Menu implements Listener, CommandExecutor {
    private String[] addAllArray(String[] ... addedArrays) {
        ArrayList<String> tempArray = new ArrayList<>();
        for (String[] array : addedArrays) {
            Collections.addAll(tempArray,array);
        }
        String[] outputArray = tempArray.toArray(new String[tempArray.size()]);
        return outputArray;
    } // Hell itself, adds all the arrays given and outputs it as an array ;)
    private Integer[] addAllArray(Integer[] ... addedArrays) {
        ArrayList<Integer> tempArray = new ArrayList<>();
        for (Integer[] array : addedArrays) {
            Collections.addAll(tempArray,array);
        }
        Integer[] outputArray = tempArray.toArray(new Integer[tempArray.size()]);
        return outputArray;
    } // Copied for Integer
    private ItemStack[] addAllArray(ItemStack[] ... addedArrays) {
        ArrayList<ItemStack> tempArray = new ArrayList<>();
        for (ItemStack[] array : addedArrays) {
            Collections.addAll(tempArray,array);
        }
        return tempArray.toArray(new ItemStack[tempArray.size()]);
    } // Copied for Integer

    //private ItemStack item = getItem(new ItemStack(Material.[whatever your item is],name:"Item Name",modelnumber:[number for the model if costum texturepack exists],lore:"Description on the item":;

    // Misc Strings



    public Menu(NordVPN plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    String[][] MenuLookup = new String[3][Menus.length]; // organised through menu display name,menu name,inventory size
    private void addEnchant(Inventory menu, int index, Enchantment[] ench, int[] lvl) {
        ItemMeta bunger = menu.getItem(index).getItemMeta();
        for (int i = 0; i < ench.length; i++) {
            bunger.addEnchant(ench[i],lvl[i],true);
        }
        menu.getItem(index).setItemMeta(bunger);
    }
    private ItemStack itemEnchanter(ItemStack item,Enchantment[] ench,int[] lvl) { // IDK anymore ok the gear needs the enchants and this is how I'm doing
        ItemMeta bunger = item.getItemMeta();
        for (int i = 0; i < ench.length; i++) {
            bunger.addEnchant(ench[i],lvl[i],true);
        }
        item.setItemMeta(bunger);
        return item;
    }

    //TODO: KEEP THIS
    private void RecipeDesignator(Inventory menu,String form) {
        menu.getSize();
        int index =(int) Math.round((menu.getSize()/2)-3.5);
        if (form.equals("Craft")) {
            menu.setItem(index,getItem(new ItemStack(Material.CRAFTING_TABLE),"§6Crafting Table",0,"§eThis recipe can be made in a Crafting Table"));
        } else if (form.equals("Furnace")) {
            menu.setItem(index,getItem(new ItemStack(Material.FURNACE),"§6Furnace",0,"§eThis recipe can be smelted in a Furnace"));
        } else if (form.equals("Smithing")) {
            menu.setItem(index,getItem(new ItemStack(Material.SMITHING_TABLE),"§6Smithing Table",0,"§eThis recipe can be combined in a Smithing Table"));
        } else if (form.equals("Campfire")) {
            menu.setItem(index,getItem(new ItemStack(Material.CAMPFIRE),"§6Campfire",0,"§eThis recipe can be cooked in any campfire"));
        }
    }
    private void ReoccurringItems(Inventory menu,ItemStack item,int ... index) {
        for (int slot : index) {
            menu.setItem(slot,item);
        }
    }






    //TODO: KEEP THIS
    public void MenuFromRecipe(Inventory menu,String form,String recipekey) {
        ShapedRecipe recipe = getShapedRecipeByKey(recipekey);
        RecipeDesignator(menu,form);
        Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();
        String[] shape = recipe.getShape();
        int[][] indexequiv = {{3,4,5},{12,13,14},{21,22,23}};
        menu.setItem(16,recipe.getResult());
        for (int y = 0; y < 3; y++) {
            String row = shape[y];
            for (int x = 0; x < 3; x++) {
                char key = row.charAt(x);
                ItemStack ingredient = ingredientMap.get(key);
                if (ingredient != null) {
                    ingredient.setAmount(1);
                }
                menu.setItem(indexequiv[y][x],ingredient);
            }
        }
    }
    public ShapedRecipe getShapedRecipeByKey(String key) {
        NamespacedKey properkey = new NamespacedKey(instance,key); //fix instance, or remember whatever this means
        Recipe recipe = Bukkit.getRecipe(properkey);
        if (recipe instanceof ShapedRecipe) {
            return (ShapedRecipe) recipe;
        }
        return null;
    }

    //TODO: Figure out what this means
    @EventHandler
    public void PlayerClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) { // If there is no item
            return;
        }
        boolean tocontinue = true;
        for (int x = 0; x < Menus.length;x++) {
            if (Menus[x].getItemMeta().getDisplayName().equals(event.getView().getTitle())) {
                tocontinue = true;
                break;
            } else {
                tocontinue = false;
            }
        }
        if (!(tocontinue)) {
            for (int x = 0; x < NormalMenus.length;x++){
                if (new TranslatableComponent(NormalMenus[x].getTranslationKey()).toPlainText().equals(event.getView().getTitle())) {
                    tocontinue = true;
                    break;
                } else {
                    tocontinue = false;
                }
            }
        }
        if (!(tocontinue)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() != null) {
            ItemStack slotname = event.getCurrentItem();
            for (int x = 0; x < Menus.length; x++) {
                if (slotname.equals(Menus[x])) {
                    if (Menus[x].getItemMeta().getDisplayName().equals("")) {
                        MenuOpen(player, GetMenu(player, Integer.parseInt(MenuLookup[2][x]), new TranslatableComponent(Menus[x].getTranslationKey()).toPlainText()),Menus[x]);
                    } else {
                        MenuOpen(player, GetMenu(player, Integer.parseInt(MenuLookup[2][x]), Menus[x].getItemMeta().getDisplayName()),Menus[x]);
                        break;
                    }
                }
            }
        }
        event.setCancelled(true);
    }

    public Inventory GetMenu(Player player, int Size, String name) {
        return Bukkit.createInventory(player,Size*9,name);
    }
    public void MenuOpen(Player player,Inventory menu,ItemStack item) {
        MenuChain(item,menu);
        if (!(item == MenuName)){
            menu.setItem(0,getItem(new ItemStack(Material.TORCH),"Menu",0,"Back to main Menu"));
        }
        player.openInventory(menu);
    }

    public void MenuInitializer(Player player) {
        for (int x = 0 ; x < Menus.length ; x++) {
            MenuLookup[2][x] = String.valueOf(InventorySize[x]);
        }
        Inventory menu = Bukkit.createInventory(player,9*3,MenuName.getItemMeta().getDisplayName());
        menu.setItem(4,getItem(new ItemStack(Material.TORCH),"§1Info",1,"§9All the server info you need"));
        player.openInventory(menu);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        MenuInitializer(player);
        return true;
    }

    private ItemStack getItem(ItemStack item,String name,int modelnumber ,String ... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
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
//544