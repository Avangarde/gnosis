package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.TutorService;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class TutorFacade extends Facade <TutorVo> {

    public TutorFacade(String PUName, TutorService service) {
        super(PUName, service);
    }  
}
