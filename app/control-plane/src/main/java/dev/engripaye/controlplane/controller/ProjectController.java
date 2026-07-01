package dev.engripaye.controlplane.controller;

import dev.engripaye.controlplane.dto.CreateProject;
import dev.engripaye.controlplane.dto.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final JdbcClient db;

    public ProjectController(JdbcClient db) {
        this.db = db;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Project create(
            @RequestHeader("X-Organization-Id") UUID organizationId,
            @Valid @RequestBody CreateProject request
    ){
        return db.sql("""
                INSERT INTO projects(organization_id, name, slug)
                VALUES (:organizationId, :name, :slug)
                RETURNING id, organization_id, name, slug, created_at
                """)
                .param("organizationId", organizationId)
                .param("name", request.name())
                .param("slug", request.slug())
                .query(Project.class).single();
    }

    @GetMapping
    List<Project> list(@RequestHeader("X-Organization-Id") UUID organizationId){
        return db.sql("""
                SELECT id, organization_id, name, slug, created_at
                FROM projects WHERE organization_id = :organizationId ORDER BY created_at DESC
                """)
                .param("organizationId", organizationId)
                .query(Project.class).list();
    }


}
