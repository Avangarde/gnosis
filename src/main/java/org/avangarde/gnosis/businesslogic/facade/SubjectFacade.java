package org.avangarde.gnosis.businesslogic.facade;

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
}
