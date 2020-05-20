/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import LogicaNegocios.Carrera;
import Model.Modelo;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ControllerCarrera {
       private Modelo model;

    public ControllerCarrera() throws GlobalException, NoDataException {
        model = new Modelo();
    }

    public ControllerCarrera(Modelo modelo) {
        this.model = modelo;
    }

    public Modelo getModelo() {
        return model;
    }

    public void setModelo(Modelo modelo) {
        this.model = modelo;
    }

    public void insertar_carrera(String codigo, String nombre, String titulo) throws GlobalException, NoDataException {
        model.setCarreraActual(new Carrera(codigo, nombre, titulo));
       model.insertar_carrera(model.getCarreraActual());
        model.setCarreraActual(new Carrera());
        
    }

    public void modificar_carrera(String codigo, String nombre, String titulo) throws GlobalException, NoDataException {
        model.setCarreraActual(new Carrera(codigo, nombre, titulo));
        model.modificar_carrera(model.getCarreraActual());
        model.setCarreraActual(new Carrera());
    }

    public void eliminar_carrera(String codigo) throws GlobalException, NoDataException {
        model.eliminar_carrera(codigo);
    }

    public void buscar_carrera(String codigo) throws GlobalException, NoDataException {
        model.setCarreraActual(model.buscar_carrera(codigo));
    }

    public ArrayList<Carrera> listar_Carreras() throws GlobalException, NoDataException  {
        return model.listar_carrera();
    }

    public boolean buscarCodigo(String codigo) {
        return model.buscarCodigoCarrera(codigo);
    }

    public void actualizarCarrera() throws GlobalException, NoDataException {
        model.actualizarCarrera();
    }
    
    public String mostrarCarreras() {
        return model.mostrarCarreras();
    }
}
