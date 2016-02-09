package build.editor.properties;

import build.editor.ui.acomponents.APanel;
import java.util.Collection;
import javafx.beans.value.ChangeListener;

public abstract class PropertyType<T> extends APanel {
    
    public void stateChanged(T value) {
        Collection<ChangeListener<T>> listeners = getChangeListeners();
        
        for (ChangeListener<T> listener: listeners) {
            listener.changed(null, null, value);
        }
    }
    
    protected abstract Collection<ChangeListener<T>> getChangeListeners();
    
    public abstract void addChangeListener(ChangeListener<T> listener);
    
    public abstract void setValue(T value);
    public abstract T getValue();
}
