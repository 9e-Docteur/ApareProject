package be.ninedocteur.apare.api;

import be.ninedocteur.apare.Apare;
import be.ninedocteur.apare.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
    public ModLoader() {
        Apare.logger.send("Loading Mods...", Logger.Type.NORMAL);
        /*
        File modsDir = getOrCreateModDir();
        if (!modsDir.exists() || !modsDir.isDirectory()) {
            System.err.println("Le dossier des mods n'existe pas.");
            return;
        }

        File[] modFiles = modsDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (modFiles == null || modFiles.length == 0) {
            System.out.println("Aucun mod à charger.");
            return;
        }

        for (File modFile : modFiles) {
            try {
                URLClassLoader classLoader = new URLClassLoader(new URL[]{modFile.toURI().toURL()});
                Class<?> addonClass = classLoader.loadClass(modAddonClass.getAddonClassName());
                if (ModAddon.class.isAssignableFrom(addonClass)) {
                    Object addonInstance = addonClass.newInstance();
                    Method loadMethod = addonClass.getMethod("loadMod");
                    loadMethod.invoke(addonInstance);
                    System.out.println("Mod chargé depuis " + modFile.getName());
                } else {
                    System.out.println("Le fichier " + modFile.getName() + " ne contient pas de classe étendue à ModAddon.");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement du mod depuis " + modFile.getName() + ": " + e.getMessage());
            }
        */
        loadMods();
    }
    private static final String MODS_FOLDER = "C:\\ApareProject\\Mods";

    public static void loadMods() {
        File folder = new File(MODS_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));

        if (files == null) {
            System.out.println("No mods found");
            return;
        }

        for (File file : files) {
            try {
                URL url = file.toURI().toURL();
                URLClassLoader loader = new URLClassLoader(new URL[] { url });

                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (entry.getName().endsWith(".class")) {
                        String className = entry.getName().replace('/', '.').substring(0, entry.getName().length() - 6);
                        try {
                            Class<?> clazz = loader.loadClass(className);
                            if (clazz.isAnnotationPresent(Mod.class)) {
                                Object instance = clazz.getAnnotation(Mod.class).value().newInstance();
                                if (instance instanceof ModAddon) {
                                    ((ModAddon) instance).loadMod();
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            System.out.println("Failed to load class " + className + " from " + file.getName());
                        }
                    }
                }

                jarFile.close();
                loader.close();

            } catch (IOException e) {
                System.out.println("Failed to load mod from " + file.getName() + ": " + e.getMessage());
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("Failed to load mod from " + file.getName() + ": " + e.getMessage());
            }
        }
    }


    public static File getOrCreateModDir() {
        File imagesDir = new File("C:\\ApareProject\\Mods\\");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        return imagesDir;
    }
}

