package GLSIB_GROUPE5.example.EgaSApplication.repositories;

import GLSIB_GROUPE5.example.EgaSApplication.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
