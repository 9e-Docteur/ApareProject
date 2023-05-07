package be.ninedocteur.apare.api;

public abstract class ModAddon {
    private String modName;
    private String modVersion;

    public ModAddon(String modName, String modVersion){
        this.modName = modName;
        this.modVersion = modVersion;
    }

    public abstract void onModLoad();

    public static void loadMod(ModAddon mod){
        mod.onModLoad();
    }

    public String getModName() {
        return modName;
    }

    public String getModVersion() {
        return modVersion;
    }
}
