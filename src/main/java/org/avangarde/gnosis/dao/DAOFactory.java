package org.avangarde.gnosis.dao;

/**
 *
 * @author Alexander
 */
public class DAOFactory {

    private DAOFactory() {
    }

    public StudentDAO getStudentDAO() {
        return StudentDAO.getInstance();
    }

    
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
}
