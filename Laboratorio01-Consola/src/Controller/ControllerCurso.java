/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import LogicaNegocios.Curso;
import Model.Modelo;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ControllerCurso {
     private Modelo model;

    public ControllerCurso() throws GlobalException, NoDataException {
        this.model = new Modelo();
    }

    public ControllerCurso(Modelo model) {
        this.model = model;
    }

    public Modelo getModel() {
        return model;
    }

    public void setModel(Modelo model) {
        this.model = model;
    }
    
    public void insertar_curso(String codigo, String nombre, int creditos, int horas, String nivel, String ciclo, String codCarrera) throws GlobalException, NoDataException {
        model.setCursoActual(new Curso(codigo, nombre, creditos, horas, nivel, ciclo,codCarrera));
        model.insertar_curso(model.getCursoActual());
        model.setCursoActual(new Curso());
       
    }

    public void modificar_curso(String codigo, String nombre, int creditos, int horas, String nivel, String ciclo, String codCarrera) throws NoDataException, GlobalException {
        model.setCursoActual(new Curso(codigo, nombre, creditos, horas, nivel, ciclo, codCarrera));
        model.modificar_curso(model.getCursoActual());
        model.setCursoActual(new Curso());
    }

    public void eliminar_curso(String codigo) throws GlobalException, NoDataException {
        model.eliminar_curso(codigo);
    }

    public void buscar_curso(String codigo) throws GlobalException, NoDataException {
        model.setCursoActual(model.buscar_curso(codigo));
    }

    public ArrayList<Curso> listar_curso() throws GlobalException, NoDataException {
        return model.listar_curso();
    }

    public boolean buscarCodigo(String codigo) {
        return model.buscarCodigoCursos(codigo);
    }

    public void actualizarCurso() throws GlobalException, NoDataException {
        model.actualizarCurso();
    }
    
    public String mostrarCursos() {
        return model.mostrarCursos();
    }
 
}
