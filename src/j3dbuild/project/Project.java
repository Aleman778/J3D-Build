package j3dbuild.project;

import j3dbuild.project.items.Item;
import java.io.File;
import java.util.ArrayList;

public final class Project {
    
    private static Item current;
    
    private final String title;
    private final File folderRoot;
    private final File folderProject;
    private final File folderResources;
    private final File projectXML;
    private final ArrayList<Item> items;
    
    public Project(String title, File projectFolder) {
        this.title = title;
        this.folderRoot = projectFolder;
        this.projectXML = new File(projectFolder, "/j3dbuild/project.xml");
        this.folderProject = new File(projectFolder, "/j3dbuild/");
        this.folderResources = new File(projectFolder, "/res/");
        this.items = new ArrayList<>();
    }

    public void create() {
        folderRoot.mkdirs();
        folderProject.mkdirs();
        folderResources.mkdirs();
    }
    
    public void open(Item item) {
        
    }
    
    public void close(Item item) {
        
    }
    
    public void save(Item item) {
        
    }
    
    public void export(Item item) {
        
    }
    
    public void saveAll() {
        
    }
    
    public static Item getItem() {
        return current;
    }
}
