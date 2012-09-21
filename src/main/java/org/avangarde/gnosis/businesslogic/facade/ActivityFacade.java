package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.ActivityService;
import org.avangarde.gnosis.vo.ActivityVo;

/**
 *
 * @author Alexander
 */
public class ActivityFacade extends Facade <ActivityVo> {

    public ActivityFacade(String PUName, ActivityService service) {
        super(PUName, service);
    }
  
}
