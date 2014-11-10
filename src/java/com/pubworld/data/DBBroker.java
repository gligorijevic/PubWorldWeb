/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pubworld.data;

import com.pubworld.results.ResultA;
import com.pubworld.results.ResultB;
import com.pubworld.results.ResultC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Djordje
 */
public class DBBroker {

    private static DBBroker instance;
    private static String db_userid;
    private static String db_password;
    private static String db_connect_string = "jdbc:sqlserver://DJORDJE;databaseName=pubworld";

    private Connection conn;

    private DBBroker() {
        db_userid = "djordje";
        db_password = "djordje";
    }

    public static synchronized DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void connect() {
        try {
//            String url = "jdbc:sqlserver://DJORDJE;databaseName=pubworld;integratedSecurity=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
                System.out.println("Connection to the database has been made.");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * If Author is selected, the user needs to supply the full name or a part
     * of the name of an author (use LIKE in your query). You need to return all
     * the papers published by the authors having the name supplied by the user.
     * Information to be displayed: paper name, year, conference name, and
     * conference location.
     *
     * @param authorName
     * @return
     */
    public List<ResultA> getAllPaperFromAuthor(String authorName) {
        List<ResultA> results = new ArrayList<>();
        try {
//            "SELECT p.paperName, c.conferenceYear, c.conferenceName, c.conferenceLocation \n" +
//" FROM [pubworld].[dbo].Conference AS c JOIN [pubworld].[dbo].Paper as p \n" +
//" ON c.conferenceId=p.conferenceId JOIN [pubworld].[dbo].Author AS a ON p.paperId=a.paperId \n" +
//" JOIN [pubworld].[dbo].Participant as pt ON a.participantId=pt.participantId \n" +
//" WHERE pt.participantName LIKE 'Alfons';";

            String SQL = "SELECT p.paperName, c.conferenceYear, c.conferenceName, c.conferenceLocation"
                    + " FROM [pubworld].[dbo].Conference AS c JOIN [pubworld].[dbo].Paper as p "
                    + " ON c.conferenceId=p.conferenceId JOIN [pubworld].[dbo].Author AS a ON p.paperId=a.paperId "
                    + " JOIN [pubworld].[dbo].Participant as pt ON a.participantId=pt.participantId "
                    + " WHERE pt.participantName LIKE '%" + authorName + "%'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultA result = new ResultA(rs.getString("paperName"), rs.getString("conferenceYear"),
                        rs.getString("conferenceName"), rs.getString("conferenceLocation"));
                results.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;
    }

    /**
     * If Affiliation is selected, return all papers published by the authors
     * from the Affiliation supplied by the user. Information to be displayed:
     * paper name, paper's authors, year, conference name, and conference
     * location.
     *
     * @param affiliationName
     * @return
     */
    public List<ResultB> getAllPapersFromAffiliation(String affiliationName) {
        List<ResultB> results = new ArrayList<>();
        try {
            String SQL = "SELECT p.paperName, pt.participantName, c.conferenceYear, c.conferenceName, c.conferenceLocation \n"
                    + "FROM [pubworld].[dbo].Conference AS c JOIN [pubworld].[dbo].Paper as p\n"
                    + "ON c.conferenceId=p.conferenceId JOIN [pubworld].[dbo].Author AS a ON p.paperId=a.paperId \n"
                    + "JOIN [pubworld].[dbo].Participant as pt ON a.participantId=pt.participantId \n"
                    + "WHERE pt.participantAffiliation LIKE '%" + affiliationName
                    + "%'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultB result = new ResultB(rs.getString("paperName"),
                        rs.getString("participantName"), rs.getString("conferenceYear"),
                        rs.getString("conferenceName"), rs.getString("conferenceLocation"));
                results.add(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;
    }

    /**
     * If PC member is selected, return all PC members having the name supplied
     * by the user. Information to be displayed: name, affiliation, and
     * conference name.
     *
     * @param pcName
     * @return
     */
    public List<ResultC> getAllPCmemberWithName(String pcName) {
        List<ResultC> results = new ArrayList<>();
        try {
            String SQL = "SELECT pt.participantName, pt.participantAffiliation, c.conferenceName\n"
                    + "FROM pubworld.dbo.Conference AS c JOIN pubworld.dbo.ProgramComitee as pc ON c.conferenceId=pc.conferenceId JOIN \n"
                    + "pubworld.dbo.Participant AS pt ON pc.participantId=pt.participantId \n"
                    + "WHERE pt.participantName LIKE '%" + pcName + "%'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultC result = new ResultC(rs.getString("participantName"),
                        rs.getString("participantAffiliation"), rs.getString("conferenceName"));
                results.add(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;
    }

    /**
     * Conference is implemented as a chekbox plus a dropdown list. If the
     * checkbox is checked then the user must supply a conference. Else, all
     * conferences in the database are considered for the user query. The
     * Conference field is used in conjunction with Author, PC member,and
     * Affiliation. For example, if Conference is checked and SIGMOD 2008 is
     * selected then a query for Author returns the papers of the author in
     * SIGMOD 2008 alone. If Conference is not checked, then a query for Author
     * returns the papers of the author in all three (3) conferences in your
     * database. You proceed similarly for PC member and Affiliation.
     */
    /**
     * If Author is selected, the user needs to supply the full name or a part
     * of the name of an author (use LIKE in your query). You need to return all
     * the papers published by the authors having the name supplied by the user.
     * Information to be displayed: paper name, year, conference name, and
     * conference location.
     *
     * @param authorName
     * @param selectedConferences
     * @return
     */
    public List<ResultA> getAllPaperFromAuthor(String authorName, String[] selectedConferences) {
        List<ResultA> results = new ArrayList<>();
        try {
            String SQL = "SELECT p.paperName, c.conferenceYear, c.conferenceName, c.conferenceLocation \n"
                    + " FROM [pubworld].[dbo].Conference AS c JOIN [pubworld].[dbo].Paper as p \n"
                    + " ON c.conferenceId=p.conferenceId JOIN [pubworld].[dbo].Author AS a ON p.paperId=a.paperId \n"
                    + " JOIN [pubworld].[dbo].Participant as pt ON a.participantId=pt.participantId \n"
                    + " WHERE pt.participantName LIKE '%" + authorName + "%'";

            SQL = SQL + " AND (";
            for (int i = 0; i < selectedConferences.length - 1; i++) {
                SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[i] + "%' OR ";
            }
            SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[selectedConferences.length - 1] + "%' )";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultA result = new ResultA(rs.getString("paperName"), rs.getString("conferenceYear"),
                        rs.getString("conferenceName"), rs.getString("conferenceLocation"));
                results.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;

    }

    /**
     * If Affiliation is selected, return all papers published by the authors
     * from the Affiliation supplied by the user. Information to be displayed:
     * paper name, paper's authors, year, conference name, and conference
     * location.
     *
     * @param affiliationName
     * @param selectedConferences
     * @return
     */
    public List<ResultB> getAllPapersFromAffiliation(String affiliationName, String[] selectedConferences) {
        List<ResultB> results = new ArrayList<>();
        try {
            String SQL = "SELECT p.paperName, pt.participantName, c.conferenceYear, c.conferenceName, c.conferenceLocation \n"
                    + "FROM [pubworld].[dbo].Conference AS c JOIN [pubworld].[dbo].Paper as p\n"
                    + "ON c.conferenceId=p.conferenceId JOIN [pubworld].[dbo].Author AS a ON p.paperId=a.paperId \n"
                    + "JOIN [pubworld].[dbo].Participant as pt ON a.participantId=pt.participantId \n"
                    + "WHERE pt.participantAffiliation LIKE '%" + affiliationName
                    + "%'"; //AND c.conferenceName LIKE '%" + conferenceName + "%'";

            SQL = SQL + " AND (";
            for (int i = 0; i < selectedConferences.length - 1; i++) {
                SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[i] + "%' OR ";
            }
            SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[selectedConferences.length - 1] + "%' )";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultB result = new ResultB(rs.getString("paperName"),
                        rs.getString("participantName"), rs.getString("conferenceYear"),
                        rs.getString("conferenceName"), rs.getString("conferenceLocation"));
                results.add(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;
    }

    /**
     * If PC member is selected, return all PC members having the name supplied
     * by the user. Information to be displayed: name, affiliation, and
     * conference name.
     *
     * @param pcName
     * @param selectedConferences
     * @return
     */
    public List<ResultC> getAllPCmemberWithName(String pcName, String[] selectedConferences) {
        List<ResultC> results = new ArrayList<>();
        try {
            String SQL = "SELECT pt.participantName, pt.participantAffiliation, c.conferenceName\n"
                    + "FROM pubworld.dbo.Conference AS c JOIN pubworld.dbo.ProgramComitee as pc ON c.conferenceId=pc.conferenceId JOIN \n"
                    + "pubworld.dbo.Participant AS pt ON pc.participantId=pt.participantId "
                    + "WHERE pt.participantName LIKE '%" + pcName + "%'";
            // " AND c.conferenceName LIKE '%" + conferenceName + "%'";

            SQL = SQL + " AND (";
            for (int i = 0; i < selectedConferences.length - 1; i++) {
                SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[i] + "%' OR ";
            }
            SQL = SQL + " c.conferenceName LIKE '%" + selectedConferences[selectedConferences.length - 1] + "%' )";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ResultC result = new ResultC(rs.getString("participantName"),
                        rs.getString("participantAffiliation"), rs.getString("conferenceName"));
                results.add(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Database returned " + results.size() + " results");
        return results;
    }
}
