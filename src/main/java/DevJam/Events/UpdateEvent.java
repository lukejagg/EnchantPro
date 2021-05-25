package DevJam.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Required to extend Event
     * @return HANDLERS
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
