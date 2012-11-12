package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.CommentService;
import org.avangarde.gnosis.businesslogic.service.PublicationService;
import org.avangarde.gnosis.vo.CommentVo;

/**
 *
 * @author Alexander
 */
public class CommentFacade extends Facade <CommentVo> {

    public CommentFacade(String PUName, CommentService service) {
        super(PUName, service);
    }  

    public List<CommentVo> getCommentsbyTopic(Integer topicId) {
        try {
            em = emf.createEntityManager();
            return ((CommentService)service).getCommentsByTopic(em, topicId);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
    
    public List<CommentVo> getCommentsbyPublication(int pubId) {
        try {
            em = emf.createEntityManager();
            return ((CommentService)service).getCommentsbyPublication(pubId, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
