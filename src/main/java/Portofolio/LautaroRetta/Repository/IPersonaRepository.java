/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Portofolio.LautaroRetta.Repository;

import Portofolio.LautaroRetta.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository /*aplicamos los metodos de JpaRepository que son los CRUD de la clase Persona e indicamos el tipo de su primary key */
public interface IPersonaRepository extends JpaRepository<Persona,Long> {
    
}
