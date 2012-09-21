package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.TopicService;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Alexander
 */
public class TopicFacade extends Facade <TopicVo> {

    public TopicFacade(String PUName, TopicService service) {
        super(PUName, service);
    }  
}
