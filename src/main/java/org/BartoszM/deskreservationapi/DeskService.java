package org.BartoszM.deskreservationapi;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DeskService {
    private DeskRepository deskRepository;

    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    public void addDesk(Desk desk) {
        deskRepository.save(desk);
    }

    public List<Desk> getAllDesks(){
        return deskRepository.findAll();
    }

    public void deleteDesk(Long id) {
        deskRepository.deleteById(id);
    }

    public void updateDesk(Long id, Desk updatedDesk) {
        Desk existingDesk = deskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono biurka o ID: " + id));

        existingDesk.setDeskNumber(updatedDesk.getDeskNumber());
        existingDesk.setAvailable(updatedDesk.isAvailable());
        existingDesk.setEmployeeName(updatedDesk.getEmployeeName());

        deskRepository.save(existingDesk);
    }
}
