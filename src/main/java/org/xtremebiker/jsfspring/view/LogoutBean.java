package org.xtremebiker.jsfspring.view;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

import org.springframework.stereotype.Component;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
@Component
public class LogoutBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/ui/home.xhtml?faces-redirect=true";
    }
}
