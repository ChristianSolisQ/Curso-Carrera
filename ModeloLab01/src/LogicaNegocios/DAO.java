/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocios;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import Gestores.GestorCarreras;
import Gestores.GestorCursos;
import Gestores.GestorUsuarios;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class DAO {

    private static DAO instanciaUnica;
    private final GestorCarreras gestorCarreras;
    private final GestorCursos gestorCursos;
    private final GestorUsuarios gestorUsuarios;

    public static DAO instanciar() {
        if (instanciaUnica == null) {
            instanciaUnica = new DAO();
        }
        return instanciaUnica;
    }

    private DAO() {
        this.gestorCarreras = new GestorCarreras();
        this.gestorCursos = new GestorCursos();
        this.gestorUsuarios = new GestorUsuarios();
    }

    //Seccion de Usuario
    public boolean iniciar_sesion(String user, String pass) throws GlobalException, NoDataException, SQLException {

        return gestorUsuarios.verificarUsuario(user, pass);

    }

    //Seccion de Carrera
    public void insertar_carrera(Carrera carrera) throws GlobalException, NoDataException {

        gestorCarreras.insertar_carrera(carrera);

    }

    public void modificar_carrera(Carrera carrera) throws GlobalException, NoDataException {

        gestorCarreras.modificar_carrera(carrera);

    }

    public void eliminar_carrera(String codigo) throws GlobalException, NoDataException {

        gestorCarreras.eliminar_carrera(codigo);

    }

    public Carrera buscar_carrera(String codigo) throws GlobalException, NoDataException {
        Carrera carrera = null;

        carrera = gestorCarreras.buscar_carrera(codigo);

        return carrera;
    }

    public ArrayList<Carrera> listar_carrera() throws GlobalException, NoDataException {
        ArrayList<Carrera> coleccion = null;

        coleccion = (ArrayList<Carrera>) gestorCarreras.listar_carrera();

        return coleccion;
    }

    //Seccion de Curso
    public void insertar_curso(Curso curso) throws GlobalException, NoDataException {

        gestorCursos.insertar_curso(curso);

    }

    public void modificar_curso(Curso curso) throws NoDataException, GlobalException {
        
            gestorCursos.modificar_curso(curso);
       
    }

    public void eliminar_curso(String codigo) throws GlobalException, NoDataException {
      
            gestorCursos.eliminar_curso(codigo);
       
    }

    public Curso buscar_curso(String codigo) throws GlobalException, NoDataException {
        Curso curso = null;
        
            curso = gestorCursos.buscar_curso(codigo);
        
        return curso;
    }

    public ArrayList<Curso> listar_curso() throws GlobalException, NoDataException {
        ArrayList<Curso> coleccion = null;
         coleccion = (ArrayList<Curso>) gestorCursos.listar_curso();
        return coleccion;
    }
    

}
