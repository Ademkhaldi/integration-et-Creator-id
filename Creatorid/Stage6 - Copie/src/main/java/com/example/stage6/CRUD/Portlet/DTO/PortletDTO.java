package com.example.stage6.CRUD.Portlet.DTO;


import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Chart.DTO.ChartDTO;
import com.example.stage6.CRUD.Dashboard.DTO.DashboardDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PortletDTO extends BusinessEntity{

    private String row;
    private String column;


    private DashboardDTO dashboard;

    private ChartDTO chart;


}
