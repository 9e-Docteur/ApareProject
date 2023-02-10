package be.ninedocteur.apare.api;

import com.sun.org.apache.xpath.internal.operations.Mod;

public class ModAddon {
    private String modName;
    private String modVersion;

    public ModAddon(String modName, String modVersion){
        this.modName = modName;
        this.modVersion = modVersion;
    }
}
