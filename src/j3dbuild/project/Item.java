package j3dbuild.project;

import j3dbuild.editor.Editor;
import j3dbuild.editor.ui.AOptionPane;
import j3dbuild.utils.FileUtils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;

public class Item extends DefaultMutableTreeNode {
    
    public Editor editor = null;
    
    protected Project project;
    protected String title;
    protected File file;

    public Item(Project project, String title, File file) {
        super(title);
        this.project = project;
        this.title = title;
        this.file = file;
    }

    public Item(String title, File file) {
        super(title);
        this.project = null;
        this.title = title;
        this.file = file;
    }

    public Item() {
        super("");
        this.project = null;
        this.title = null;
        this.file = null;
    }
    
    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setTitle(String title) {
        this.title = title;
        setUserObject(title);
    }

    public String getTitle() {
        return title;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public Project getProject() {
        return project;
    }
    
    public final void refresh() {
        File[] files = getFile().listFiles();
        for (File f: files) {
            Item item;
            switch (FileUtils.getFileExtension(f)) {
                case "j3ds":
                    item = new Scene(project, f.getName(), f);
                break;
                default:
                    item = new Item(project, f.getName(), f);
                break;
            }
            if (!isHidden(item)) {
                insert(item, 0);

                if (f.isDirectory()) {
                    item.refresh();
                }
            }
        }
    }
    
    public void edit() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().edit(file);
            }
        } catch (IOException ex) {
            AOptionPane.showErrorMessage("java.io.IOException", "Failed to edit file: \n\r" + file.getPath() + "\n\r" + "Error report:\n\r" + ex.getMessage());
        }
    }
    
    public void close() {
        
    }
    
    public void save() {
        if (editor != null) {
            editor.save();
        }
    }
    
    public void saveAs(File file) {
        if (editor != null) {
            editor.saveAs(file);
        }
    }
    
    public void export(File file) {
        if (editor != null) {
            editor.export(file);
        }        
    }
    
    public static boolean isHidden(Item item) {
        if (item.file.getPath().equals(item.getProject().folderProject.getPath())) {
            return true;
        }
        
        return false;
    }
}
