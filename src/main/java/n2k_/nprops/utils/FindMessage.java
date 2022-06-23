package n2k_.nprops.utils;
import n2k_.nprops.base.model.MessagesModel;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.List;
public class FindMessage {
    private final List<Location> LOCATION_LIST;
    private final Player PLAYER;
    private final MessagesModel MESSAGES_MODEL;
    private Location CURRENT_LOCATION;
    public FindMessage(List<Location> LOCATION_LIST, Player PLAYER, MessagesModel MESSAGES_MODEL) {
        this.LOCATION_LIST = LOCATION_LIST;
        this.PLAYER = PLAYER;
        this.MESSAGES_MODEL = MESSAGES_MODEL;
    }
    public String get() {
        StringBuilder BUILDER = new StringBuilder();
        BUILDER.append(this.placeholder(this.MESSAGES_MODEL.FIND_MESSAGE_TITLE));
        this.LOCATION_LIST.forEach(LOCATION -> {
            this.CURRENT_LOCATION = LOCATION;
            BUILDER.append(this.placeholder(this.MESSAGES_MODEL.FIND_MESSAGE_ELEMENT));
        });
        BUILDER.append(this.placeholder(this.MESSAGES_MODEL.FIND_MESSAGE_FOOTER));
        return BUILDER.toString();
    }
    private String placeholder(String STR) {
        String RESULT = STR;
        for(PHDecorator decorator : Placeholders.values()) {
            RESULT = decorator.decorate(RESULT, this);
        }
        return RESULT;
    }
    enum Placeholders implements PHDecorator {
        size((STR, FIND_MESSAGE) -> STR.replace("{size}", FIND_MESSAGE.LOCATION_LIST.size()+"")),
        x((STR, FIND_MESSAGE) -> STR.replace("{x}", FIND_MESSAGE.CURRENT_LOCATION.getBlockX()+"")),
        y((STR, FIND_MESSAGE) -> STR.replace("{y}", FIND_MESSAGE.CURRENT_LOCATION.getBlockY()+"")),
        z((STR, FIND_MESSAGE) -> STR.replace("{z}", FIND_MESSAGE.CURRENT_LOCATION.getBlockZ()+"")),
        type((STR, FIND_MESSAGE) -> STR.replace("{type}", FIND_MESSAGE.CURRENT_LOCATION.getBlock().getType().name())),
        player((STR, FIND_MESSAGE) -> STR.replace("{player}", FIND_MESSAGE.PLAYER.getName()))
        ;
        private final PHDecorator PH_DECORATOR;
        Placeholders(PHDecorator PH_DECORATOR) {
            this.PH_DECORATOR = PH_DECORATOR;
        }
        @Override
        public String decorate(String STR, FindMessage FIND_MESSAGE) {
            return this.PH_DECORATOR.decorate(STR, FIND_MESSAGE);
        }
    }
    interface PHDecorator {
        String decorate(String STR, FindMessage FIND_MESSAGE);
    }
}
