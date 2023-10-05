
package Portofolio.LautaroRetta.Service;

import Portofolio.LautaroRetta.Entity.Persona;
import Portofolio.LautaroRetta.Interface.IPersonaService;
import Portofolio.LautaroRetta.Repository.IPersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service /*le indica que se comporte como un servicio a la Database*/
public class ImpPersonaService implements IPersonaService{
    /*implementamos la dependencia del repositorio para qu elea los campos que tenemos*/
    @Autowired IPersonaRepository iPersonaRepository; /* Autowired inyecta las dependencias en otras,  <tipo><nombre>*/
    
    @Override
    public List<Persona> getPersona() {
       List<Persona> Persona = iPersonaRepository.findAll(); /*Creo una variable de tipo lista Persona, 
                                                              donde alamceno la lista de Personas del repository*/
       return Persona;
    }

    @Override
    public void savePersona(Persona Persona) {
        iPersonaRepository.save(Persona); /*guarda el objeto Persona que pasa por parametro*/
    }

    @Override
    public void deletePersona(Long id) {
        iPersonaRepository.deleteById(id); /*elimina una Persona segun el id que pase por parametro*/  
    }

    @Override
    public Persona findPersona(Long id) {
        /*Busca por id que paso por parametro a la Persona y muestrala, si el id no existe retrona null*/
        Persona Persona = iPersonaRepository.findById(id).orElse(null); 
        return Persona;
    }
    
}