package build.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class ClassCompiler {
    
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    
    private final String classname;
    private final JavaFileObject file;
    private final Iterable<? extends JavaFileObject> compilationUnits;
    
    public ClassCompiler(String classname, String classdata) {
        this.classname = classname;
        file = new JavaSourceFromString(classname, classdata);
        compilationUnits = Arrays.asList(file);
    }
    
    
    public Object compile() {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        
        CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);
        boolean success = task.call();
        
        for (Diagnostic diagnostic: diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null));
        }
        System.out.println("Success: " + success);
        if (success) {
            try {
                System.out.println(classname);
                return Class.forName(classname).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        
        return null;
    }
}
