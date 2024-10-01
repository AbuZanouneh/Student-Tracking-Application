package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Service.AdvertisementsService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
public class AdvertisementBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AdvertisementsService advertisementsService;

    private Advertisements advertisement = new Advertisements();

    private List<Advertisements> advertisements;


    @PostConstruct
    public void init() {
        loadAdvertisements();
    }

    // Call this method when the page is loaded
    public void initialize() {
        loadAdvertisementDetails();
        loadAdvertisements(); // Optionally load all advertisements as well
    }


//    @PostConstruct
//    public void init() {
//        loadAdvertisements();
//        loadAdvertisementDetails(); // Load details of the selected advertisement
//    }

    public void loadAdvertisements() {
        advertisements = advertisementsService.findAllAdvertisements();
    }

    public void saveAdvertisement() {
        advertisementsService.saveAdvertisement(advertisement);
        // To reset the form
        advertisement = new Advertisements();
        // Reload the advertisements
        loadAdvertisements();
    }

    public void deleteAdvertisement(Long id) {
        advertisementsService.deleteAdvertisementById(id);
        // Reload the advertisements
        loadAdvertisements();
    }

    // Getters and Setters
    public Advertisements getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisements advertisement) {
        this.advertisement = advertisement;
    }

    public List<Advertisements> getAdvertisements() {
        return advertisements;
    }


//public void loadAdvertisementDetails() {
//    String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//    if (idParam != null) {
//        try {
//            Long id = Long.valueOf(idParam);
//            advertisement = advertisementsService.findAdvertisementById(id); // Fetch advertisement by ID
//        } catch (NumberFormatException e) {
//            // Handle invalid ID format
//            advertisement = new Advertisements(); // Or set to null or redirect elsewhere
//        }
//    } else {
//        advertisement = new Advertisements(); // Set a default advertisement or handle as needed
//    }
//}

    public void loadAdvertisementDetails() {
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idParam != null) {
            try {
                Long id = Long.valueOf(idParam);
                advertisement = advertisementsService.findAdvertisementById(id); // Fetch advertisement by ID
            } catch (NumberFormatException e) {
                advertisement = null; // Handle invalid ID format
            }
        }
    }

    // This method for redirection
    public String redirectToViewAdvertisement(Long id) {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/ui/view-advertisement.xhtml?id=" + id);
            return null; // Return null - handling navigation directly.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String redirectToViewAdvertisement(Long id) {
//        try {
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            ec.redirect(ec.getRequestContextPath() + "/ui/view-advertisement.xhtml?id=" + id);
//            return null; // Return null - handling navigation directly.
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}