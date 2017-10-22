package com.playground.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class App
{
    public static void main( String[] args )
    {
        /* If you resolve relative to the class, the path starts at the class directory.
           Given that this resource is sibling to the root package, you must indicate ‘/’.

           But if you resolve relative to the module, the path starts at the module root.
           On this case, whether you use / or not, makes no difference.
         */

        Class clazz = App.class;
        InputStream is1 = clazz.getResourceAsStream("/config.yml");
        URL url = clazz.getResource("/config.yml");
        Module m = clazz.getModule();
        try {
            InputStream is2 = url.openStream();
            InputStream is3 = m.getResourceAsStream("/config.yml");
            InputStream is4 = m.getResourceAsStream("config.yml");
            App.read(is1);
            App.read(is2);
            App.read(is3);
            App.read(is4);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void read(InputStream is) {
        String s = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        System.out.println("config.yml: " + s);
    }
}
