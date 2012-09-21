package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.PublicationService;
import org.avangarde.gnosis.vo.PublicationVo;

/**
 *
 * @author Alexander
 */
public class PublicationFacade extends Facade <PublicationVo> {

    public PublicationFacade(String PUName, PublicationService service) {
        super(PUName, service);
    } 
}
