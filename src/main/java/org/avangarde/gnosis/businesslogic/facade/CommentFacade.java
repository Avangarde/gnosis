package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.CommentService;
import org.avangarde.gnosis.vo.CommentVo;

/**
 *
 * @author Alexander
 */
public class CommentFacade extends Facade <CommentVo> {

    public CommentFacade(String PUName, CommentService service) {
        super(PUName, service);
    }  
}
