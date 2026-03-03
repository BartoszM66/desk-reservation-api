package org.BartoszM.deskreservationapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeskController {
    private final DeskService deskService;

    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping("/desks")
    public List<Desk> getAllDesks() {
        return deskService.getAllDesks();
    }

    @PostMapping("/desks")
    public void addDesk(@Valid @RequestBody Desk desk) {
        deskService.addDesk(desk);
    }

    @DeleteMapping("/desks/{id}")
    public void deleteDesk(@PathVariable Long id) {
        deskService.deleteDesk(id);
    }

    @PutMapping("/desks/{id}")
    public void updateDesk(@PathVariable Long id, @Valid @RequestBody Desk desk) {
        deskService.updateDesk(id, desk);
    }
}
