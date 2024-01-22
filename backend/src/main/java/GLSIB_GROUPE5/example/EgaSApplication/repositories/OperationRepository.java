package GLSIB_GROUPE5.example.EgaSApplication.repositories;

import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByNuCptAndDateAfter(String numCpt, LocalDate date);
}
