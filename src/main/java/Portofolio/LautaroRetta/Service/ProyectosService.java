/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Proyectos;
import Portofolio.LautaroRetta.Repository.IProyectosRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional 
public class ProyectosService {
    @Autowired IProyectosRepository iproyectosRepo;

    public List<Proyectos> getProyecto() {
        return iproyectosRepo.findAll();
    }

   
    public void saveProyecto(Proyectos Proyecto) {
        iproyectosRepo.save(Proyecto);
    }

    
    public void deleteProyecto(int id) {
        iproyectosRepo.deleteById(id);
    }

    
    public Proyectos findProyecto(int id) {
        Proyectos Proyecto = iproyectosRepo.findById(id).orElse(null);
        return Proyecto;
    }
 
     // si existe la educacion x id
    public boolean existById(int id){
      return iproyectosRepo.existsById(id);
    }
    //cmprobar si existe exp x Educacion
    public boolean existByNombre_p(String proyecto){
      return iproyectosRepo.existsByNombre(proyecto);
    }  
    //buscar exp por id
    public Optional<Proyectos>getOne(int id){
        return iproyectosRepo.findById(id);
    }
    //buscar exp x empresa
    public Optional<Proyectos> getNombre_p(String proyecto){
       return iproyectosRepo.findByNombre(proyecto);
    }
}
