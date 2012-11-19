package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.EventService;
import org.avangarde.gnosis.vo.EventVo;

/**
 *
 * @author Alexander
 */
public class EventFacade extends Facade <EventVo> {

    public EventFacade(String PUName, EventService service) {
        super(PUName, service);
    }
    
    public List<EventVo> getEventsFromSubject(Integer subjectCode){
            try {
            em = emf.createEntityManager();
            return ((EventService)service).getEventsFromSubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }


}
