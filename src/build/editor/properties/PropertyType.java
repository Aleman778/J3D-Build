package build.editor.properties;

import build.editor.ui.acomponents.APanel;
import java.util.Collection;
import javafx.beans.value.ChangeListener;

public abstract class PropertyType<T> extends APanel {

    public PropertyType() {
        super.setName("Property");
    }
    
    public void stateChanged(T oldValue, T newValue) {
        Collection<ChangeListener<T>> listeners = getChangeListeners();
        
        for (ChangeListener<T> listener: listeners) {
            listener.changed(null, oldValue, newValue);
        }
    }
    
    protected abstract Collection<ChangeListener<T>> getChangeListeners();
    
    public abstract void addChangeListener(ChangeListener<T> listener);
    
    public abstract void setValue(T value);
    public abstract T getValue();
}
