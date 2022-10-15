
package com.portafolio.juan.Services;

import com.portafolio.juan.Entity.Persona;
import com.portafolio.juan.Interface.IPersonaService;
import com.portafolio.juan.Repository.IPersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements IPersonaService{
    
    @Autowired IPersonaRepository ipersonaRepository;
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = ipersonaRepository.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
       ipersonaRepository.save(persona);
    }

    @Override
    public void deletePersona(int id) {
     ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(int id) {
       Persona persona= ipersonaRepository.findById(id).orElse(null);
       return persona;
    }
}

