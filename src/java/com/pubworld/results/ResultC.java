/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pubworld.results;

/**
 *
 * @author Djordje
 */
public class ResultC {
    private String pcMembername;
    private String pcMemberAffiliation;
    private String conferenceName;

    public ResultC() {
    }

    public ResultC(String pcMembername, String pcMemberAffiliation, String conferenceName) {
        this.pcMembername = pcMembername;
        this.pcMemberAffiliation = pcMemberAffiliation;
        this.conferenceName = conferenceName;
    }

    /**
     * @return the pcMembername
     */
    public String getPcMembername() {
        return pcMembername;
    }

    /**
     * @param pcMembername the pcMembername to set
     */
    public void setPcMembername(String pcMembername) {
        this.pcMembername = pcMembername;
    }

    /**
     * @return the pcMemberAffiliation
     */
    public String getPcMemberAffiliation() {
        return pcMemberAffiliation;
    }

    /**
     * @param pcMemberAffiliation the pcMemberAffiliation to set
     */
    public void setPcMemberAffiliation(String pcMemberAffiliation) {
        this.pcMemberAffiliation = pcMemberAffiliation;
    }

    /**
     * @return the conferenceName
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * @param conferenceName the conferenceName to set
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    

}
