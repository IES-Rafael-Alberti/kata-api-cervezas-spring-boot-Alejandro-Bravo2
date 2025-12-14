package api.kata.cervezas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para la gestión de cervezas
 * Esta aplicación proporciona una API REST para administrar información sobre cervezas,
 * fabricantes, categorías y estilos de cerveza
 *
 * @author Sistema de Gestión de Cervezas
 * @version 1.0
 */
@SpringBootApplication
public class AplicacionCervezas {

    /**
     * Método principal que inicia la aplicación Spring Boot
     * @param argumentos Argumentos de línea de comandos
     */
    public static void main(String[] argumentos) {
        SpringApplication.run(AplicacionCervezas.class, argumentos);
    }

}
