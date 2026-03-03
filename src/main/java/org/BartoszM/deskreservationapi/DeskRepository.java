package org.BartoszM.deskreservationapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeskRepository  extends JpaRepository<Desk, Long> {
}
