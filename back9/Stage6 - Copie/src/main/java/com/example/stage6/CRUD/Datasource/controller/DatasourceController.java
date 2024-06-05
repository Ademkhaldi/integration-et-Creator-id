package com.example.stage6.CRUD.Datasource.controller;

import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Chart.DTO.ChartDTO;
import com.example.stage6.CRUD.Datasource.DTO.DatasourceDTO;
import com.example.stage6.CRUD.Datasource.Service.IDatasourceService;
import com.example.stage6.CRUD.Datasource.entity.Datasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/datasources")
public class DatasourceController {

    @Autowired
    private IDatasourceService datasourceService;

/*Avec ces modifications, /Charts/getAllCharts renverra uniquement les informations pertinentes pour les charts, y compris les détails du Datasource associé, et /datasources/getAllDatasources renverra uniquement les informations pertinentes pour les datasources, y compris les détails du Chart associé.*/
   @GetMapping("/getAllDatasources")
   public List<DatasourceDTO> getAllDatasources() {
       List<Datasource> datasources = datasourceService.getAllDatasources();
       List<DatasourceDTO> datasourceDTOs = new ArrayList<>();
       for (Datasource datasource : datasources) {
           DatasourceDTO datasourceDTO = convertToDTO(datasource);
           datasourceDTOs.add(datasourceDTO);
       }
       return datasourceDTOs;
   }

    private DatasourceDTO convertToDTO(Datasource datasource) {
        DatasourceDTO datasourceDTO = new DatasourceDTO();
        datasourceDTO.setType(datasource.getType());
        datasourceDTO.setConnection_port(datasource.getConnection_port());

        datasourceDTO.setUrl(datasource.getUrl());
        datasourceDTO.setIndex(datasource.getIndex());
        datasourceDTO.setUser(datasource.getUser());
        datasourceDTO.setPassword(datasource.getPassword());



        if (datasource instanceof BusinessEntity) {
            BusinessEntity businessEntity = (BusinessEntity) datasource;
            datasourceDTO.setId(businessEntity.getId());
            datasourceDTO.setCreationDate(businessEntity.getCreationDate());
            datasourceDTO.setCreator_id(businessEntity.getCreator_id());
            datasourceDTO.setUpdate_date(businessEntity.getUpdate_date());
            datasourceDTO.setUpdator_id(businessEntity.getUpdator_id());


        }
        if (datasource.getChart() != null) {
            ChartDTO chartDTO = new ChartDTO();
            chartDTO.setId(datasource.getChart().getId());
            chartDTO.setTitle(datasource.getChart().getTitle());
            chartDTO.setType(datasource.getChart().getType());
            chartDTO.setX_axis(datasource.getChart().getX_axis());
            chartDTO.setY_axis(datasource.getChart().getY_axis());
            datasourceDTO.setChart(chartDTO);
        }

        return datasourceDTO;
    }
   @GetMapping("/{id}")
   public DatasourceDTO retrieveDatasource(@PathVariable("id") String id) {
       Datasource datasource = datasourceService.retrieveDatasource(id);
       return convertToDTO(datasource);
   }
    @PostMapping("/Add")
    public ResponseEntity<Datasource> createDatasource(@RequestBody Datasource datasource) {
        Datasource createdDatasource = datasourceService.createDatasource(datasource);
        return new ResponseEntity<>(createdDatasource, HttpStatus.CREATED);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Map<String, Object>> updateDashboard(@PathVariable String id, @RequestBody Datasource datasource) {
        Datasource updatedDatasource = datasourceService.updateDatasource(id, datasource);
        if (updatedDatasource != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Datasource updated successfully");
            response.put("dashboard", updatedDatasource);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Datasource not found with id: " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteDatasource(@PathVariable String id) {
        boolean deleted = datasourceService.deleteDatasource(id);
        if (deleted) {
            return new ResponseEntity<>("Datasource removed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il n'y a aucun Datasource à supprimer", HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/deleteAllDatasources")
    public ResponseEntity<String> deleteAllDatasources() {
        try {
            boolean deleted = datasourceService.deleteAllDatasources();
            if (deleted) {
                return ResponseEntity.ok("Datasource removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il n'y a aucun Datasource à supprimer");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression");
        }
    }




    //Affectation
    //{idPortlet}{idDashboard}
    @PutMapping("/affecterChartADatasource/{idDatasource}/{idChart}")
    public ResponseEntity<Map<String, String>> affecterChartADatasource(
            /*{idPortlet}*/ @PathVariable("idDatasource") String idDatasource,
            /*{idDashboard}*/@PathVariable("idChart") String idChart) {

        boolean affectationReussie =datasourceService.affecterChartADatasource(idDatasource, idChart);
        // Créez une carte pour stocker les informations de la réponse
        Map<String, String> response = new HashMap<>();
        if(affectationReussie){

            // Ajoutez les détails de l'affectation à la réponse
            response.put("message", "Affectation réussie");
            response.put("datasourceId", idDatasource);
            response.put("chartId", idChart);

            // Répondez avec un objet ResponseEntity contenant la carte de réponse
            return ResponseEntity.ok(response);
        } else {

            // Si le client ou le marché n'est pas trouvé, ajoutez un message d'erreur à la réponse
            response.put("message", "Chart ou Datasource non trouvé");

            // Répondez avec un statut NOT_FOUND et la carte de réponse
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
