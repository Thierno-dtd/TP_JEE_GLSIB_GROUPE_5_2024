package GLSIB_GROUPE5.example.EgaSApplication.repositories;

import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import GLSIB_GROUPE5.example.EgaSApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByNumCptAndDateAfterAndDateBefore(String numCpt, LocalDateTime date, LocalDateTime dt);
    List<Operation> findByClientAndDateAfterAndDateBefore(User client, LocalDateTime date, LocalDateTime dt);

}
