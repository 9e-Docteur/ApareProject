package be.ninedocteur.apare.api.event;

import be.ninedocteur.apare.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventRegistry {
    private static Map<Class<? extends Event>, List<Consumer<Event>>> listeners = new HashMap<>();

    public static <T extends Event> void addListener(Consumer<T> listener) {
        List<Consumer<Event>> eventListeners = getListeners((Class<? extends Event>) listener.getClass());
        if (eventListeners != null) {
            eventListeners.add((Consumer<Event>) listener);
        }
    }

    public static void fireEvent(Event event) {
        List<Consumer<Event>> eventListeners = getListeners(event.getClass());
        eventListeners.forEach(listener -> listener.accept(event));
    }

    private static List<Consumer<Event>> getListeners(Class<? extends Event> eventClass) {
        List<Consumer<Event>> eventListeners = listeners.get(eventClass);
        if (eventListeners == null) {
            eventListeners = new ArrayList<>();
            listeners.put(eventClass, eventListeners);
        }
        return eventListeners;
    }
}

