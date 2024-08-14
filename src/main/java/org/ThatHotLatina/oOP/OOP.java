package org.ThatHotLatina.oOP;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class OOP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        this.getServer().getConsoleSender().sendMessage("§a[§eOOP§a] §8Only One Plugin is now enabled");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
