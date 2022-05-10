package n2k_.nprops.base;
import org.bukkit.Location;
import java.util.List;
public interface IRepository extends IInitializable {
    void save(List<Location> LIST, String NAME);
    List<Location> getByName(String NAME);
}
