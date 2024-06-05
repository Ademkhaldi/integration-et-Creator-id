package com.example.stage6.CRUD.Chart.Service;

import com.example.stage6.CRUD.Chart.entity.Chart;

import java.util.List;

public interface IChartService {


    List<Chart> getAllCharts();

    Chart retrieveChart(String id);

    Chart createChart(Chart chart);

    Chart updateChart(String id, Chart chart);

    boolean deleteChart(String id);

    boolean deleteAllCharts();


    boolean affecterDatasourceAChart(String idChart, String idDatasource);

    boolean affecterPortletAChart(String idChart,String idPortlet);



    }
