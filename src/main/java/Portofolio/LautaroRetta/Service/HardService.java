/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Hard;
import Portofolio.LautaroRetta.Repository.IHardRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional 
public class HardService {
  @Autowired IHardRepository ihardrepo;

 
    public List<Hard> getHabilidad() {
        return ihardrepo.findAll();
    }


    public void saveHabilidad(Hard hard) {
        ihardrepo.save(hard);
    }


    public void deleteHabilidad(int id) {
        ihardrepo.deleteById(id);
    }

  
    public Hard findHabilida(int id) {
        Hard hard = ihardrepo.findById(id).orElse(null);
        return hard;
    }
 
     // si existe la educacion x id
    public boolean existById(int id){
      return ihardrepo.existsById(id);
    }
    //cmprobar si existe exp x Educacion
    public boolean existByHabilidad(String hard){
      return ihardrepo.existsByHabilidad(hard);
    }  
    //buscar exp por id
    public Optional<Hard>getOne(int id){
        return ihardrepo.findById(id);
    }
    //buscar exp x empresa
    public Optional<Hard> getHabilidad(String hard){
       return ihardrepo.findByHabilidad(hard);
    }
}