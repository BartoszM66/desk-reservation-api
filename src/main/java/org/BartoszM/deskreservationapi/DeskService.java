package org.BartoszM.deskreservationapi;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DeskService {
    private final DeskRepository deskRepository;

    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    public void addDesk(Desk desk) {
        if (deskRepository.existsByDeskNumber(desk.getDeskNumber())) {
            throw new IllegalArgumentException("Biurko o numerze " + desk.getDeskNumber() + " już istnieje w systemie!");
        }
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

        if (!existingDesk.isAvailable() && !updatedDesk.isAvailable()) {
            throw new IllegalArgumentException("To biurko jest już zajęte! Aby je zarezerwować, ktoś musi je najpierw zwolnić.");
        }

        if (!updatedDesk.isAvailable() && (updatedDesk.getEmployeeName() == null || updatedDesk.getEmployeeName().isBlank())) {
            throw new IllegalArgumentException("Musisz podać imię, aby zarezerwować biurko!");
        }

        if (updatedDesk.isAvailable()) {
            updatedDesk.setEmployeeName(null);
        }

        existingDesk.setDeskNumber(updatedDesk.getDeskNumber());
        existingDesk.setAvailable(updatedDesk.isAvailable());
        existingDesk.setEmployeeName(updatedDesk.getEmployeeName());

        deskRepository.save(existingDesk);
    }
}
