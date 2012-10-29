package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.StudentService;
import org.avangarde.gnosis.businesslogic.service.TutorService;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class StudentFacade extends Facade <StudentVo> {

    public StudentFacade(String PUName, StudentService service) {
        super(PUName, service);
    }

    public StudentVo login(StudentVo vo) {
        try {
            em = emf.createEntityManager();
            return ((StudentService)service).login(vo, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }    
    
    public boolean isTutor(TutorVo vo){
        //TODO verificación de si el sujeto ya es tutor
        boolean flag = true;
        
        try {
            em = emf.createEntityManager();
            vo = ((TutorService)service).find(vo, em);
        }finally{
            if (em!=null){
                em.clear();
                em.close();
            }
        }
        
        
        return flag;
        
    }
}
