package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.TopicService;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Alexander
 */
public class TopicFacade extends Facade<TopicVo> {

    public TopicFacade(String PUName, TopicService service) {
        super(PUName, service);
    }

    public int getNewId() {
        try {
            return ((TopicService)service).getNewId(em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public List<TopicVo> getTopicsbySubject(Integer subjectCode) {
        try {
            em = emf.createEntityManager();
            return ((TopicService)service).getTopicsBySubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
