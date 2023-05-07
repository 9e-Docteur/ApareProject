package be.ninedocteur.apare.init;

import be.ninedocteur.apare.ApareFrame;
import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.api.module.ModuleRegistry;
import be.ninedocteur.apare.module.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleInit {
    private static List<Module> moduleToInit = new ArrayList();
    public static void init(){
        initApareModule();
        for(Module module : moduleToInit){
            ModuleRegistry.registerModule(module);
        }
    }

    public static void initModule(Module module){
        moduleToInit.add(module);
    }

    private static void initApareModule(){
        Module checkoutModule = new CheckoutModule();
        Module adminModule = new AdminModule();
        Module loginModule = new LoginModule();
        Module modListModule = new ModListModule();
        Module itemsModule = new ItemsModule();
        Module itemListModule = new ItemListModule();
        ModuleRegistry.registerModule(checkoutModule);
        ModuleRegistry.registerModule(adminModule);
        ModuleRegistry.registerModule(loginModule);
        ModuleRegistry.registerModule(modListModule);
        ModuleRegistry.registerModule(itemsModule);
        ModuleRegistry.registerModule(itemListModule);
        ApareFrame.setLoaded(true);
    }
}
