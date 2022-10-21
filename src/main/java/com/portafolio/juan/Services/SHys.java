
package com.portafolio.juan.Services;


import com.portafolio.juan.Entity.Hys;
import com.portafolio.juan.Repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHys {
    
    @Autowired
    RHys  rHys;
    
    public List<Hys> list(){
        return rHys.findAll();
    }
    
    public Optional<Hys> getOne(int id){
        return rHys.findById(id);
    }
    
    public  Optional<Hys> getByNombreH(String nombreH){
    return rHys.findByNombreH(nombreH);
    }
    
    public void save(Hys skill){
        rHys.save(skill);
    }
    
    public void delete(int id){
        
        rHys.deleteById(id);
    }
    
    public boolean existById(int id){
        return rHys.existsById(id);
    }
    
   public boolean existsByNombreH(String nombreH){
        return rHys.existsByNombreH(nombreH);
    }
}
