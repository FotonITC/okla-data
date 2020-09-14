package com.foton.okla;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.foton.okla.models.Plat;
 

public class App {
     
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Plat plat = new Plat();
        List<String> countries = new ArrayList<String>();
        plat.setLabel("coscous");
        
        countries.add("Tunisia");
        countries.add("Maghreb");
        countries.add("Algeria");
        
        plat.setCountries(countries);
        
        session.save(plat);
         
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}