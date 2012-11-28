package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.ProgramService;
import org.avangarde.gnosis.vo.ProgramVo;

/**
 *
 * @author Alexander
 */
public class ProgramFacade extends Facade <ProgramVo> {

    public ProgramFacade(String PUName, ProgramService service) {
        super(PUName, service);
    } 

    public List<String> getProgramsBySubject(Integer subjectCode) {
       try {
            em = emf.createEntityManager();
            return ((ProgramService)service).getProgramsBySubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
