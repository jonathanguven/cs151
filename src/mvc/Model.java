package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    private Boolean unsavedChanges = false;
    private String fileName = null;

    public Model () {
    }

    public void changed () {
        this.unsavedChanges = true;
        this.notifySubscribers();
    }

    public boolean getUnsavedChanges () {
        return this.unsavedChanges;
    }

    public void setUnsavedChanges (boolean b) {
        this.unsavedChanges = b;
    }

    public String getFileName () {
        return this.fileName;
    }

    public void setFileName (String fName) {
        this.fileName = fName;
    }
}
