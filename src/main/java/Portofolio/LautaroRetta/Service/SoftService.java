/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.SoftSkills;
import Portofolio.LautaroRetta.Repository.ISoftRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SoftService {
    @Autowired ISoftRepository isoftrepo;
    

    public List<SoftSkills> getHabilidad() {
        return isoftrepo.findAll();
    }


    public void saveHabilidad(SoftSkills SoftSkills) {
        isoftrepo.save(SoftSkills);
    }


    public void deleteHabilidad(int id) {
        isoftrepo.deleteById(id);
    }

    public SoftSkills findHabilida(int id) {
        SoftSkills SoftSkills = isoftrepo.findById(id).orElse(null);
        return SoftSkills;
    }
 
     // si existe la habilidad x id
    public boolean existById(int id){
      return isoftrepo.existsById(id);
    }
    //cmprobar si existe exp x habilidad
    public boolean existByHabilidad(String SoftSkills){
      return isoftrepo.existsByHabilidad(SoftSkills);
    }  
    //buscar exp por id
    public Optional<SoftSkills>getOne(int id){
        return isoftrepo.findById(id);
    }
    //buscar exp x habilidad
    public Optional<SoftSkills> getHabilidad(String SoftSkills){
       return isoftrepo.findByHabilidad(SoftSkills);
    }
}
