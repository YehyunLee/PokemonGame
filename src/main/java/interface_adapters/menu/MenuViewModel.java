package interface_adapters.menu;
import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Pokemon";
    public static final String START_BUTTON_LABEL = "Start";
    public static final String IMAGE_PATH = "src/main/java/data_access/logo.png";

    private MenuState state = new MenuState();
    public MenuViewModel() {super("menu");}

    public void setState(MenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MenuState getState() {
        return state;
    }
}
