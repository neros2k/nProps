package n2k_.nprops.core.presenter;
import n2k_.nprops.base.APresenter;
import n2k_.nprops.base.IInteractor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
public class EventPresenter extends APresenter implements Listener {
    public EventPresenter(IInteractor INTERACTOR) {
        super(INTERACTOR);
    }
    @Override
    public void init() {
        JavaPlugin PLUGIN = super.getInteractor().getPlugin();
        PLUGIN.getServer().getPluginManager().registerEvents(this, PLUGIN);
    }
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent EVENT) {
        super.getInteractor().loadEngine(EVENT.getPlayer());
    }
    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent EVENT) {
        super.getInteractor().unloadEngine(EVENT.getPlayer());
    }
    @EventHandler
    public void onBlockPlace(@NotNull BlockPlaceEvent EVENT) {
        if(EVENT.isCancelled()) return;
        super.getInteractor().addBlock(EVENT.getPlayer(), EVENT.getBlock());
    }
    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent EVENT) {
        if(EVENT.isCancelled()) return;
        super.getInteractor().removeBlock(EVENT.getPlayer(), EVENT.getBlock());
    }
}

