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
    
   
    private String img_Empresa;
    
    @NotBlank
   
    private String inicio;
    
    @NotBlank
    private String fin;
    
    private String url_info;
    
    //Constructor vacio
    public DtoExperiencia(){}
    //Constructor
    public DtoExperiencia(String Empresa, String Descripcion, String Img_Empresa,String Inicio, 
          String Fin, String url_info ){
        this.empresa = Empresa;
        this.descripcion = Descripcion;
        this.img_Empresa = Img_Empresa;
        this.inicio = Inicio;
        this.fin = Fin;
        this.url_info = url_info;
    }
}
