
package Portofolio.LautaroRetta.Interface;

import Portofolio.LautaroRetta.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //traer una Persona
    public List<Persona> getPersona();
    
    //guardar objeto de la tipo Persona
    public void savePersona(Persona Persona);
    
    //Eliminar un objeto de tipo Persona segun su id
    public void deletePersona(Long id);
    
    //buscar un objeto de tipo Persona por id
    public Persona findPersona(Long id);
    
}
