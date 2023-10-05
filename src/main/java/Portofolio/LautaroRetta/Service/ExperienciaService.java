/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Experiencia;
import Portofolio.LautaroRetta.Repository.IExperienciaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //Permite mantenes la persistencia, es decir lo que tengamos en el back tambien lo mantiene en la database
public class ExperienciaService {
 @Autowired IExperienciaRepository iexperienciaRepositosy;

    public List<Experiencia> getExperiencia() {
        return iexperienciaRepositosy.findAll();
    }
    
    //buscar exp por id
    public Optional<Experiencia> getOne(int id){
       return iexperienciaRepositosy.findById(id);
    }
    //buscar exp x empresa
    public Optional<Experiencia> getEmpresa(String empresa){
       return iexperienciaRepositosy.findByEmpresa(empresa);
    }
    //cmprobar si existe exp x id
    public boolean existById(int id){
      return iexperienciaRepositosy.existsById(id);
    }
    //cmprobar si existe exp x empresa
    public boolean existByEmpresa(String empresa){
      return iexperienciaRepositosy.existsByEmpresa(empresa);
    }
    public void saveExperiencia(Experiencia exp) {
        iexperienciaRepositosy.save(exp);
    }

    public void deleteExperiencia(int id) {
       iexperienciaRepositosy.deleteById(id);
    }

    public Experiencia findExperiencia(int id) {
        Experiencia exp = iexperienciaRepositosy.findById(id).orElse(null);
        return exp;
    }
}