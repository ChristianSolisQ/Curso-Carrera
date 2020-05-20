/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Controller.ControllerCarrera;
import Controller.ControllerCurso;
import Controller.ControllerUsuario;
import Exceptions.GlobalException;
import Exceptions.NoDataException;
import Model.Modelo;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Interfaz implements Observer {

    private final Scanner entrada;
    private final Modelo modelo;
    private ControllerCarrera controlCarrera;
    private ControllerCurso controlCurso;
    private ControllerUsuario controlUsuario;

    private int convertir(String dato) {
        int resultado = -1;
        try {
            resultado = Integer.parseInt(dato);
        } catch (NumberFormatException excepcion) {
            System.err.println(String.format("ERROR: %s%n", excepcion.getMessage()));
        } finally {
            return resultado;
        }
    }

    private int digitaOpcion() {
        System.out.print("Seleccione una Opcion: ");
        String dato = this.entrada.nextLine();
        return convertir(dato);
    }

    private boolean login() throws GlobalException, NoDataException, SQLException {
        System.out.flush();
        String usuario = "???", clave = "???";
        System.out.println("****** EIF411 Diseño y programación de palataformas móviles Laboratorio 1 ****** \n");
        System.out.println("------------------ SISTEMA DE MATRICULA ------------------ \n");
        System.out.print("Digite el Usuario: ");
        usuario = entrada.nextLine();

        System.out.print("Digite su Clave: ");
        clave = entrada.nextLine();
        controlUsuario.verificaCredenciales(usuario, clave);
        if (modelo.getLogged() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public void limpiar() {
        int i = 0;
        for (i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private void menuPrincipal() {
//        clearConsole();
        limpiar();
        System.out.flush();
        System.out.println("------------------------------------------------------------------");
        System.out.println("*************** BIENVENIDO AL SISTEMA DE MATRICULA ***************");
        System.out.println("------------------------------------------------------------------");
        System.out.println("| Menu Principal:                                                |");
        System.out.println("|                                                                |");
        System.out.println("| 1- ADMINISTRAR CARRERAS                                        |");
        System.out.println("| 2- ADMINISTRAR CURSOS                                          |");
        System.out.println("| 3- SALIR DEL SISTEMA.                                          |");
        System.out.println("|                                                                |");
        System.out.println("------------------------------------------------------------------");
    }

    private void menuGestionCarreras() {
        limpiar();
        System.out.flush();
        System.out.println("-------------------------------------------------------------");
        System.out.println("---------------- ADMINISTRACION DE CARRERAS -----------------");
        System.out.println("|SELECCIONE UNA DE LAS SIGUIENTES OPCIONES                  |");
        System.out.println("|                                                           |");
        System.out.println("| 1- INGRESAR.                                              |");
        System.out.println("| 2- MODIFICAR.                                             |");
        System.out.println("| 3- ELIMINAR.                                              |");
        System.out.println("| 4- BUSCAR.                                                |");
        System.out.println("| 5- LISTAR.                                                |");
        System.out.println("| 6- REGRESAR AL MENU PRINCIPAL.                            |");
        System.out.println("|                                                           |");
        System.out.println("-------------------------------------------------------------");
    }

    private void registrarCarrera() throws GlobalException, NoDataException {
        String codigo, nombre, titulo;
        limpiar();
        System.out.flush();
        System.out.println("-----------------------------------------------------------");
        System.out.println("--------------- INGRESAR UNA NUEVA CARRERA ----------------");
        System.out.println("|                                                         |");
        System.out.println("|Complete el siguiente Formulario:                        |");
        System.out.println("|                                                         |");
          System.out.print("| Codigo de la Carrera: ");
        codigo = this.entrada.nextLine();
          System.out.print("| Nombre de la Carrera: ");
        nombre = this.entrada.nextLine();
          System.out.print("| El Titulo: ");
        titulo = this.entrada.nextLine();
        System.out.println("|                                                         |");
        System.out.println("-----------------------------------------------------------");
        controlCarrera.insertar_carrera(codigo, nombre, titulo);
        controlCarrera.actualizarCarrera();
//            System.out.println("*** CARRERA FUE REGISTRADA CON EXITO ***");

    }

    private void modificarCarrera() throws GlobalException, NoDataException {
        limpiar();
        String codigo, nombre, titulo;
        System.out.flush();
        System.out.println(controlCarrera.mostrarCarreras());
        System.out.println("-------------------------------------------------------");
        System.out.println("--------------- ACTUALIZAR UNA CARRERA ----------------");
        System.out.println("|Complete el siguiente Formulario:                    |");
        System.out.println("|                                                     |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        System.out.print("| El es Nombre: ");
        nombre = this.entrada.nextLine();
        System.out.print("| El Titulo es: ");
         titulo = this.entrada.nextLine();
        System.out.println("|                                                         |");
        System.out.println("-----------------------------------------------------------");
        if (controlCarrera.buscarCodigo(codigo)) {
            controlCarrera.modificar_carrera(codigo, nombre, titulo);
            controlCarrera.actualizarCarrera();
          
        } else {
          
        }
    }

    private void eliminarCarrera() throws GlobalException, NoDataException {
        limpiar();
        String codigo;
        System.out.flush();
        System.out.println(controlCarrera.mostrarCarreras());
        System.out.println("-----------------------------------------------------");
        System.out.println("--------------- ELIMINAR UNA CARRERA ----------------");
        System.out.println("|Complete el siguiente Formulario:                  |");
        System.out.println("|                                                   |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        if (controlCarrera.buscarCodigo(codigo)) {
            controlCarrera.eliminar_carrera(codigo);
            controlCarrera.actualizarCarrera();
            controlCurso.actualizarCurso();
           
        } else {
           
        }
    }

    private void buscarCarrera() throws GlobalException, NoDataException {
        limpiar();
        String codigo;
        System.out.flush();
        System.out.println("---------------------------------------------------");
        System.out.println("--------------- BUSCAR UNA CARRERA ----------------");
        System.out.println("|Complete el siguiente Formulario:                |");
        System.out.println("|                                                 |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        if (controlCarrera.buscarCodigo(codigo)) {
            controlCarrera.buscar_carrera(codigo);
            System.out.println(controlCarrera.getModelo().getCarreraActual().toString());
        } else {
          
        }
    }

    private void listarCarrera() {
        limpiar();
        System.out.flush();
        System.out.println("---------------------------------------------------");
        System.out.println("--------------- LISTAR LAS CARRERA ----------------");
        System.out.println(controlCarrera.mostrarCarreras());
        System.out.println("---------------------------------------------------");
    }

    private void menuGestionCurso() {
        limpiar();
        System.out.flush();
        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------- ADMINISTRACION DE CURSOS -----------------");
        System.out.println("|SELECCIONE UNA DE LAS SIGUIENTES OPCIONES                |");
        System.out.println("|                                                         |");
        System.out.println("| 1- INGRESAR UN NUEVO CURSO                              |");
        System.out.println("| 2- ACTUALIZAR                                           |");
        System.out.println("| 3- ELIMINAR                                             |");
        System.out.println("| 4- BUSCAR UN CURSO                                      |");
        System.out.println("| 5- LISTAR CURSOS                                        |");
        System.out.println("| 6- REGRESAR AL MENU PRINCIPAL.                          |");
        System.out.println("|                                                         |");
        System.out.println("-----------------------------------------------------------");
    }

    private void registrarCurso() throws GlobalException, NoDataException {
        limpiar();
        String codigo, nombre, nivel, ciclo, codigo_carrera;
        int creditos, horas;
        System.out.flush();
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------- INGRESAR UN NUEVO CURSO ----------------");
        System.out.println("| Complete el siguiente Formulario:                     |");
        System.out.println("|                                                       |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        System.out.print("| El Nombre es: ");
        nombre = this.entrada.nextLine();
        try {
            System.out.print("| Cantidad de Creditos: ");
            creditos = Integer.parseInt(this.entrada.nextLine());
            System.out.print("| Horas semanales: ");
            horas = Integer.parseInt(this.entrada.nextLine());
        } catch (NumberFormatException excepcion) {
            System.err.println(String.format("ERROR: %s%n", excepcion.getMessage()));
            System.out.println("--ALGO FALLO, VUELVA A INTENTAR--");
            return;
        }
        System.out.print("| El Nivel es: ");
        nivel = this.entrada.nextLine();
        System.out.print("| El Ciclo: ");
        ciclo = this.entrada.nextLine();
        System.out.print("| Codigo carrera: ");
        codigo_carrera = this.entrada.nextLine();
        System.out.println("|                                                       |");
        System.out.println("--------------------------------------------------------");
        controlCurso.insertar_curso(codigo, nombre, creditos, horas, nivel, ciclo, codigo_carrera);
        controlCurso.actualizarCurso();

    }

    private void ModificarCurso() throws NoDataException, GlobalException {
         limpiar();
        String codigo, nombre, nivel, ciclo, codigo_carrera;
        int creditos, horas;
        System.out.flush();
        System.out.println(controlCurso.listar_curso());
        System.out.println("-------------------------------------------------");
        System.out.println("--------------- ACTUALIZAR CURSO ----------------");
        System.out.println("| Complete el siguiente Formulario              |");
        System.out.println("|                                               |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        System.out.print("| El Nombre es: ");
        nombre = this.entrada.nextLine();
        try {
            System.out.print("| Los Creditos: ");
            creditos = Integer.parseInt(this.entrada.nextLine());
            System.out.print("| Horas semanales: ");
            horas = Integer.parseInt(this.entrada.nextLine());
        } catch (NumberFormatException excepcion) {
            System.err.println(String.format("ERROR: %s%n", excepcion.getMessage()));
            System.out.println("--ALGO FALLO, VUELVA A INTENTAR--");
            return;
        }
        System.out.print("| El Nivel: ");
        nivel = this.entrada.nextLine();
        System.out.print("| El Ciclo: ");
        ciclo = this.entrada.nextLine();
        System.out.print("| Codigo carrera: ");
        codigo_carrera = this.entrada.nextLine();
        System.out.println("|                                                       |");
        System.out.println("--------------------------------------------------------");
        if (controlCurso.buscarCodigo(codigo) && controlCarrera.buscarCodigo(codigo_carrera)) {
            controlCurso.modificar_curso(codigo, nombre, creditos, horas, nivel, ciclo, codigo_carrera);
            controlCurso.actualizarCurso();
          
        } else {
           
        }
    }

    private void eliminarCurso() throws GlobalException, NoDataException {
         limpiar();
        String codigo;
        System.out.flush();
        System.out.println(controlCurso.listar_curso());
        System.out.println("-------------------------------------------------");
        System.out.println("--------------- ELIMINAR CARRERA ----------------");
         System.out.println("| Complete el siguiente Formulario             |");
        System.out.println("|                                               |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        System.out.println("|                                               |");
        System.out.println("-------------------------------------------------");
        if (controlCurso.buscarCodigo(codigo)) {
            controlCurso.eliminar_curso(codigo);
            controlCurso.actualizarCurso();
          
        } else {
            
        }
    }

    private void buscarCurso() throws GlobalException, NoDataException {
         limpiar();
        String codigo;
        System.out.flush();
        System.out.println("-----------------------------------------------");
        System.out.println("--------------- BUSCAR CARRERA ----------------");
        System.out.println("| Complete el siguiente Formulario            |");
        System.out.println("|                                             |");
        System.out.print("| El Codigo es: ");
        codigo = this.entrada.nextLine();
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
        if (controlCurso.buscarCodigo(codigo)) {
            controlCurso.buscar_curso(codigo);
            System.out.println(controlCurso.getModel().getCursoActual().toString());
        } else {
          
        }
    }

    private void listarCurso() {
         limpiar();
        System.out.flush();
        System.out.println("--------------------------------------------------");
        System.out.println("--------------- LISTAR LOS CURSOS ----------------");
        System.out.println(controlCurso.mostrarCursos());
        System.out.println("|                                                |");
        System.out.println("--------------------------------------------------");
    }

    public Interfaz(Modelo modelo, ControllerCarrera controlCarrera, ControllerCurso controlCurso, ControllerUsuario controlUsuario) {
        this.entrada = new Scanner(System.in);
        this.modelo = modelo;
        this.controlCarrera = controlCarrera;
        this.controlCurso = controlCurso;
        this.controlUsuario = controlUsuario;
        this.modelo.addObserver(this);
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Scanner getEntrada() {
        return entrada;
    }

    public ControllerCarrera getControlCarrera() {
        return controlCarrera;
    }

    public ControllerCurso getControlCurso() {
        return controlCurso;
    }

    public ControllerUsuario getControlUsuario() {
        return controlUsuario;
    }

    public void setControlCarrera(ControllerCarrera controlCarrera) {
        this.controlCarrera = controlCarrera;
    }

    public void setControlCurso(ControllerCurso controlCurso) {
        this.controlCurso = controlCurso;
    }

    public void setControlUsuario(ControllerUsuario controlUsuario) {
        this.controlUsuario = controlUsuario;
    }

    public void inicializar() throws GlobalException, NoDataException, SQLException {
        int opcion, opcionCarrera, opcionCurso;
        opcion = opcionCarrera = opcionCurso = -1;
        if (login()) {
            while (opcion != 3) {
                menuPrincipal();
                opcion = digitaOpcion();
                switch (opcion) {
                    case 1: {
                        while (opcionCarrera != 6) {
                            menuGestionCarreras();
                            opcionCarrera = digitaOpcion();

                            if (opcionCarrera == 1) {
                                registrarCarrera();
                                continue;
                            }
                            if (opcionCarrera == 2) {
                                modificarCarrera();
                                continue;
                            }
                            if (opcionCarrera == 3) {
                                eliminarCarrera();
                                continue;
                            }
                            if (opcionCarrera == 4) {
                                buscarCarrera();
                                continue;
                            }
                            if (opcionCarrera == 5) {
                                listarCarrera();
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                    case 2: {
                        while (opcionCurso != 6) {
                            menuGestionCurso();
                            opcionCurso = digitaOpcion();

                            if (opcionCurso == 1) {
                                registrarCurso();
                                continue;
                            }
                            if (opcionCurso == 2) {

                                ModificarCurso();

                                continue;
                            }
                            if (opcionCurso == 3) {

                                eliminarCurso();

                                continue;
                            }
                            if (opcionCurso == 4) {
                                buscarCurso();
                                continue;
                            }
                            if (opcionCurso == 5) {
                                listarCurso();
                                continue;
                            }
                            break;
                        }
                        break;
                    }

                    case 3: {
                        System.out.println("OUT TO APLICATION");
                        break;
                    }

                    default: {
                        System.out.println("OPCION INVALIDA.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("DATOS INCORRECTOS. INTENTE NUEVAMENTE.");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE APLICATION");
    }

}
