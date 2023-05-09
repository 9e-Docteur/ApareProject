package be.ninedocteur.apare;

import be.ninedocteur.apare.api.Mod;
import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.event.Event;
import be.ninedocteur.apare.event.LoggerEvent;
import be.ninedocteur.apare.utils.Logger;

public class ApareBaseAddon extends ModAddon {
    public ApareBaseAddon(String modName, String modVersion) {
        super(modName, modVersion);
    }

    @Override
    public void loadMod() {
        Apare.logger.send("Apare Project started successfuly!", Logger.Type.SUCCESS);

    }

    public static void testEvent(LoggerEvent event){
        Apare.logger.send("LOGGER NOT BLANK", Logger.Type.WARN);
    }
}
