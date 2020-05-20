/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import Exceptions.GlobalException;
import Exceptions.NoDataException;
import static Gestores.Gestor.conexion;
import static Gestores.Gestor.connection;
import static Gestores.Gestor.disconnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class GestorUsuarios extends Gestor {

//    private String URL_Servidor = "localhost";
//    private static final String BASE_DATOS = "MatriculaBD";
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "root";
    private static final String CMD_VERIFICAR = "SELECT * FROM Usuario WHERE cedula=? AND clave=? ";
   
//     public void setUrlServidor(String nuevoURL) {
//        URL_Servidor = nuevoURL;
//    }
     
    //esto me da lo que ocupo de estudiante activo o no
//     public void actualizarEstudiante(Estudiante estudiante) {
//        estudiante.actualizar();
//        usuarios.put(estudiante.getId(), estudiante);
//    }
    public GestorUsuarios() {
        //usuarios = new HashMap<>();
    }
    public  boolean verificarUsuario(String id, String clave) throws GlobalException, NoDataException, SQLException {

      boolean encontrado = false;
       
        try {
       connection();

            try (PreparedStatement stm = conexion.prepareStatement(CMD_VERIFICAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                stm.setString(2, clave);
                ResultSet rs = stm.executeQuery();
                encontrado = rs.next();
                                               
            }

        } catch (ClassNotFoundException
                | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (conexion != null) {
                disconnect();
            }
        }
        return encontrado;
    }
}

    //}

//
//    public static int validar_ingreso(String cedula, String clave) throws ClassNotFoundException, NoDataException {
//
//        int resultado = 0;
//
//        String SSQL = "SELECT * FROM Usuario WHERE cedula=? AND clave=? ";
//
//        try {
//            connection();
//            Statement st = conexion.createStatement();
//            ResultSet rs = st.executeQuery(SSQL);
//
//            if (rs.next()) {
//
//                resultado = 1;
//
//            }
//
//        } catch (SQLException ex) {
//
//            //JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
//            throw new NoDataException("La base de datos no se encuentra disponible");
//
//        } finally {
//
//            try {
//
//                disconnect();
//
//            } catch (SQLException ex) {
//
//                throw new NoDataException("La base de datos no se encuentra disponible");
//
//            }
//
//        }
//
//        return resultado;
//
//    }
//    public  boolean verificarUsuario2(String cedula, String clave){
//        boolean valido= false;
//        if (("11111111".equals(cedula) || "22222222".equals(cedula)) && ("1234".equals(clave) || "12345".equals(clave))  )
//            return true;
//        else 
//            return false;
//        
//    }
//    public void agregarUsuarios(){
//        Usuario u1 = new Usuario("11111111", "1234");
//       Usuario u2 = new Usuario("22222222", "12345");
//        List<Usuario> usuarios = new ArrayList<>();
//        usuarios.add(u1);
//          usuarios.add(u2);
//    }
    

    //public static void main(String[] args) {
//        try {
////            Usuario u1 = new Usuario("11111111", "1234");
////            Usuario u2 = new Usuario("22222222", "12345");
////            List<Usuario> usuarios = new ArrayList<>();
////            usuarios.add(u1);
////            usuarios.add(u2);
//
//GestorUsuarios servUsuarios= new GestorUsuarios();
//
//            if (servUsuarios.verificarUsuario("11111111","1234" )) {
//                System.out.println("Exito");
//            } else {
//                System.out.println("ERROR");
//            }
//
//        } catch (Exception e) {
//            System.out.println("ERROR");
//        }
//
//    }
//}

