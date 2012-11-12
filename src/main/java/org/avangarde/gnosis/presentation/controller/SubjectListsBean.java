/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;
import org.primefaces.model.DualListModel;

/**
 *
 * @author andres
 */
@ManagedBean
@RequestScoped
public class SubjectListsBean {
    
    private List<TutorVo> tutors;
    private List<StudentVo> students;

    public SubjectListsBean() {
        
        List<TutorVo> availables = new ArrayList<TutorVo>();
        
        TutorVo one = new TutorVo();
        one.setReputation(5);
        one.setUserName("jperez");
        one.setUrlPhoto("http://4.bp.blogspot.com/_U_BEf_yeYBA/TSFzWFghHTI/AAAAAAAAABQ/MwSwNxAZJzk/s1600/1266368823_74532441_1-Fotos-de-PROFESOR-DE-MATEMATICAS-CALCULO-FISICA.jpg");
        
        
        TutorVo two = new TutorVo();
        two.setReputation(5);
        two.setUserName("tmartinezf");
        two.setUrlPhoto("http://4.bp.blogspot.com/_U_BEf_yeYBA/TSFzWFghHTI/AAAAAAAAABQ/MwSwNxAZJzk/s1600/1266368823_74532441_1-Fotos-de-PROFESOR-DE-MATEMATICAS-CALCULO-FISICA.jpg");
        
        availables.add(one);
        availables.add(two);
        
        tutors = new ArrayList<TutorVo>(availables); 
    }
    
    public List<TutorVo> getTutors() {  
        return tutors;  
    }  
    public void setTutors(List<TutorVo> tutors) {  
        this.tutors = tutors;  
    }
}

