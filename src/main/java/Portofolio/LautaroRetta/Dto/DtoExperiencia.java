/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class DtoExperiencia {
     private String empresa;
@NotBlank
    private String descripcion;
 
    private String img_empresa;

    private String inicio;
    
    private String fin;
    
    private String url_info;
    
    //Constructor vacio
    public DtoExperiencia(){}
    //Constructor
    public DtoExperiencia(String empresa, String descripcion, String img_empresa,String inicio, 
          String fin, String url_info ){
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.img_empresa = img_empresa;
        this.inicio = inicio;
        this.fin = fin;
        this.url_info = url_info;
    }
}
