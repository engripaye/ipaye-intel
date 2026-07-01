package dev.engripaye.controlplane.controller;

import dev.engripaye.controlplane.dto.CreateOrganization;
import dev.engripaye.controlplane.dto.Organization;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final JdbcClient db;
    private final boolean bootstrapEnabled;

    public OrganizationController(JdbcClient db, boolean bootstrapEnabled) {
        this.db = db;
        this.bootstrapEnabled = bootstrapEnabled;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Organization create(@Valid @RequestBody CreateOrganization request){
        if (!bootstrapEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return db.sql("""
                INSERT INTO organizations(name, slug) VALUES (:name, :slug)
                RETURNING id, name, slug, created_at
                """)
                .param("name", request.name())
                .param("slug", request.slug())
                .query(Organization.class).single();
    }
}
