package GLSIB_GROUPE5.example.EgaSApplication.repositories;

import GLSIB_GROUPE5.example.EgaSApplication.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
