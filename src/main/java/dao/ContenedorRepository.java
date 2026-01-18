package dao;

import com.entity.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, String> {
    
    /**
     * Busca todos los contenedores de un código postal específico
     */
    List<Contenedor> findByCodigoPostal(Integer codigoPostal);
    
    /**
     * Busca un contenedor por su ID
     */
    Optional<Contenedor> findById(String id);
    
    /**
     * Guarda o actualiza un contenedor
     */
    Contenedor save(Contenedor contenedor);
    
    @Query("SELECT DISTINCT c FROM Contenedor c LEFT JOIN FETCH c.historial WHERE c.codigoPostal = :codigoPostal")
    List<Contenedor> findByCodigoPostalWithHistorial(@Param("codigoPostal") Integer codigoPostal);
    
}