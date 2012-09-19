package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.ServiceFactory;

/**
 *
 * @author Alexander
 */
public class FacadeFactory {

    private String PU = "GnosisPU";

    private FacadeFactory() {
    }
    
    public StudentFacade getStudentFacade() {
        return new StudentFacade(PU, ServiceFactory.getInstance().getStudentService());
    }
    
    private static FacadeFactory instance;

    public static synchronized FacadeFactory getInstance() {
        if (instance == null) {
            instance = new FacadeFactory();
        }
        return instance;
    }
}
