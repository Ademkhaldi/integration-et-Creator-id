package com.example.stage6.CRUD.Chart.entity;

import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Datasource.entity.Datasource;
import com.example.stage6.CRUD.Portlet.entity.Portlet;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "chart")
@TypeAlias("chart")
@AllArgsConstructor
@NoArgsConstructor
public class Chart extends BusinessEntity {

    private String title;
    private charttype type;
    private String x_axis;
    private String y_axis;


    @DBRef
    private Datasource datasource;


    @DBRef
    private Portlet portlet;


}
