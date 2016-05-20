package j3dbuild.project;

import j3dbuild.core.Application;
import java.io.File;

public final class Project extends Item {
    
    public final File folderSource;
    public final File folderProject;
    public final File folderResources;
    private final File fileJ3DBP;
    
    public Project(String title, File projectFolder) {
        super(title, projectFolder);
        
        this.title           = title;
        this.file            = projectFolder;
        this.fileJ3DBP       = new File(getFile(), "/j3dbuild/project.xml");
        this.folderProject   = new File(getFile(), "/j3dbuild/");
        this.folderResources = new File(getFile(), "/res/");
        this.folderSource    = new File(getFile(), "/src/");
    }

    public static Project load(File fileJ3DB) {
        File projectFolder = fileJ3DB.getParentFile().getParentFile();
        if (projectFolder.isDirectory()) {
            return new Project(projectFolder.getName(), projectFolder);
        }
        
        return null;
    }
    
    public void create() {
        getFile().mkdirs();
        folderSource.mkdirs();
        folderProject.mkdirs();
        folderResources.mkdirs();
    }
    
    public void open() {
        Application.projects.addProject(this);
    }
    
    @Override
    public void close() {
        Application.projects.removeProject(this);
    }
}
