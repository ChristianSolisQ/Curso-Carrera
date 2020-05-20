/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import LogicaNegocios.Curso;
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
public class GestorCursos extends Gestor {

    private static final String INSERTAR_CURSOS = "{call insertar_cursos(?,?,?,?,?,?,?)}";
    private static final String MODIFICAR_CURSOS = "{call modificar_cursos(?,?,?,?,?,?,?)}";
    private static final String CONSULTAR_CURSOS = "{?=call consultar_cursos(?)}";
    private static final String LISTAR_CURSOS = "{?=call listar_cursos()}";
    private static final String ELIMINAR_CURSO = "{call eliminar_curso(?)}";
     private static final String BUSCAR_CURSOS
            = "select codigo, nombre, creditos, horas_semanales, nivel, ciclo, codigo_carrera "
            + "from Curso where codigo = ?";
   private static final String BUSCAR_CURSOS_CARRERA
            = "select codigo, nombre, creditos, horas_semanales, nivel, ciclo, codigo_carrera "
            + "from Curso where codigo_carrera = ?";
    public static String codigo = "";
    public static String codigo_carrera = "";

    public GestorCursos() {
    }

    public void insertar_curso(Curso curso) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTAR_CURSOS);
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras_semanales());
            pstmt.setString(5, curso.getNivel());
            pstmt.setString(6, curso.getCiclo());
            pstmt.setString(7, curso.getCodigo_carrera());

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
    }// insertar curso

    //MODIFICAR CURSO
    public void modificar_curso(Curso curso) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(MODIFICAR_CURSOS);
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras_semanales());
            pstmt.setString(5, curso.getNivel());
            pstmt.setString(6, curso.getCiclo());
            pstmt.setString(7, curso.getCodigo_carrera());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "No se realizo la insercion", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
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
    }// modificar curso

    //CONSULTAR CURSO
    public Curso buscar_curso(String id) throws GlobalException, NoDataException {

        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(CONSULTAR_CURSOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES"),
                        rs.getString("NIVEL"),
                        rs.getString("CICLO"),
                        rs.getString("CODIGO_CARRERA"));
                coleccion.add(elCurso);
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
        if (coleccion == null || coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return elCurso;
    }//CONSULTAR CURSO

    //Listar Cursos
    public List listar_curso() throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso elCurso = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CURSOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES"),
                        rs.getString("NIVEL"),
                        rs.getString("CICLO"),
                        rs.getString("CODIGO_CARRERA")
                );
                coleccion.add(elCurso);
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
    }
    // fin del metodo listar cursos

    //Eliminar cursos
    public void eliminar_curso(String id) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_CURSO);
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

    }
 public List<Curso> listarTodosPorCodigo() throws SQLException {
        List<Curso> r = new ArrayList<>();
        try {
            connection();
            try (PreparedStatement stm = conexion.prepareStatement(BUSCAR_CURSOS)) {
                stm.clearParameters();
                
                stm.setString(1, codigo);
           ResultSet rs = stm.executeQuery();
        
                 rs = stm.executeQuery();

                while (rs.next()) {
                    Curso elCurso = new Curso(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES"),
                        rs.getString("NIVEL"),
                        rs.getString("CICLO"),
                        rs.getString("CODIGO_CARRERA")
                );
                r.add(elCurso);               
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
  public List<Curso> listarTodosPorCarrera() throws SQLException {
        List<Curso> r = new ArrayList<>();
        try {
            connection();
            try (PreparedStatement stm = conexion.prepareStatement(BUSCAR_CURSOS_CARRERA)) {
                stm.clearParameters();
                
                stm.setString(1, codigo_carrera);
           ResultSet rs = stm.executeQuery();
        
                 rs = stm.executeQuery();

                while (rs.next()) {
                    Curso elCurso = new Curso(rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("CREDITOS"),
                        rs.getInt("HORAS_SEMANALES"),
                        rs.getString("NIVEL"),
                        rs.getString("CICLO"),
                        rs.getString("CODIGO_CARRERA")
                );
                r.add(elCurso);               
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
   public static void main(String[] args) throws NoDataException, GlobalException, SQLException {
//    
//     Curso curse = new Curso("MAT006","Proba",7, 4, "III","II","EIF");
//        

        GestorCursos servCurse= new GestorCursos();
//        
////        System.out.println(curse.toString());
////        
////        servCurse.insertar_curso(curse);
//        
        System.out.println(servCurse.listarTodosPorCarrera());
    }

   
}
