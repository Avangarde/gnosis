package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.EventService;
import org.avangarde.gnosis.vo.EventVo;

/**
 *
 * @author Alexander
 */
public class EventFacade extends Facade <EventVo> {

    public EventFacade(String PUName, EventService service) {
        super(PUName, service);
    }
}
