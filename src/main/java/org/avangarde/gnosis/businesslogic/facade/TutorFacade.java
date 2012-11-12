package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.TutorService;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class TutorFacade extends Facade<TutorVo> {

    public TutorFacade(String PUName, TutorService service) {
        super(PUName, service);
    }

    public TutorVo findByUsername(TutorVo vo) {
        //TODO verificación de si el sujeto ya es tutor


        try {
            em = emf.createEntityManager();

            //metodo para obtener el vo de un tutor
            //condicional

            return ((TutorService) service).findByUsername(vo, em);



        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }




    }
}
