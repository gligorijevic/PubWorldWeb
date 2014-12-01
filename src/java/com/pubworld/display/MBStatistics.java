/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pubworld.display;

import com.pubworld.data.DBBroker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Djordje
 */
@ManagedBean
public class MBStatistics {

    private BarChartModel publicationsCountBarModel;
    private DonutChartModel donutModel2;
    private PieChartModel pieModel2;
    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        try {
            DBBroker.getInstance().connect();
            createBarModels();
            createDonutModels();
            createPieModels();
            createLineModels();
            DBBroker.getInstance().closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MBStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BarChartModel getBarModel() {
        return publicationsCountBarModel;
    }

    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new HorizontalBarChartModel();
        try {
//            DBBroker.getInstance().connect();
            ResultSet rs = DBBroker.getInstance().getCountOfPublicationsOfAllUniversities();

            ChartSeries girls = new ChartSeries();
            girls.setLabel("Universities");
            int i = 0;
            if (rs != null) {
                while (rs.next()) {
                    if (i < 30) {
                        String universityName = rs.getString(1);
                        int universityPublicationCount = rs.getInt(2);
                        if (!universityName.equals("null")) {
                            girls.set(universityName, universityPublicationCount);
//                            System.out.println(universityName + " -- " + universityPublicationCount);
                            i++;
                        }
                    }
                }
            } else {
                girls.set("null", 0);
            }
//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Girls");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 110);
//        girls.set("2007", 135);
//        girls.set("2008", 120);

            model.addSeries(girls);
//            DBBroker.getInstance().closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MBStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        publicationsCountBarModel = initBarModel();

        publicationsCountBarModel.setTitle("Bar Chart");
        publicationsCountBarModel.setLegendPosition("ne");

        Axis xAxis = publicationsCountBarModel.getAxis(AxisType.X);
        xAxis.setLabel("# Publications");

        Axis yAxis = publicationsCountBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Universities");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    private void createDonutModels() {
        try {
            //        donutModel1 = initDonutModel();
//        donutModel1.setTitle("Donut Chart");
//        donutModel1.setLegendPosition("w");

            donutModel2 = initDonutModel();
        } catch (SQLException ex) {
            Logger.getLogger(MBStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        donutModel2.setTitle("Rate of publications on universities vs univeristies with PC members");
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(5);
        donutModel2.setShowDataLabels(true);
        donutModel2.setDataFormat("value");
        donutModel2.setShadow(false);
    }

    private DonutChartModel initDonutModel() throws SQLException {
        DonutChartModel model = new DonutChartModel();

//        DBBroker.getInstance().connect();
        ResultSet rs = DBBroker.getInstance().getCountOfPublicationsOfSelectedUniversities();

        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
//        girls.setLabel("Universities");
        if (rs != null) {
            while (rs.next()) {
                String universityName = rs.getString(1);
                int universityPublicationCount = rs.getInt(2);
                circle1.put(universityName, universityPublicationCount);
            }
        } else {
            circle1.put("null", 0);
        }
        model.addCircle(circle1);

        ResultSet rs2 = DBBroker.getInstance().getCountOfPublicationsOfSelectedUniversitiesProgramCommitees();
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
//        girls.setLabel("Universities");
        if (rs2 != null) {
            while (rs2.next()) {
                String universityName = rs2.getString(1);
                int universityPublicationCount = rs2.getInt(2);
                circle2.put(universityName, universityPublicationCount);
            }
        } else {
            circle2.put("null", 0);
        }
        model.addCircle(circle2);
//        DBBroker.getInstance().closeConnection();
//        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
//        circle2.put("Brand 1", 540);
//        circle2.put("Brand 2", 125);
//        circle2.put("Brand 3", 702);
//        circle2.put("Brand 4", 421);
//        model.addCircle(circle2);
        return model;
    }

    private void createPieModels() {
        try {
            createPieModel2();
        } catch (SQLException ex) {
            Logger.getLogger(MBStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPieModel2() throws SQLException {
        pieModel2 = new PieChartModel();

//        DBBroker.getInstance().connect();
        ResultSet rs = DBBroker.getInstance().getCountOfPublicationsOfConference();

        if (rs != null) {
            while (rs.next()) {
                String conferenceName = rs.getString(1);
                int conferencePublicationCount = rs.getInt(2);
                pieModel2.set(conferenceName, conferencePublicationCount);
            }
        } else {
            pieModel2.set("null", 0);
        }
//        DBBroker.getInstance().closeConnection();

//        pieModel2.set("Brand 1", 540);
//        pieModel2.set("Brand 2", 325);
//        pieModel2.set("Brand 3", 702);
//        pieModel2.set("Brand 4", 421);
        pieModel2.setTitle("Percentage of papers in each conference");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

    private void createLineModels() {
        try {
//        lineModel1 = initLinearModel();
//        lineModel1.setTitle("Linear Chart");
//        lineModel1.setLegendPosition("e");
//        Axis yAxis = lineModel1.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10);

            lineModel2 = initCategoryModel();
        } catch (SQLException ex) {
            Logger.getLogger(MBStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        lineModel2.setTitle("Timeseries chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("ICDE conference years"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Counts");
        yAxis.setMin(0);
        yAxis.setMax(1000);
    }


    private LineChartModel initCategoryModel() throws SQLException {
        LineChartModel model = new LineChartModel();

//        DBBroker.getInstance().connect();
        ResultSet rs = DBBroker.getInstance().getCountOfPublicationsOfConference();
        ChartSeries boys = new ChartSeries();
        boys.setLabel("# publications per conference");
        if (rs != null) {
            while (rs.next()) {
                String conferenceYear = rs.getString(1);
                int publicationCount = rs.getInt(2);
                boys.set(conferenceYear, publicationCount);
            }
        } else {
            boys.set("null", 0);
        }

//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
        ResultSet rs2 = DBBroker.getInstance().getCountParticipantsPerYear();
        ChartSeries girls = new ChartSeries();
        girls.setLabel("# participants per conference");
        if (rs2 != null) {
            while (rs2.next()) {
                String conferenceYear = rs2.getString(1);
                int publicationCount = Integer.parseInt(rs2.getString(2));
                girls.set(conferenceYear, publicationCount);
            }
        } else {
            girls.set("null", 0);
        }
//        DBBroker.getInstance().closeConnection();

//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Girls");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 110);
//        girls.set("2007", 90);
//        girls.set("2008", 120);
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

}
