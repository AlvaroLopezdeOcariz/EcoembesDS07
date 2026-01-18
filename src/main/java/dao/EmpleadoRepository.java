package dao;

import com.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    
    /**
     * Busca un empleado por su email
     * Spring JPA genera automáticamente la implementación
     */
    Optional<Empleado> findByEmail(String email);
    
    /**
     * Busca un empleado por su ID
     */
    Optional<Empleado> findById(String id);
}