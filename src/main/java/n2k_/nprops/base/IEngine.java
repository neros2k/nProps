package n2k_.nprops.base;
import org.bukkit.Location;
import java.util.List;
public interface IEngine extends IInitializable {
    void start();
    void stop();
    void tick();
    void add(Location LOCATION);
    void remove(Location LOCATION);
    void clear();
    List<Location> getList();
}
