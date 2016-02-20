package build.editor.scene;

import java.io.File;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.VirtualUniverse;

public class Universe extends VirtualUniverse {
    
    private final BranchGroup root;
    private final Locale locale;
    
    public Universe() {
        //Root
        root = new BranchGroup();
        root.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        root.setCapability(Group.ALLOW_CHILDREN_READ);
        root.setCapability(Group.ALLOW_CHILDREN_WRITE);
        
        //Locale
        locale = new Locale(this);
        locale.addBranchGraph(root);
    }
    
    public void addView(UniverseView view) {
        locale.addBranchGraph(view.getBranchGraph());
    }
    
    public void addChild(Node child) {
        root.addChild(child);
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public BranchGroup getRoot() {
        return root;
    }
    
    public void load(File file) {
        
    }
    
    public void save(File file) {
        
    }
    
    public void export(File file) {
        
    }

    @Override
    public String toString() {
        return "Universe";
    }
}
