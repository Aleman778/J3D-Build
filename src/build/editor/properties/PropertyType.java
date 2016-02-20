package build.editor.properties;

import build.editor.ui.acomponents.APanel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javax.swing.ImageIcon;

public abstract class PropertyType<T> extends APanel {

    private ImageIcon icon= null;
    private final List<ChangeListener<T>> listeners;
    
    public PropertyType() {
        super.setName("Property");
        listeners = new ArrayList<>();
    }
    
    public final void stateChanged(T oldValue, T newValue) {
        for (ChangeListener<T> listener: listeners) {
            listener.changed(null, oldValue, newValue);
        }
    }
    
    public final Collection<ChangeListener<T>> getChangeListeners() {
        return listeners;
    }
    
    public final void addChangeListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIcon() {
        return icon;
    }
    
    public abstract void setValue(T value);
    public abstract T getValue();
}
