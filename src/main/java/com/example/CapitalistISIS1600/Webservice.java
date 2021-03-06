/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CapitalistISIS1600;

import generated.World;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Utilisateur
 */
@Path("generic")
public class Webservice {
    
    Services services;
    
    public Webservice() {
    services = new Services();
    }
    
    @GET
    @Path("world")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWorld(@Context HttpServletRequest request) {
        String username = request.getHeader("X-user");
        System.out.println(username);
        World world = this.services.getWorld(username);
        this.services.saveWordlToXml(world, username);
        return Response.ok(world).build();
    }

}
