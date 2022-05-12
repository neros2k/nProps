package n2k_.nprops.base;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public interface IInteractor extends IInitializable {
    void loadEngine(Player PLAYER);
    void unloadEngine(Player PLAYER);
    void clear(Player PLAYER);
    IEngine getEngine(Player PLAYER);
    IEngine getEngineFromLocation(Location LOCATION);
    JavaPlugin getPlugin();
}
