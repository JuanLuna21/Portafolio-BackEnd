
package com.portafolio.juan.Controller;

import com.portafolio.juan.Dto.dtoProyecto;
import com.portafolio.juan.Entity.Proyecto;
import com.portafolio.juan.Security.Dto.Mensaje;
import com.portafolio.juan.Services.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proyecto")
@CrossOrigin(origins = "*")

public class CProyecto {
    
     @Autowired
      SProyecto sProyecto;
     
     
       
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto>list = sProyecto.list();
        return new ResponseEntity(list,HttpStatus.OK);
        
    }
    
       @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody dtoProyecto dtoproyecto){
    
        if(StringUtils.isBlank(dtoproyecto.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(sProyecto.existsByNombreP(dtoproyecto.getNombreP()))
                return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
            
            Proyecto proyecto = new Proyecto(dtoproyecto.getNombreP(), dtoproyecto.getDescripcionP(), dtoproyecto.getImgP());
            sProyecto.save(proyecto);
            
            return new ResponseEntity(new Mensaje("Experiencia agregada"),HttpStatus.OK);
    }
    
    
    
     @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto){
        
        if(!sProyecto.existById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        
        if(sProyecto.existsByNombreP(dtoproyecto.getNombreP()) && sProyecto.getByNombreP(dtoproyecto.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoproyecto.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreP(dtoproyecto.getNombreP());
        proyecto.setDescripcionP((dtoproyecto.getDescripcionP()));
        proyecto.setImgP(dtoproyecto.getImgP());
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
     @GetMapping("/details/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        
        if(!sProyecto.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") int id){
    
    if(!sProyecto.existById(id))
        return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
    
    sProyecto.delete(id);
    
    return new ResponseEntity(new Mensaje("Experiencia Eliminada"),HttpStatus.OK);
    }
    
}
