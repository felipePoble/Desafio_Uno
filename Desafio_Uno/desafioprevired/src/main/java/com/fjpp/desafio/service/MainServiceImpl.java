package com.fjpp.desafio.service;

import com.fjpp.desafio.exception.NotReachableException;
import com.fjpp.desafio.model.DesafioResponse;
import com.fjpp.desafio.model.GDDResponse;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<DesafioResponse> getResponseFromGdd() {
        DesafioResponse desafioResponse = new DesafioResponse();
        GDDResponse gddResponse = null;
        List<LocalDate> datesList;
        int monthsDiff;
        HttpStatus responseStatus = HttpStatus.SERVICE_UNAVAILABLE;
        try {
            gddResponse = callService();
        } catch (NotReachableException e) {
            logger.info("No se ha podido obtener la respuesta del servicio GDD", e);
        } catch (Exception ex) {
            logger.info("Error inesperado - No se ha podido obtener la respuesta del servicio GDD", ex);
        }
        if (null == gddResponse) {
            return new ResponseEntity<>(desafioResponse, responseStatus);
        }
        desafioResponse = new DesafioResponse(gddResponse);
        datesList = gddResponse.getFechas();
        for (int i = 0; i < gddResponse.getFechas().size(); i++) {
            monthsDiff = getMonthsDiffNumberBetweenDatesFromList(datesList, i);
            if (monthsDiff > 0)
                desafioResponse.getFechasFaltantes()
                        .addAll(
                                getMonthDatesListBetweenDates(
                                        datesList.get(i),
                                        datesList.get(i + 1)));
        }
        responseStatus = HttpStatus.OK;
        return new ResponseEntity<>(desafioResponse, responseStatus);
    }

    /**
     * Método encargado de llamar al rest de GDD
     *
     * @return La respuesta del servicio GDD
     * @throws NotReachableException se obtendrá si no se puede obtener respuesta del servicio GDD
     */
    private GDDResponse callService() throws NotReachableException {
        try {
            return restTemplate.getForObject(env.getProperty("rutaGdd"), GDDResponse.class);
        } catch (Exception e) {
            throw new NotReachableException(e.getMessage());
        }
    }

    /**
     *
     * @param datesList La lista desde donde se extraeran las fechas a comparar
     * @param indice    El primer indice de los dos indices consecutivos a comparar
     * @return la cantidad de meses de diferencia entre los dos indices. Devuelve 0 si es el ultimo indice.
     */
    private int getMonthsDiffNumberBetweenDatesFromList(List<LocalDate> datesList, int indice) {
        return (indice >= datesList.size() || indice + 1 >= datesList.size()) ? 0 :
                Months.monthsBetween(
                        datesList.get(indice),
                        datesList.get(indice + 1)
                ).getMonths();
    }

    /**
     * Se encarga de obtener la lista de meses en forma de fecha entre dos fechas.
     *
     * @param startDate La fecha en la que inicia el rango del cual se desea obtener la lista de meses
     * @param endDate   La fecha en la que finaliza el rango.
     * @return Devuelve la lista de meses que existen entre las dos fechas pasadas por parametro.
     */
    private List<LocalDate> getMonthDatesListBetweenDates(LocalDate startDate, LocalDate endDate) {
        LocalDate auxDate = startDate;
        List<LocalDate> result = new ArrayList<>();
        while (auxDate.isBefore(endDate)) {
            auxDate = auxDate.plusMonths(1);
            if (!auxDate.monthOfYear().equals(endDate.monthOfYear()))
                result.add(auxDate);
        }
        return result;
    }
}
