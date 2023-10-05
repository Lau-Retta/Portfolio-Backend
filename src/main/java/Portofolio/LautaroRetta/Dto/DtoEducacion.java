/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoEducacion {
    @NotBlank
    private String instituto;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;

    private String img_educacion;

    private String inicio;

    private String fin;

    //consturctor vacio

    public DtoEducacion() {
    }

    //constructor 
    public DtoEducacion(String instituto, String titulo, String descripcion, String img_educacion, String inicio, String fin) {
        this.instituto = instituto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img_educacion = img_educacion;
        this.inicio = inicio;
        this.fin = fin;
    }
}
