package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Program;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class StudentService implements IService<StudentVo> {

    private static StudentService instance;

    private StudentService() {
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    @Override
    public void create(StudentVo vo, EntityManager em) {
        Student entity = new Student();
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setUserName(vo.getUserName());
        entity.setEmail(vo.getEmail());
        entity.setPassword(vo.getPassword());
        entity.setUrlPhoto("./resources/images/childish_User.png");
        entity.setActive(false);

        Program program = DAOFactory.getInstance().getProgramDAO().find(vo.getProgramId(), em);
        program.getStudentList().add(entity);
        entity.setProgram(program);

        DAOFactory.getInstance().getStudentDAO().persist(entity, em);
        StudentVo findByUserName = findByUserName(vo.getUserName(), em);
        vo.setId(findByUserName.getId());
        sendEmailConfirmation(vo);
    }

    @Override
    public StudentVo find(Object id, EntityManager em) {
        Student student = DAOFactory.getInstance().getStudentDAO().find(id, em);
        if (student != null) {
            return student.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(StudentVo vo, EntityManager em) {
        Student entity = DAOFactory.getInstance().getStudentDAO().find(vo.getId(), em);
        entity.setUrlPhoto(vo.getUrlPhoto());
        entity.setAboutMe(vo.getAboutMe());
        entity.setActive(vo.isActive());
        DAOFactory.getInstance().getStudentDAO().update(entity, em);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<StudentVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StudentVo login(StudentVo vo, EntityManager em) {
        Student entity = new Student();
        entity.setUserName(vo.getUserName());
        entity.setPassword(vo.getPassword());

        Student alumno = DAOFactory.getInstance().getStudentDAO().login(entity, em);
        return alumno != null ? alumno.toVo() : null;

    }

    public boolean isTutor(TutorVo vo, EntityManager em) {
        Tutor entity = new Tutor();
        entity.setUserName(vo.getUserName());

        Tutor tutor = DAOFactory.getInstance().getTutorDAO().findByUsername(entity, em);
        if (tutor != null) {
            vo = tutor.toVo();
            return true;
        } else {
            return false;
        }


    }

    public List<StudentVo> getStudents(String query, EntityManager em) {
        List<StudentVo> students = new ArrayList<StudentVo>();
        List<Student> entities = DAOFactory.getInstance().getStudentDAO().getStudents(query, em);
        for (Student student : entities) {
            students.add(student.toVo());
        }
        return students;
    }

    public StudentVo findByUserName(String userName, EntityManager em) {
        Student student = DAOFactory.getInstance().getStudentDAO().findByUserName(userName, em);
        if (student.getUserName() != null) {
            return student.toVo();
        } else {
            return null;
        }
    }

    private void sendEmailConfirmation(StudentVo vo) {
        try {
            // Conection Properties
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", vo.getEmail());
            props.setProperty("mail.smtp.auth", "true");

            // Prepare session
            Session session = Session.getDefaultInstance(props);

            // Build Message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gnosisun@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(vo.getEmail()));
            message.setSubject("GnosisUN - Confirmación de Registro");
            message.setText(
                    "<p>Hola "+ vo.getFirstName() + " " + vo.getLastName() +",</p>\n"
                    + "        <p>Tu registro ha sido exitoso. Antes de iniciar sesión debes confirmar"
                    + "            tu dirección de correo electrónico, para ello dirígete por favor a:</p>\n"
                    + "        <p>\n"
                    + "            <a href=" + vo.getContextPath() + "confirm.xhtml?id=" + new Integer(vo.getId()).toString() + ">"
                    + "                " + vo.getContextPath() + "confirm.xhtml?id=" + new Integer(vo.getId()).toString() + "\n"
                    + "            </a>\n"
                    + "        </p>",
                    "ISO-8859-1",
                    "html");

            // Send Message
            Transport t = session.getTransport("smtp");
            t.connect("gnosisun@gmail.com", "4123gnosis");
            t.sendMessage(message, message.getAllRecipients());

            // Close
            t.close();
        } catch (MessagingException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
