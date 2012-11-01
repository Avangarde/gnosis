package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
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

    public List<String> getTopicsBySubject(Integer subjectCode) {
        try {
            em = emf.createEntityManager();
            return ((PublicationService)service).getTopicsBySubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public List<PublicationVo> getPublicationsByTopic(String topic) {
        try {
            em = emf.createEntityManager();
            return ((PublicationService)service).getPublicationsByTopic(topic, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
