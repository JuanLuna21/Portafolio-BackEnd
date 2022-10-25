
package com.portafolio.juan.Controller;

import com.portafolio.juan.Dto.dtoHys;
import com.portafolio.juan.Entity.Hys;
import com.portafolio.juan.Security.Dto.Mensaje;
import com.portafolio.juan.Services.SHys;
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
@RequestMapping("hys")
@CrossOrigin(origins = "*")

public class CHys {
    
      @Autowired
      SHys sHys;
      
      
    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys>list = sHys.list();
        return new ResponseEntity(list,HttpStatus.OK);
        
    }
    
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody dtoHys dtohys){
    
        if(StringUtils.isBlank(dtohys.getNombreH()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(sHys.existsByNombreH(dtohys.getNombreH()))
                return new ResponseEntity(new Mensaje("Esa tecnologia existe"), HttpStatus.BAD_REQUEST);
            
            Hys hys = new Hys(dtohys.getNombreH(), dtohys.getDescripcionH(), dtohys.getPorcentajeH());
            sHys.save(hys);
            
            return new ResponseEntity(new Mensaje("tecnologia agregada"),HttpStatus.OK);
    }
    
     @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        
        if(!sHys.existById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        
        if(sHys.existsByNombreH(dtohys.getNombreH()) && sHys.getByNombreH(dtohys.getNombreH()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtohys.getNombreH()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        
        Hys hys = sHys.getOne(id).get();
        hys.setNombreH(dtohys.getNombreH());
        hys.setDescripcionH((dtohys.getDescripcionH()));
        hys.setPorcentajeH(dtohys.getPorcentajeH());
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
   
     @GetMapping("/details/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        
        if(!sHys.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Hys hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") int id){
    
    if(!sHys.existById(id))
        return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
    
    sHys.delete(id);
    
    return new ResponseEntity(new Mensaje("Experiencia Eliminada"),HttpStatus.OK);
    }
    
}
