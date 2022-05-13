package n2k_.nprops.core;
import n2k_.nprops.base.IEngine;
import n2k_.nprops.base.IInteractor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
public class Engine implements IEngine {
    private final IInteractor INTERACTOR;
    private final Player PLAYER;
    private final List<Location> LOCATION_LIST;
    private BukkitTask TICK_TASK;
    public Engine(IInteractor INTERACTOR, Player PLAYER) {
        this.INTERACTOR = INTERACTOR;
        this.PLAYER = PLAYER;
        this.LOCATION_LIST = new ArrayList<>();
    }
    @Override
    public void start() {
        this.TICK_TASK = Bukkit.getScheduler().runTaskTimer(this.INTERACTOR.getPlugin(), this::tick, 0L, 1L);
    }
    @Override
    public void stop() {
        this.TICK_TASK.cancel();
    }
    @Override
    public void tick() {
        this.LOCATION_LIST.removeIf(LOCATION -> LOCATION.getBlock().isEmpty());
        @Nullable Block BLOCK = this.PLAYER.getTargetBlockExact(3);
        if(BLOCK == null) return;
        @Nullable IEngine ENGINE = this.INTERACTOR.getEngineFromLocation(BLOCK.getLocation());
        if(ENGINE == null) return;
        this.PLAYER.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ENGINE.getPLayer().getName()));
    }
    @Override
    public void add(Location LOCATION) {
        if(!this.LOCATION_LIST.contains(LOCATION)) {
            this.LOCATION_LIST.add(LOCATION);
        }
    }
    @Override
    public void remove(Location LOCATION) {
        this.LOCATION_LIST.remove(LOCATION);
    }
    @Override
    public void clear() {
        this.LOCATION_LIST.forEach(LOCATION -> LOCATION.getBlock().setType(Material.AIR));
    }
    @Override
    public List<Location> getList() {
        return this.LOCATION_LIST;
    }
    @Override
    public Player getPLayer() {
        return this.PLAYER;
    }
}
