package org.avangarde.gnosis.businesslogic.facade;

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
}
