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
public class ResultB {

    private String paperName;
    private String papersAuthors;
    private String year;
    private String conferenceName;
    private String conferenceLocation;

    public ResultB() {
    }

    public ResultB(String paperName, String papersAuthors, String year, String conferenceName, String conferenceLocation) {
        this.paperName = paperName;
        this.papersAuthors = papersAuthors;
        this.year = year;
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
     * @return the papersAuthors
     */
    public String getPapersAuthors() {
        return papersAuthors;
    }

    /**
     * @param papersAuthors the papersAuthors to set
     */
    public void setPapersAuthors(String papersAuthors) {
        this.papersAuthors = papersAuthors;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
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
