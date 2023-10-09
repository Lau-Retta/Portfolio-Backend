/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //agrega de forma automatica los getters y setter
@Builder // permite construir los objetos que necesito
@AllArgsConstructor // Nos permite que si luego agremos un nuevo atributo no es necesario ccrear los getter y setter ni los contrucxtores
@NoArgsConstructor
public class LoginRequest {
    String email;
    String password; 
}
