/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import Model.Modelo;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ControllerUsuario {
      Modelo model;

    public ControllerUsuario(Modelo model) {
        this.model = model;
    }

    public void verificaCredenciales(String user, String pass) throws GlobalException, NoDataException, SQLException {
       // model.setUsuarioActual(user, pass);
        model.validarCredenciales(user,pass);
      //  model.setUsuarioActual(new Usuario());
    }
}
