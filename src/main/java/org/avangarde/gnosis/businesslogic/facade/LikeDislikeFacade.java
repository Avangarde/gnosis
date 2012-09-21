package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.LikeDislikeService;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Alexander
 */
public class LikeDislikeFacade extends Facade <LikeDislikeVo> {

    public LikeDislikeFacade(String PUName, LikeDislikeService service) {
        super(PUName, service);
    }   
}
