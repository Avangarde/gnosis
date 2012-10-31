package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.SubjectService;
import org.avangarde.gnosis.businesslogic.service.TutorSubjectService;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Alexander
 */
public class TutorSubjectFacade extends Facade <TutorSubjectVo> {

    public TutorSubjectFacade(String PUName, TutorSubjectService service) {
        super(PUName, service);
    }
    
    public List<TutorSubjectVo> getTutorsByName(String query) {
        try {
            return ((TutorSubjectService)service).getTutorsByName(query, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
