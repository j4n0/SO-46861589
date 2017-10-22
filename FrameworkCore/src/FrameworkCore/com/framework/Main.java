package com.framework;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main
{
    public static void main( String[] args )
    {
        // load from anywhere in the modulepath
        try {
            URL url = ClassLoader.getSystemResources("config.yml").nextElement();
            InputStream is = url.openStream();
            Main.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // load from the the module where a given class is
        try {
            InputStream is = Class.forName("com.playground.api.App").getResourceAsStream("/config.yml");
            Main.read(is);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // load from a specific module
        Optional<Module> specificModule = ModuleLayer.boot().findModule("PlaygroundApi");
        specificModule.ifPresent(module -> {
            try {
                InputStream is = module.getResourceAsStream("config.yml");
                Main.read(is);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void read(InputStream is) {
        String s = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        System.out.println("config.yml: " + s);
    }
}
