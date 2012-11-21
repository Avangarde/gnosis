package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import org.avangarde.gnosis.businesslogic.service.StudentService;
import org.avangarde.gnosis.businesslogic.service.TutorService;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class StudentFacade extends Facade<StudentVo> {

    public StudentFacade(String PUName, StudentService service) {
        super(PUName, service);
    }

    public StudentVo login(StudentVo vo) {
        try {
            em = emf.createEntityManager();
            return ((StudentService) service).login(vo, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public boolean isTutor(TutorVo vo) {
        //TODO verificación de si el sujeto ya es tutor


        try {
            em = emf.createEntityManager();

            //metodo para obtener el vo de un tutor
            //condicional

            return ((StudentService) service).isTutor(vo, em);



        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }




    }
    
    public List<StudentVo> getStudents(String query) {
        try {
            return ((StudentService) service).getStudents(query, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public StudentVo findByUserName(String userName) {
        try {
            return ((StudentService) service).findByUserName(userName, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
