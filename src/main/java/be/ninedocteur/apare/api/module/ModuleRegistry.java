package be.ninedocteur.apare.api.module;

import be.ninedocteur.apare.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModuleRegistry {
    public static List<Module> moduleList = new ArrayList();

    public static void registerModule(Module module){
        moduleList.add(module);
        Logger.send("Registering " + module.getName() + "...", Logger.Type.NORMAL);
    }

    public static List<Module> getModuleList() {
        return moduleList;
    }
}
