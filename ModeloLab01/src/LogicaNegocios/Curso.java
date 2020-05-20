/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocios;

/**
 *
 * @author Usuario
 */
public class Curso {
    
    private String codigo;
   private String nombre;
   private int creditos;
   private int horas_semanales;
   private String nivel;
   private String ciclo;
   private String codigo_carrera;  

    public Curso(String codigo, String nombre, int creditos, int horas_semanales, String nivel, String ciclo, String codigo_carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas_semanales = horas_semanales;
        this.nivel = nivel;
        this.ciclo = ciclo;
        this.codigo_carrera = codigo_carrera;
    }

    public Curso() {
    }
    


   
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras_semanales() {
        return horas_semanales;
    }

    public void setHoras_semanales(int horas_semanales) {
        this.horas_semanales = horas_semanales;
    }

    public String getCodigo_carrera() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(String codigo_carrera) {
        this.codigo_carrera = codigo_carrera;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + ", horas_semanales=" + horas_semanales + ", nivel=" + nivel + ", ciclo=" + ciclo + ", codigo_carrera=" + codigo_carrera + '}';
    }


  
   
}

