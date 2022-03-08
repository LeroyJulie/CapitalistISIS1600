package com.example.demo;

import generated.PallierType;
import generated.ProductType;
import generated.World;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Utilisateur
 */
public class Services {

    World world = new World();
    String path = "d:/ISIS_-Capitalist/src/main/resources/";

    public World readWorldFromXml(String username) {
        JAXBContext jaxbContext;
        InputStream input;
        try {
            try {
                System.out.println("in read" + path + username);
                input = new FileInputStream(path + username + "world.xml");

            } catch (Exception ex) {
                input = getClass().getClassLoader().getResourceAsStream("world.xml");
            }
            jaxbContext = JAXBContext.newInstance(World.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            world = (World) jaxbUnmarshaller.unmarshal(input);
        } catch (Exception ex) {
            System.out.println("Erreur lecture du fichier:" + ex.getMessage());
            ex.printStackTrace();
        }
        return world;
    }

    public void saveWordlToXml(World world, String username) {
        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(World.class);
            Marshaller march = jaxbContext.createMarshaller();
            OutputStream output = new FileOutputStream(path + username + "world.xml");
            march.marshal(world, output);

        } catch (Exception ex) {
            System.out.println("Erreur écriture du fichier:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public World getWorld(String username) {
        System.out.println("getWorld()" + username);
        return readWorldFromXml(username);

    }


    public Boolean updateProduct(String username, ProductType newproduct) {
       
        World world = getWorld(username);
        System.out.println(world);        
        ProductType product = findProductById(world, newproduct.getId());        
        if (product == null) {
            return false;
        }

        int qtchange = newproduct.getQuantite() - product.getQuantite();
        if (qtchange > 0) {            
            double prix = (product.getCout() * (1 - Math.pow(product.getCroissance(), newproduct.getQuantite()))) / (1 - product.getCroissance());
            System.out.println("voici le prix"+prix);
            world.setMoney(world.getMoney() - prix);
            product.setQuantite(product.getQuantite() + newproduct.getQuantite());
        } else {
            product.setTimeleft(product.getVitesse());
            product.setQuantite(newproduct.getQuantite());           
        }       
        this.saveWordlToXml(world, username);
        return true;
    }
    
     private ProductType findProductById(World world, int id) {
        ProductType produit = null;
        for (ProductType p : world.getProducts().getProduct()) {
            if (p.getId() == id) {
                produit = p;
            }
        }
        return produit;
    }
// prend en paramètre le pseudo du joueur et le manager acheté.
// renvoie false si l’action n’a pas pu être traitée


   
}
