package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.SubjectService;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
public class SubjectFacade extends Facade <SubjectVo> {

    public SubjectFacade(String PUName, SubjectService service) {
        super(PUName, service);
    }
    
    public List<SubjectVo> getSubjectsByProgram (Integer programCode){
        try {
            return ((SubjectService)service).getSubjectsByProgram(programCode, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
