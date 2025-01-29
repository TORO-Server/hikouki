package net.harupiza.hikouki;

import net.harupiza.hikouki.commands.DebugCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Hikouki extends JavaPlugin implements Listener {
    static Hikouki instance;
    public static Hikouki getInstance() { return instance; }

    @Override
    public void onEnable() {
        // インスタンスを設定
        instance = this;

        Objects.requireNonNull(getServer().getPluginCommand("hikoukidebug")).setExecutor(new DebugCommand());

        // このインスタンスをイベントリスナーとして登録
        getServer().getPluginManager().registerEvents(this, this);
    }
}
