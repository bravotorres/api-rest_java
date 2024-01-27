package com.quironlabs.api.models.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quironlabs.api.models.entities.City;

public class CityRepository {
    private static Logger logger = LoggerFactory.getLogger(CityRepository.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<City> getAll() {
        try {
            List<City> cities = new ArrayList<>();
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("PKG_LABS01.CITY_READ_ALL");

            // Registrar los parámetros de entrada y salida:
            // PKG_LABS01.CITY_READ_ALL(response, code, message)
            spQuery.registerStoredProcedureParameter("response", Void.class, ParameterMode.REF_CURSOR);
            spQuery.registerStoredProcedureParameter("code", Integer.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("message", String.class, ParameterMode.OUT);

            spQuery.execute();

            String code = spQuery.getOutputParameterValue("code").toString();
            logger.info("Code: {}", code);

            String message = spQuery.getOutputParameterValue("message").toString();
            logger.info("Message: {}", message);

            // Obtenemos el resultado del cursos en una lista
            List<Object[]> results = spQuery.getResultList();

            // Recorremos la lista con map y devolvemos un List<BusinessObject>
            cities = results.stream().map(
                result -> new City(
                    (Integer) result[0], 
                    (result[1]).toString(), 
                    (result[2]).toString()
                )
           ).toList();

            return cities;
        } catch (Exception e) {
            logger.error("Error in Persistence Layer: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
}
