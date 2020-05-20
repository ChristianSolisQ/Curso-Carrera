/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import LogicaNegocios.Carrera;
import LogicaNegocios.Curso;
import LogicaNegocios.DAO;
import LogicaNegocios.Usuario;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Usuario
 */
public class Modelo extends Observable {
    private ArrayList<Carrera> carreras;
    private Carrera carreraActual;

    private ArrayList<Curso> cursos;
    private Curso cursoActual;

    private Usuario usuarioActual;
    private int logged;
    
        public void actualizarCarrera() throws GlobalException, NoDataException {
            this.carreras = listar_carrera();
            setChanged();
            notifyObservers();
       
    }

    public void actualizarCurso() throws GlobalException, NoDataException {

            this.cursos = listar_curso();
            setChanged();
            notifyObservers();
        
    }
      public Modelo() throws GlobalException, NoDataException {
        this.carreras = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.carreraActual = new Carrera();
        this.cursoActual = new Curso();
        this.usuarioActual = new Usuario();
        this.logged = -1;
        actualizarCarrera();
        actualizarCurso();
    }
      public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public Carrera getCarreraActual() {
        return carreraActual;
    }

    public void setCarreraActual(Carrera carreraActual) {
        this.carreraActual = carreraActual;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCursoActual() {
        return cursoActual;
    }

    public void setCursoActual(Curso cursoActual) {
        this.cursoActual = cursoActual;
    }
       public void insertar_carrera(Carrera carrera) throws GlobalException, NoDataException {
         DAO.instanciar().insertar_carrera(carrera);
    }

    public void modificar_carrera(Carrera carrera) throws GlobalException, NoDataException {
        DAO.instanciar().modificar_carrera(carrera);
    }

    public void eliminar_carrera(String codigo) throws GlobalException, NoDataException {
        DAO.instanciar().eliminar_carrera(codigo);
    }

    public Carrera buscar_carrera(String codigo) throws GlobalException, NoDataException {
        return DAO.instanciar().buscar_carrera(codigo);
    }

    public ArrayList<Carrera> listar_carrera() throws GlobalException, NoDataException {
        carreras = DAO.instanciar().listar_carrera();
        return carreras;
    }

    public void insertar_curso(Curso curso) throws GlobalException, NoDataException {
         DAO.instanciar().insertar_curso(curso);
    }

    public void modificar_curso(Curso curso) throws NoDataException, GlobalException {
        DAO.instanciar().modificar_curso(curso);
    }

    public void eliminar_curso(String codigo) throws GlobalException, NoDataException {
        DAO.instanciar().eliminar_curso(codigo);
    }

    public Curso buscar_curso(String codigo) throws GlobalException, NoDataException {
        return DAO.instanciar().buscar_curso(codigo);
    }

    public ArrayList<Curso> listar_curso() throws GlobalException, NoDataException  {
        cursos = DAO.instanciar().listar_curso();
        return cursos;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.setChanged();
        this.notifyObservers();
    }

    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
        this.setChanged();
        this.notifyObservers();
    }

    public void validarCredenciales(String cedula, String clave) throws GlobalException, NoDataException, SQLException {
        if (DAO.instanciar().iniciar_sesion(cedula, clave)) {
            logged = 0;
        } else {
            logged = 1;
        }
    }

    public boolean buscarCodigoCursos(String codigo) {
        return this.cursos.stream().anyMatch((objeto) -> (objeto.getCodigo().equals(codigo)));
    }

    public boolean buscarCodigoCarrera(String codigo) {
        return this.carreras.stream().anyMatch((objeto) -> (objeto.getCodigo().equals(codigo)));
    }

    public String mostrarCarreras() {
        return this.carreras.toString();
    }

    public String mostrarCursos() {
        return this.cursos.toString();
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }
}
