package dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.PlantaReciclaje;

@Repository
public interface PlantaRepository extends JpaRepository<PlantaReciclaje, String> {}

