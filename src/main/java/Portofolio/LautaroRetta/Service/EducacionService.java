/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Educacion;
import Portofolio.LautaroRetta.Repository.IEducacionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional 
public class EducacionService {
    
@Autowired IEducacionRepository ieduRepo;

    public List<Educacion> getEducacion() {
        return ieduRepo.findAll();
    }

    public void saveEducacion(Educacion edu) {
        ieduRepo.save(edu);
    }

    public void deleteEducacion(int id) {
        ieduRepo.deleteById(id);
    }

    
    public Educacion findEducacion(int id) {
        Educacion educacion = ieduRepo.findById(id).orElse(null);
        
        return educacion;
    }
    
    // si existe la educacion x id
    public boolean existById(int id){
      return ieduRepo.existsById(id);
    }
    //cmprobar si existe exp x Educacion
    public boolean existByEducaucion(String titulo){
      return ieduRepo.existsByTitulo(titulo);
    }  
    //buscar exp por id
    public Optional<Educacion>getOne(int id){
        return ieduRepo.findById(id);
    }
    //buscar exp x empresa
    public Optional<Educacion> getEduacion(String titulo){
       return ieduRepo.findByTitulo(titulo);
    }
    
}
