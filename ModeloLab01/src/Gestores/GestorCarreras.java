/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import LogicaNegocios.Carrera;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author Usuario
 */
public class GestorCarreras extends Gestor{
    private static final String INSERTAR_CARRERA = "{call insertar_carrera(?,?,?)}";
    private static final String MODIFICAR_CARRERA = "{call modificar_carrera(?,?,?)}";
    private static final String CONSULTAR_CARRERA = "{?=call consultar_carrera(?)}";
    private static final String LISTAR_CARRERA = "{?=call listar_carrera()}";
    private static final String ELIMINAR_CARRERAS = "{call eliminar_carreras(?)}";
     private static final String BUSCAR_CARRERAS
            = "select codigo, nombre, titulo "
            + "from Carrera where codigo = ?";

    public static String codigo = "";
   
    public GestorCarreras() {
    }
    
    public void insertar_carrera(Carrera carrera) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTAR_CARRERA);
            pstmt.setString(1, carrera.getCodigo());
            pstmt.setString(2, carrera.getNombre());
            pstmt.setString(3, carrera.getTitulo());
          

            boolean resultado = pstmt.execute();
            if (resultado == true) {
                JOptionPane.showMessageDialog(null, "No se realizo la insercion", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Llave duplicada", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Estatutos invalidos o nulos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }// insertar carrera
      public Carrera buscar_carrera(String id) throws GlobalException, NoDataException {

        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera laCarrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(CONSULTAR_CARRERA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                laCarrera = new Carrera(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getString("TITULO"));
                coleccion.add(laCarrera);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return laCarrera;
    }// fin del consulta carrera
      
      public List listar_carrera() throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

         ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        
        CallableStatement pstmt = null;
        try {
         pstmt = conexion.prepareCall(LISTAR_CARRERA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                Carrera laCarrera = new Carrera(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getString("TITULO")
                );
                coleccion.add(laCarrera);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }// fin de listar

    /*Eliminar carrera*/
    public void eliminar_carrera(String id) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_CARRERAS);
            pstmt.setString(1, id);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }//eliminarCarrera
    
    public List<Carrera> listarTodosPorCodigo() throws SQLException {
        List<Carrera> r = new ArrayList<>();
        try {
            connection();
            try (PreparedStatement stm = conexion.prepareStatement(BUSCAR_CARRERAS)) {
                stm.clearParameters();
                
                stm.setString(1, codigo);
           ResultSet rs = stm.executeQuery();
        
                 rs = stm.executeQuery();

                while (rs.next()) {
                    Carrera laCarrera = new Carrera(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getString("TITULO"));
                
                r.add(laCarrera);               
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (conexion != null) {
                disconnect();
            }
        }
        return r;
    }
      public void modificar_carrera(Carrera laCarrera) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(MODIFICAR_CARRERA);
            pstmt.setString(1, laCarrera.getCodigo());
            pstmt.setString(2, laCarrera.getNombre());
            pstmt.setString(3, laCarrera.getTitulo());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }//fin del metodo modificar carrera
   
}
 