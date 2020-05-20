/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio01.consola;

import Controller.ControllerCarrera;
import Controller.ControllerCurso;
import Controller.ControllerUsuario;
import Exceptions.GlobalException;
import Exceptions.NoDataException;
import Model.Modelo;
import Presentation.Interfaz;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Laboratorio01Consola {

    public static void main(String[] args) throws GlobalException, NoDataException, SQLException {
        Modelo m = new Modelo();
        ControllerCarrera c1 = new ControllerCarrera(m);
        ControllerCurso c2 = new ControllerCurso(m);
        ControllerUsuario c3 = new ControllerUsuario(m);
        Interfaz view = new Interfaz(m, c1, c2, c3);
       // System.out.println(c2.buscarCodigo("EIF212"));
        view.inicializar();
    }
    
}
