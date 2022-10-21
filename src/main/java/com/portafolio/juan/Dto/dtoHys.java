
package com.portafolio.juan.Dto;

import javax.validation.constraints.NotBlank;


public class dtoHys {
   
    @NotBlank
    private String nombreH;
    @NotBlank
    private String descripcionH;
    @NotBlank
    private int porcentajeH;

    public dtoHys() {
    }

    public dtoHys(String nombreH, String descripcionH, int porcentajeH) {
        this.nombreH = nombreH;
        this.descripcionH = descripcionH;
        this.porcentajeH = porcentajeH;
    }

    public String getNombreH() {
        return nombreH;
    }

    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }

    public String getDescripcionH() {
        return descripcionH;
    }

    public void setDescripcionH(String descripcionH) {
        this.descripcionH = descripcionH;
    }

    public int getPorcentajeH() {
        return porcentajeH;
    }

    public void setPorcentajeH(int porcentajeH) {
        this.porcentajeH = porcentajeH;
    }
  
    
    
}
