package n2k_.nprops.core;
import n2k_.nprops.base.APresenter;
import n2k_.nprops.base.IEngine;
import n2k_.nprops.base.IInteractor;
import n2k_.nprops.base.IRepository;
import n2k_.nprops.core.presenter.CommandPresenter;
import n2k_.nprops.core.presenter.EventPresenter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
public class Interactor implements IInteractor {
    private final IRepository REPOSITORY;
    private final List<APresenter> PRESENTER_LIST;
    private final Map<String, IEngine> ENGINE_LIST;
    private final JavaPlugin PLUGIN;
    public Interactor(JavaPlugin PLUGIN) {
        this.REPOSITORY = new Repository(this);
        this.PRESENTER_LIST = new ArrayList<>();
        this.ENGINE_LIST = new HashMap<>();
        this.PLUGIN = PLUGIN;
        this.PRESENTER_LIST.addAll(List.of(
                new CommandPresenter(this),
                new EventPresenter(this)
        ));
    }
    @Override
    public void init() {
        this.REPOSITORY.init();
        this.PRESENTER_LIST.forEach(APresenter::init);
    }
    @Override
    public void loadEngine(@NotNull Player PLAYER) {
        String NAME = PLAYER.getName();
        if(!this.ENGINE_LIST.containsKey(NAME)) {
            IEngine ENGINE = new Engine(this, PLAYER);
            ENGINE.start();
            List<Location> LOCATION_LIST = this.REPOSITORY.getByName(NAME);
            if(!LOCATION_LIST.isEmpty()) LOCATION_LIST.forEach(ENGINE::add);
            this.ENGINE_LIST.put(NAME, ENGINE);
        }
    }
    @Override
    public void unloadEngine(@NotNull Player PLAYER) {
        String NAME = PLAYER.getName();
        if(this.ENGINE_LIST.containsKey(NAME)) {
            IEngine ENGINE = this.ENGINE_LIST.get(NAME);
            ENGINE.clear();
            ENGINE.stop();
            this.ENGINE_LIST.remove(NAME);
        }
    }
    @Override
    public void addBlock(@NotNull Player PLAYER, Block BLOCK) {
        String NAME = PLAYER.getName();
        if(this.ENGINE_LIST.containsKey(NAME)) {
            IEngine ENGINE = this.ENGINE_LIST.get(NAME);
            if(ENGINE.getList().size() > 10) {
                ENGINE.getPLayer().sendMessage("ЭЙ чувачок ОСТАНОВИСЬ!!");
            } else {
                ENGINE.add(BLOCK.getLocation());
            }
        }
    }
    @Override
    public void removeBlock(@NotNull Player PLAYER, Block BLOCK) {
        String NAME = PLAYER.getName();
        if(this.ENGINE_LIST.containsKey(NAME)) {
            IEngine ENGINE = this.ENGINE_LIST.get(NAME);
            if(ENGINE.getList().isEmpty()) {
                Bukkit.getLogger().warning("[!] Line: 78 | Removing block from empty list");
            } else {
                ENGINE.remove(BLOCK.getLocation());
            }
        }
    }
    @Override
    public void clear(@NotNull Player PLAYER) {
        String NAME = PLAYER.getName();
        if(this.ENGINE_LIST.containsKey(NAME)) {
            this.ENGINE_LIST.get(NAME).clear();
        }
    }
    @Override
    public IEngine getEngine(@NotNull Player PLAYER) {
        return this.ENGINE_LIST.getOrDefault(PLAYER.getName(), null);
    }
    @Override
    public IEngine getEngineFromLocation(Location LOCATION) {
        AtomicReference<IEngine> RETURN_ENGINE = new AtomicReference<>(null);
        this.ENGINE_LIST.forEach((NAME, ENGINE) -> ENGINE.getList().forEach(ENGINE_LOCATION -> {
            if(ENGINE_LOCATION == LOCATION) RETURN_ENGINE.set(ENGINE);
        }));
        return RETURN_ENGINE.get();
    }
    @Override
    public JavaPlugin getPlugin() {
        return this.PLUGIN;
    }
}
