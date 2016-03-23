package j3dbuild.editor.resource;

import j3dbuild.editor.ui.acomponents.ATree;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class JTreeResources extends ATree {

    public static File projectRoot, resourceFolder;
    public static final JTreeResources instance = new JTreeResources();
    
    //Singleton
    private JTreeResources() {
        setCellRenderer(new ResourceGraphRenderer());
    }
    
    public void setProjectRoot(File root, File resfolder) {
        JTreeResources.projectRoot = root;
        JTreeResources.resourceFolder = resfolder;
        
        buildGraph(resfolder);
    }
    
    public void buildGraph(File file) {
        if (file.isDirectory()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
            buildFolder(node, file);
            TreeModel model = new DefaultTreeModel(node);
            setModel(model);
        }
        
    }
    
    private void buildFolder(DefaultMutableTreeNode parent, File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files == null)
                return;
            
            for (File f: files) {
                if (f.isDirectory()) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());
                    buildFolder(node, f);
                }
            }
        }
    }
    
}
