package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // Indica que esta clase es una entidad de la base de datos
@Table(name = "usuarios") // Nombre de la tabla en la que se almacenarán los registros de esta entidad
@ToString // Genera el método toString() automáticamente usando la biblioteca Lombok
@EqualsAndHashCode // Genera los métodos equals() y hashCode() automáticamente usando Lombok
public class Usuario {

    @Id // Indica que este campo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que se usará una estrategia de generación de claves autoincremental
    @Getter @Setter @Column(name = "id") // Anotaciones de Lombok que generan los métodos getter y
                                        // setter para el campo "id" y el nombre de la columna correspondiente
    private Long id;

    @Getter @Setter @Column(name = "nombre") // Anotaciones de Lombok que generan los métodos getter y
                                            // setter para el campo "nombre" y el nombre de la columna correspondiente
    private String nombre;

    @Getter @Setter @Column(name = "apellido") // Anotaciones de Lombok que generan los métodos getter y
                                              // setter para el campo "apellido" y el nombre de la columna correspondiente
    private String apellido;

    @Getter @Setter @Column(name = "email") // Anotaciones de Lombok que generan los métodos getter y
                                           // setter para el campo "email" y el nombre de la columna correspondiente
    private String email;

    @Getter @Setter @Column(name = "telefono") // Anotaciones de Lombok que generan los métodos getter y
                                              // setter para el campo "telefono" y el nombre de la columna correspondiente
    private String telefono;

    @Getter @Setter @Column(name = "password") // Anotaciones de Lombok que generan los métodos getter y
                                              // setter para el campo "password" y el nombre de la columna correspondiente
    private String password;
}


