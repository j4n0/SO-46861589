package com.framework;

import java.io.*;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main
{
    public static void main( String[] args )
    {
        Optional<Module> otherModule = ModuleLayer.boot().findModule("PlaygroundApi");
        otherModule.ifPresent(other -> {
            try {
                InputStream is1 = other.getResourceAsStream("config.yml");
                InputStream is2 = Class.forName("com.playground.api.App").getResourceAsStream("/config.yml");
                Main.read(is1);
                Main.read(is2);
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
