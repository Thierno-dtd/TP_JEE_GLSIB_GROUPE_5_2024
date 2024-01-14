package GLSIB_GROUPE5.example.EgaSApplication.repositories;

import GLSIB_GROUPE5.example.EgaSApplication.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
