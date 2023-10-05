/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Portofolio.LautaroRetta.Repository;

import Portofolio.LautaroRetta.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository  extends JpaRepository<Educacion, Integer>{
    
    
    public Optional<Educacion> findByTitulo(String titulo);
    
    public boolean existsByTitulo(String titulo);
}