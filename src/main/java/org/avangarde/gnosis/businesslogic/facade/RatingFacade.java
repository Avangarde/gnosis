/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.RatingService;
import org.avangarde.gnosis.vo.RatingVo;
/**
 *
 * @author Alexander
 */
public class RatingFacade extends Facade <RatingVo> {
    
    public RatingFacade(String PUName, RatingService service) {
        super(PUName, service);
    }  
}
