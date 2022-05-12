package n2k_.nprops.core;
import n2k_.nprops.base.IInteractor;
import n2k_.nprops.base.IRepository;
import org.bukkit.Location;
import java.util.List;
public class Repository implements IRepository {
    public Repository(IInteractor INTERACTOR) {}
    @Override
    public void init() {

    }
    @Override
    public void save(List<Location> LIST, String NAME) {

    }
    @Override
    public List<Location> getByName(String NAME) {
        return null;
    }
}
