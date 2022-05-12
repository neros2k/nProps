package n2k_.nprops.base;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.List;
public interface IEngine {
    void start();
    void stop();
    void tick();
    void add(Location LOCATION);
    void remove(Location LOCATION);
    void clear();
    List<Location> getList();
    Player getPLayer();
}
