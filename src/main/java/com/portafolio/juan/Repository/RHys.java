
package com.portafolio.juan.Repository;
import com.portafolio.juan.Entity.Hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHys extends JpaRepository<Hys,Integer> {
    
    public Optional<Hys> findByNombreH(String nombreH);
    public boolean existsByNombreH(String nombreH);
    
}
