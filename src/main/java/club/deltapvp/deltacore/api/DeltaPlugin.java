package club.deltapvp.deltacore.api;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class DeltaPlugin extends JavaPlugin {

    @Override
    public abstract void onEnable();

    @Override
    public abstract void onDisable();
}
