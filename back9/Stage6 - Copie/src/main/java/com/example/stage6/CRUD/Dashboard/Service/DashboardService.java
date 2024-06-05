package com.example.stage6.CRUD.Dashboard.Service;

import com.example.stage6.CRUD.Dashboard.entity.Dashboard;
import com.example.stage6.CRUD.Dashboard.repository.DashboardRepository;
import com.example.stage6.CRUD.Portlet.Service.IPortletService;
import com.example.stage6.CRUD.Portlet.entity.Portlet;
import com.example.stage6.CRUD.Portlet.repository.PortletRepository;
import com.example.stage6.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService implements IDashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private IPortletService IportletService;

    @Autowired
    private PortletRepository portletRepository;

    @Autowired
    private UserService userService;
    @Override

    public List<Dashboard> getAllDashboards() {
        return dashboardRepository.findAll();
    }



    @Override
    public Dashboard retrieveDashboard(String id) {
        return dashboardRepository.findById(id).orElse(null);
    }
    @Override

    public Dashboard createDashboard(Dashboard dashboard) {

        dashboard.setCreationDate(new Date()); // Utilise la date et l'heure actuelles lors de la création
        dashboard.setUpdate_date(dashboard.getCreationDate()); // Assure que update_date est le même que creationDate
        return dashboardRepository.save(dashboard);
    }
    @Override

    public Dashboard updateDashboard(String id, Dashboard dashboard) {
        Optional<Dashboard> existingDashboardOptional = dashboardRepository.findById(id);
        if (existingDashboardOptional.isPresent()) {
            Dashboard existingDashboard = existingDashboardOptional.get();
            existingDashboard.setTitle(dashboard.getTitle());
            existingDashboard.setPortlets(dashboard.getPortlets());

            // Mise à jour de la date de mise à jour et de l'identifiant du metteur à jour
            existingDashboard.setUpdate_date(new Date());
            existingDashboard.setUpdator_id(dashboard.getUpdator_id());

            return dashboardRepository.save(existingDashboard);
        } else {
            return null; // Gérer l'absence de l'élément à mettre à jour comme vous le souhaitez
        }
    }

    @Override

    public boolean deleteDashboard(String id) {
        if (dashboardRepository.existsById(id)) {
            dashboardRepository.deleteById(id);
            return true;
        } else {
            return false; // Gérer l'absence de l'élément à supprimer comme vous le souhaitez
        }
    }

    @Override
    public boolean deleteAllDashboards() {
        long countBeforeDelete = dashboardRepository.count();
        dashboardRepository.deleteAll();
        long countAfterDelete = dashboardRepository.count();
        return countBeforeDelete != countAfterDelete;

    }

    //Affectation
/*
    @Override

    public Dashboard assignerListePortletsADashboard(String id, List<Portlet> portlets) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById(id);

        if (optionalDashboard.isPresent()) {
            Dashboard dashboard = optionalDashboard.get();

            // Récupérer les portlets actuelles du tableau de bord
            List<Portlet> existingPortlets = dashboard.getPortlets();

            // Vérifier si la liste de portlets existantes est null
            if (existingPortlets == null) {
                existingPortlets = new ArrayList<>();
            }

            // Ajouter les nouvelles portlets à la liste existante
            existingPortlets.addAll(portlets);

            // Mettre à jour les portlets du tableau de bord avec la nouvelle liste
            dashboard.setPortlets(existingPortlets);

            // Enregistrer le tableau de bord mis à jour dans la base de données
            return dashboardRepository.save(dashboard);
        } else {
            // Gérer le cas où le tableau de bord n'est pas trouvé
            // Vous pouvez lancer une exception appropriée ou renvoyer null, selon vos besoins
            return null;
        }
    }
*/
    @Override
    public Dashboard assignerListePortletsADashboard(String id, List<Portlet> portlets) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById(id);

        if (optionalDashboard.isPresent()) {
            Dashboard dashboard = optionalDashboard.get();

            // Récupérer les portlets actuelles du tableau de bord
            List<Portlet> existingPortlets = dashboard.getPortlets();

            // Vérifier si la liste de portlets existantes est null
            if (existingPortlets == null) {
                existingPortlets = new ArrayList<>();
            }

            // Parcourir la liste des nouveaux portlets pour créer ceux avec ID null
            for (Portlet portlet : portlets) {
                // Vérifier si l'ID du portlet est null
                if (portlet.getId() == null) {
                    // Si l'ID du portlet est null, générer un nouvel ID pour le portlet
                    // Vous pouvez utiliser une logique appropriée pour générer l'ID, par exemple UUID.randomUUID().toString()
                    String newPortletId = UUID.randomUUID().toString();
                    // Mettre à jour l'ID du portlet avec le nouvel ID généré
                    portlet.setId(newPortletId);
                    // Enregistrer le portlet dans la base de données
                    portletRepository.save(portlet);
                }
                // Ajouter le portlet à la liste existante de portlets du tableau de bord
                existingPortlets.add(portlet);
            }

            // Mettre à jour les portlets du tableau de bord avec la nouvelle liste
            dashboard.setPortlets(existingPortlets);

            // Enregistrer le tableau de bord mis à jour dans la base de données
            return dashboardRepository.save(dashboard);
        } else {
            // Gérer le cas où le tableau de bord n'est pas trouvé
            // Vous pouvez lancer une exception appropriée ou renvoyer null, selon vos besoins
            return null;
        }
    }

















    @Override
    public Set<Portlet> getPortletsForDashboard(String dashboardId) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById(dashboardId);
        if (optionalDashboard.isPresent()) {
            Dashboard dashboard = optionalDashboard.get();
            return dashboard.getPortlets() != null ? new HashSet<>(dashboard.getPortlets()) : Collections.emptySet();
        } else {
            return Collections.emptySet();
        }
    }





}
