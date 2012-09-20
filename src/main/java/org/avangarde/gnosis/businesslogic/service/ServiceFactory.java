package org.avangarde.gnosis.businesslogic.service;

/**
 *
 * @author Alexander
 */
public class ServiceFactory {

    private ServiceFactory() {
    }

    public StudentService getStudentService() {
        return StudentService.getInstance();
    }
    private static ServiceFactory instance;

    public static synchronized ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}
