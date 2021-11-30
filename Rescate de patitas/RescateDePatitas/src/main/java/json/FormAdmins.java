package json;

import java.util.List;

public class FormAdmins {
    List<String> nuevosAdmins;
    List<String> viejosAdmins;

    public FormAdmins(List<String> nuevosAdmins, List<String> viejosAdmins) {
        this.nuevosAdmins = nuevosAdmins;
        this.viejosAdmins = viejosAdmins;
    }

    public List<String> getNuevosAdmins() {
        return nuevosAdmins;
    }

    public void setNuevosAdmins(List<String> nuevosAdmins) {
        this.nuevosAdmins = nuevosAdmins;
    }

    public List<String> getViejosAdmins() {
        return viejosAdmins;
    }

    public void setViejosAdmins(List<String> viejosAdmins) {
        this.viejosAdmins = viejosAdmins;
    }
}
