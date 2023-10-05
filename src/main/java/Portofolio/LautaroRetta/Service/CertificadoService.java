/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Certificado;
import Portofolio.LautaroRetta.Repository.ICertificadoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CertificadoService {
    @Autowired ICertificadoRepository icertiRepo;

   
    public List<Certificado> getCertificado() {
        return icertiRepo.findAll();
    }

    
    public void saveCertificado(Certificado certi) {
         icertiRepo.save(certi);
    }

  
    public void deleteCertificado(int id) {
        icertiRepo.deleteById(id);
    }

    
    public Certificado findCertificado(int id) {
        Certificado certi = icertiRepo.findById(id).orElse(null);
        return certi;
    }
    
    
    public boolean existById(int id){
      return icertiRepo.existsById(id);
    }
    
    public boolean existByCertificado(String desc){
      return icertiRepo.existsByDescripcion(desc);
    }  
    
    public Optional<Certificado>getOne(int id){
        return icertiRepo.findById(id);
    }
   
    public Optional<Certificado> getCertificado(String desc){
       return icertiRepo.findByDescripcion(desc);
    }
}
