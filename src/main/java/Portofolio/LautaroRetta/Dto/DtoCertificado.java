/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Dto;
import javax.validation.constraints.NotBlank;

public class DtoCertificado {
    @NotBlank
    private String descripcion;
    @NotBlank
    private String certificado;
    
    //constructor vacio
    public DtoCertificado(){}
    
    //constructor 

    public DtoCertificado(String descripcion, String cetificado) {
        this.descripcion = descripcion;
        this.certificado = cetificado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String cetificado) {
        this.certificado = cetificado;
    }
}
