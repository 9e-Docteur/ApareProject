package be.ninedocteur.apare.api.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleRegistry {
    public static List<Module> moduleList = new ArrayList();

    public static void registerModule(Module module){
        moduleList.add(module);
        System.out.println("Registering " + module.getName() + "...");
    }

    public static List<Module> getModuleList() {
        return moduleList;
    }
}
