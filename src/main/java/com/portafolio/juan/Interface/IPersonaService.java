
package com.portafolio.juan.Interface;
import com.portafolio.juan.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    
    public List<Persona> getPersona();
    
    public void savePersona(Persona persona);
    
    public void deletePersona (int id);
    
    public Persona findPersona(int id);
}