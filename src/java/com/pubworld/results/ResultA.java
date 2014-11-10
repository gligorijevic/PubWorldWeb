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
public class ResultA implements Result{

    private String paperName;
    private String conferenceYear;
    private String conferenceName;
    private String conferenceLocation;

    public ResultA() {
    }

    public ResultA(String paperName, String conferenceYear, String conferenceName, String conferenceLocation) {
        this.paperName = paperName;
        this.conferenceYear = conferenceYear;
        this.conferenceName = conferenceName;
        this.conferenceLocation = conferenceLocation;
    }

    /**
     * @return the paperName
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * @param paperName the paperName to set
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    /**
     * @return the conferenceYear
     */
    public String getConferenceYear() {
        return conferenceYear;
    }

    /**
     * @param conferenceYear the conferenceYear to set
     */
    public void setConferenceYear(String conferenceYear) {
        this.conferenceYear = conferenceYear;
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

    /**
     * @return the conferenceLocation
     */
    public String getConferenceLocation() {
        return conferenceLocation;
    }

    /**
     * @param conferenceLocation the conferenceLocation to set
     */
    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }

}
