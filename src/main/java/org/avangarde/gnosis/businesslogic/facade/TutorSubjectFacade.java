package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.TutorSubjectService;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Alexander
 */
public class TutorSubjectFacade extends Facade <TutorSubjectVo> {

    public TutorSubjectFacade(String PUName, TutorSubjectService service) {
        super(PUName, service);
    }    
}
