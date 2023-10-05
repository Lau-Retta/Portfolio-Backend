/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Portofolio.LautaroRetta.Repository;

import Portofolio.LautaroRetta.Entity.SoftSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoftRepository extends JpaRepository<SoftSkills, Integer>{
    public Optional<SoftSkills> findByHabilidad(String habilidad);
    
    public boolean existsByHabilidad(String habilidad);
}

