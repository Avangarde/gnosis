package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ProgramVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class ListsBean implements Serializable {

    private List<SelectItem> programs;
    private List<SelectItem> topics;

    public ListsBean() {
    }

    public List<SelectItem> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<SelectItem>();
            List<ProgramVo> programsList = FacadeFactory.getInstance().getProgramFacade().getList();
            if (programsList != null) {
                for (ProgramVo program : programsList) {
                    programs.add(new SelectItem(program.getCode(),
                            program.getName()));
                }
            }
        }
        return programs;
    }

    public List<SelectItem> getTopics() {
        if (topics == null) {
            topics = new ArrayList<SelectItem>();
            List<String> topicList = FacadeFactory.getInstance().getPublicationFacade().getTopics();
            if (topicList != null) {
                for (String topic : topicList) {
                    topics.add(new SelectItem(topic));
                }
            }
        }
        return topics;
    }
        
}