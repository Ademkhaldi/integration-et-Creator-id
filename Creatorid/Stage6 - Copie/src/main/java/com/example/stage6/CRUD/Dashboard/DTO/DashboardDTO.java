package com.example.stage6.CRUD.Dashboard.DTO;


import com.example.stage6.CRUD.BusinessEntity.BusinessEntity;
import com.example.stage6.CRUD.Portlet.DTO.PortletDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
   public class DashboardDTO extends BusinessEntity {

    private String title;
        private List<PortletDTO> portlets;

        /* Modifier la méthode setPortlets pour accepter une liste d'objets PortletDTO
       / public void setPortlets(List<PortletDTO> portlets) {
       /     this.portlets = portlets;
        }*/
    }




