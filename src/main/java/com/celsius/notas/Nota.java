package com.celsius.notas;

import java.util.UUID;

// POJO = Plain Old Java Object
public class Nota {
  // Ctrl + . -> Generar Getters y Setters
  private String id;
  private String titulo;
  private String contenido;

  public Nota(String id, String titulo, String contenido) {
    if (id == null){
    this.id = UUID.randomUUID().toString();
    } else {
      this.id = id;
    }
    this.titulo = titulo;
    this.contenido = contenido;
  }

  public String getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    // this.titulo se refiere a la propiedad
    // titulo de la clase.
    // titulo sin this. se refiere al parámetro titulo
    this.titulo = titulo;
  }

  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  @Override
  public String toString() {
    // El control ListView llama automáticamente a este
    // método para obtener el texto a mostrar en la lista
    return titulo;
  }
}
