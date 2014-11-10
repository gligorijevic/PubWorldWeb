/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pubworld.display;

import com.pubworld.data.DBBroker;
import com.pubworld.results.Result;
import com.pubworld.results.ResultA;
import com.pubworld.results.ResultB;
import com.pubworld.results.ResultC;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Djordje
 */
@ManagedBean
public class MBPubWorld {

    private List<ResultA> resultsA;
    private List<ResultB> resultsB;
    private List<ResultC> resultsC;

    private String conference = "";
    private List<String> conferences;
    private String[] selectedConferences;
    private String option = "";

    private String author = "";
    private String affiliation = "";
    private String pcMember = "";

    private List<String> data;

    private boolean table1rendered = false;
    private boolean table2rendered = false;
    private boolean table3rendered = false;

    @PostConstruct
    public void init() {
        setConferences(new ArrayList<>());
        getConferences().add("ICDE2014");
        getConferences().add("ICDE2013");
        getConferences().add("ICDE2006");
    }

    public void renderTable() {
//        System.out.println("OPALIO");
//        System.out.println(conference);
//        System.out.println("#######");
//        System.out.println(author);
//        System.out.println(affiliation);
//        System.out.println(pcMember);
        System.out.println(getOption());
        if (getOption().equals("Author")) {
            setTable1rendered(true);
        } else if (getOption().equals("Affiliation")) {
            setTable2rendered(true);
        } else if (getOption().equals("Program Commitee")) {
            setTable3rendered(true);
        }
    }

    public void executeQuery(ActionEvent event) {
        System.out.println(getOption());
        DBBroker.getInstance().connect();
        if (getSelectedConferences().length == 0) {
            if (getOption().equals("Author")) {
                setResultsA(DBBroker.getInstance().getAllPaperFromAuthor(getAuthor()));
                setTable1rendered(true);
            } else if (getOption().equals("Affiliation")) {
                setResultsB(DBBroker.getInstance().getAllPapersFromAffiliation(getAffiliation()));
                setTable2rendered(true);
            } else if (getOption().equals("Program Commitee")) {
                setResultsC(DBBroker.getInstance().getAllPCmemberWithName(getPcMember()));
                setTable3rendered(true);
            }
        } else {
            if (getOption().equals("Author")) {
                setResultsA(DBBroker.getInstance().getAllPaperFromAuthor(getAuthor(), getSelectedConferences()));
                setTable1rendered(true);
            } else if (getOption().equals("Affiliation")) {
                setResultsB(DBBroker.getInstance().getAllPapersFromAffiliation(getAffiliation(), getSelectedConferences()));
                setTable2rendered(true);
            } else if (getOption().equals("Program Commitee")) {
                setResultsC(DBBroker.getInstance().getAllPCmemberWithName(getPcMember(), getSelectedConferences()));
                setTable3rendered(true);
            }
        }
        try {
            DBBroker.getInstance().closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MBPubWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void executeQueryAuto(String query) {
        System.out.println(getOption());
        DBBroker.getInstance().connect();
        if (getSelectedConferences().length == 0) {
            if (getOption().equals("Author")) {
                this.author = query;
                setResultsA(DBBroker.getInstance().getAllPaperFromAuthor(getAuthor()));
                setTable1rendered(true);
            } else if (getOption().equals("Affiliation")) {
                this.affiliation = query;
                setResultsB(DBBroker.getInstance().getAllPapersFromAffiliation(getAffiliation()));
                setTable2rendered(true);
            } else if (getOption().equals("Program Commitee")) {
                this.pcMember = query;
                setResultsC(DBBroker.getInstance().getAllPCmemberWithName(getPcMember()));
                setTable3rendered(true);
            }
        } else {
            if (getOption().equals("Author")) {
                this.author = query;
                setResultsA(DBBroker.getInstance().getAllPaperFromAuthor(getAuthor(), getSelectedConferences()));
                setTable1rendered(true);
            } else if (getOption().equals("Affiliation")) {
                this.affiliation = query;
                setResultsB(DBBroker.getInstance().getAllPapersFromAffiliation(getAffiliation(), getSelectedConferences()));
                setTable2rendered(true);
            } else if (getOption().equals("Program Commitee")) {
                this.pcMember = query;
                setResultsC(DBBroker.getInstance().getAllPCmemberWithName(getPcMember(), getSelectedConferences()));
                setTable3rendered(true);
            }
        }
        try {
            DBBroker.getInstance().closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MBPubWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the conferences
     */
    public List<String> getConferences() {
        return conferences;
    }

    /**
     * @param conferences the conferences to set
     */
    public void setConferences(List<String> conferences) {
        this.conferences = conferences;
    }

    /**
     * @return the conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * @param conference the conference to set
     */
    public void setConference(String conference) {
        this.conference = conference;
    }

    /**
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation the affiliation to set
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return the pcMember
     */
    public String getPcMember() {
        return pcMember;
    }

    /**
     * @param pcMember the pcMember to set
     */
    public void setPcMember(String pcMember) {
        this.pcMember = pcMember;
    }

    /**
     * @return the data
     */
    public List<String> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<String> data) {
        this.data = data;
    }

    /**
     * @return the table1rendered
     */
    public boolean isTable1rendered() {
        return table1rendered;
    }

    /**
     * @param table1rendered the table1rendered to set
     */
    public void setTable1rendered(boolean table1rendered) {
        this.table1rendered = table1rendered;
    }

    /**
     * @return the table2rendered
     */
    public boolean isTable2rendered() {
        return table2rendered;
    }

    /**
     * @param table2rendered the table2rendered to set
     */
    public void setTable2rendered(boolean table2rendered) {
        this.table2rendered = table2rendered;
    }

    /**
     * @return the table3rendered
     */
    public boolean isTable3rendered() {
        return table3rendered;
    }

    /**
     * @param table3rendered the table3rendered to set
     */
    public void setTable3rendered(boolean table3rendered) {
        this.table3rendered = table3rendered;
    }

    /**
     * @return the selectedConferences
     */
    public String[] getSelectedConferences() {
        return selectedConferences;
    }

    /**
     * @param selectedConferences the selectedConferences to set
     */
    public void setSelectedConferences(String[] selectedConferences) {
        this.selectedConferences = selectedConferences;
    }

    /**
     * @return the resultsA
     */
    public List<ResultA> getResultsA() {
        return resultsA;
    }

    /**
     * @param resultsA the resultsA to set
     */
    public void setResultsA(List<ResultA> resultsA) {
        this.resultsA = resultsA;
    }

    /**
     * @return the resultsB
     */
    public List<ResultB> getResultsB() {
        return resultsB;
    }

    /**
     * @param resultsB the resultsB to set
     */
    public void setResultsB(List<ResultB> resultsB) {
        this.resultsB = resultsB;
    }

    /**
     * @return the resultsC
     */
    public List<ResultC> getResultsC() {
        return resultsC;
    }

    /**
     * @param resultsC the resultsC to set
     */
    public void setResultsC(List<ResultC> resultsC) {
        this.resultsC = resultsC;
    }

}
