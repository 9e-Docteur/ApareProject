package be.ninedocteur.apare;

import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.utils.Logger;

public class ApareBaseAddon extends ModAddon {
    public ApareBaseAddon(String modName, String modVersion) {
        super(modName, modVersion);
    }

    @Override
    public void onModLoad() {
        Logger.send("Apare Project started successfuly!", Logger.Type.SUCCESS);
    }
}
