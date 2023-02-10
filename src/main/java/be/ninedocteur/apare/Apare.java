package be.ninedocteur.apare;

import be.ninedocteur.apare.api.ModAddon;
import be.ninedocteur.apare.api.module.Module;
import be.ninedocteur.apare.api.module.ModuleRegistry;
import be.ninedocteur.apare.init.ModuleInit;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tracing.dtrace.ModuleName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Apare {
    public static List<ModAddon> modList = new ArrayList();

    public static void main(String[] args) {
        ApareFrame apareFrame = new ApareFrame();
    }

    public static List<ModAddon> getModList() {
        return modList;
    }

    public static void registerModAddon(ModAddon modAddon){
        modList.add(modAddon);
    }

}
