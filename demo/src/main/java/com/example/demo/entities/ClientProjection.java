package com.example.demo.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "clientDetails", types = Client.class)
public interface ClientProjection {
    String getNom();
    String getEmail();
}