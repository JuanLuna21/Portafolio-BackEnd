package com.portafolio.juan.Security.Service;

import com.portafolio.juan.Security.Entity.Rol;
import com.portafolio.juan.Security.Enums.RolNombre;
import com.portafolio.juan.Security.Repository.RolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
    return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {        
        rolRepository.save(rol);
    }
}