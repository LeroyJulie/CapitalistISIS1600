/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import static javafx.scene.input.KeyCode.X;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Content;

/**
 *
 * @author Utilisateur
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext)
            throws IOException {
        MultivaluedMap<String, Object> headers
                = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE,PUT, OPTIONS");
 headers.add("Access-Control-Allow-Headers", "X-Requested-With,Content-Type, X-Codingpedia, authorization,X-User");

 }
}

