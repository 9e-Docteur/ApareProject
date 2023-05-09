package be.ninedocteur.apare.event;

import be.ninedocteur.apare.utils.Logger;

public class LoggerEvent extends Event{
    private Logger logger;

    public LoggerEvent(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
