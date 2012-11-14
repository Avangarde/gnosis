package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.ActivityService;
import org.avangarde.gnosis.vo.ActivityVo;

/**
 *
 * @author Alexander
 */
public class ActivityFacade extends Facade <ActivityVo> {

    public ActivityFacade(String PUName, ActivityService service) {
        super(PUName, service);
    }
    
    public List<ActivityVo> getActivitiesBySubject(Integer subjectCode) {
        try {
            em = emf.createEntityManager();
            return ((ActivityService)service).getActivitiesBySubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
  
}
