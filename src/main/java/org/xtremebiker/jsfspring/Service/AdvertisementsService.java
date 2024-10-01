package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.AdvertisementsRepository;
import org.xtremebiker.jsfspring.view.Advertisements;

import java.util.List;

// Service Layer
@Service
public class AdvertisementsService {

    @Autowired
    private AdvertisementsRepository advertisementsRepository;

    // retrieve all advertisements
    public List<Advertisements> findAllAdvertisements() {
        return advertisementsRepository.findAll();
    }

    // Save an advertisement
    public void saveAdvertisement(Advertisements advertisement) {
        advertisementsRepository.save(advertisement);
    }

    // Find an advertisement by its Id
    public Advertisements findAdvertisementById(Long id) {
        return advertisementsRepository.findById(id).orElse(null);
    }

    // Delete an advertisement by its Id
    public void deleteAdvertisementById(Long id) {
        advertisementsRepository.deleteById(id);
    }

}
