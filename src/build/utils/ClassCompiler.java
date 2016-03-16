package build.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ClassCompiler {
    
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    
    private final File root;
    private final File sourcefile;
    private final String classname;
    
    public ClassCompiler(File root, File sourcefile, String classname) {
        this.root = root;
        this.sourcefile = sourcefile;
        this.classname = classname;
    }
    
    
    public Object compile() {
        boolean success = (compiler.run(null, null, null, sourcefile.getPath()) == 0);
        System.out.println("Success: " + success);
        
        if (success) {
            try {
                URLClassLoader classloader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
                Class<?> clazz = Class.forName(classname, true, classloader);
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                System.err.println("Could not instatiate class: " + ex);
            } catch (ClassNotFoundException | MalformedURLException ex) {
                System.err.println("Class not found: " + ex);
            }
        }
        
        return null;
    }
}
