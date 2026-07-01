package dev.engripaye.controlplane.controller;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final JdbcClient db;
    private final boolean bootstrapEnabled;

    public OrganizationController(JdbcClient db, boolean bootstrapEnabled) {
        this.db = db;
        this.bootstrapEnabled = bootstrapEnabled;
    }


}
