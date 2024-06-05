package com.example.stage6.CRUD.Portlet.entity;

import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Chart.entity.Chart;
import com.example.stage6.CRUD.Dashboard.entity.Dashboard;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "portlet")
@TypeAlias("portlet")
@AllArgsConstructor
@NoArgsConstructor
public class Portlet extends BusinessEntity {


    private String row;
    private String column;


    @DBRef
    private Dashboard dashboard;

    @DBRef
    private Chart chart;



}
