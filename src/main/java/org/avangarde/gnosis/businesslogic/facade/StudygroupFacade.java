package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.StudygroupService;
import org.avangarde.gnosis.vo.StudygroupVo;

/**
 *
 * @author Alexander
 */
public class StudygroupFacade extends Facade <StudygroupVo> {

    public StudygroupFacade(String PUName, StudygroupService service) {
        super(PUName, service);
    }   
}
