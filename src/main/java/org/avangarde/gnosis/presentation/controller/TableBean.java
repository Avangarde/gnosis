package org.avangarde.gnosis.presentation.controller;
  
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.SubjectVo;
  

@ManagedBean
@RequestScoped  
public class TableBean implements Serializable {  
    @ManagedProperty(value = "#{subjectBean}")      
    private SubjectBean subjectBean;
    @ManagedProperty(value = "#{searchBean}")      
    private searchBean searchBean;    

    public org.avangarde.gnosis.presentation.controller.searchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(org.avangarde.gnosis.presentation.controller.searchBean searchBean) {
        this.searchBean = searchBean;
    }
    
    private List<SubjectVo> subjects;

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }
    
    public TableBean(){
    
    }
        public List<SubjectVo> getSubjects() {
        if (subjects == null) {
            subjects = FacadeFactory.getInstance().getSubjectFacade().getSubjectsByName(searchBean.getQuery());
              }      
        return subjects;
    }
    }  

  