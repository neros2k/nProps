package n2k_.nprops.base;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Map;
public interface IInteractor extends IInitializable {
    void loadEngine(Player PLAYER);
    void unloadEngine(Player PLAYER);
    void onPlace(BlockPlaceEvent EVENT);
    void onBreak(BlockBreakEvent EVENT);
    void addBlock(Player PLAYER, Block BLOCK);
    void removeBlock(Player PLAYER, Block BLOCK);
    void clear(Player PLAYER);
    IEngine getEngine(Player PLAYER);
    IEngine getEngineFromLocation(Location LOCATION);
    JavaPlugin getPlugin();
    Map<String, IEngine> getMap();
}
