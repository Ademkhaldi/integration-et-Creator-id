package com.example.stage6.CRUD.Chart.DTO;

import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Chart.entity.charttype;
import com.example.stage6.CRUD.Datasource.DTO.DatasourceDTO;
import com.example.stage6.CRUD.Portlet.DTO.PortletDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChartDTO extends BusinessEntity {
    private String title;
    private charttype type;
    private String x_axis;
    private String y_axis;
    private DatasourceDTO datasource;
    private PortletDTO portlet;
}
