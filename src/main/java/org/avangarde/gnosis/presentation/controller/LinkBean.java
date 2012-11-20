package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class LinkBean implements Serializable {

    private String url;

    public LinkBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
