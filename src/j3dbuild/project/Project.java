package j3dbuild.project;

import j3dbuild.project.items.Item;
import java.io.File;
import java.util.ArrayList;

public final class Project {
    
    private static Item current;
    
    public final ArrayList<Item> items;
    public final String title;
    public final File folderRoot;
    public final File folderSource;
    public final File folderProject;
    public final File folderResources;
    private final File fileJ3DBP;
    
    public Project(String title, File projectFolder) {
        this.title = title;
        this.folderRoot = projectFolder;
        this.fileJ3DBP = new File(projectFolder, "/j3dbuild/project.xml");
        this.folderProject = new File(projectFolder, "/j3dbuild/");
        this.folderResources = new File(projectFolder, "/res/");
        this.folderSource = new File(projectFolder, "/src/");
        this.items = new ArrayList<>();
    }

    public static Project load(File fileJ3DB) {
        File projectFolder = fileJ3DB.getParentFile().getParentFile();
        if (projectFolder.isDirectory()) {
            return new Project(projectFolder.getName(), projectFolder);
        }
        
        return null;
    }
    
    public void create() {
        folderRoot.mkdirs();
        folderSource.mkdirs();
        folderProject.mkdirs();
        folderResources.mkdirs();
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
