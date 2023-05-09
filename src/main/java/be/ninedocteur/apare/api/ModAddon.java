package be.ninedocteur.apare.api;

import be.ninedocteur.apare.Apare;

public abstract class ModAddon {
    private String modName;
    private String modVersion;

    public ModAddon(String modName, String modVersion){
        this.modName = modName;
        this.modVersion = modVersion;
        Apare.registerModAddon(this);
    }

    public abstract void loadMod();

    public String getModName() {
        return modName;
    }

    public String getModVersion() {
        return modVersion;
    }
}
