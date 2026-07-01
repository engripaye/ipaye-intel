package dev.engripaye.controlplane.controller;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/scans")
public class ScanController {

    private final JdbcClient db;


    public ScanController(JdbcClient db) {
        this.db = db;
    }
}
