package api;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class crud {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion acceso = new conexion();


    public ArrayList listar(){
        String sql = "Select * from estudiante";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<alumnos> datos = new ArrayList<alumnos>();

            while(rs.next()){
                alumnos al = new alumnos();
                al.setCodigo(rs.getInt(1));
                al.setNombre(rs.getString(2));
                al.setApellido(rs.getString(3));
                al.setEdad(rs.getInt(4));
                al.setNotaProm(rs.getDouble(5));
                datos.add(al);
                
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+
                        rs.getDouble(5));
               
            }
            return datos;
        }catch(Exception e){System.out.println(e);}
        return null;
    }

    public void crear (String nombre, String apellido, int edad, double nota){
        String sql = "Insert into estudiante(nombre,apellido,edad,nota) values (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setDouble(4, nota);
            ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
    }
    
    public void modificar(int codigo, String nombre, String apellido, int edad, double nota){
        String sql = "update estudiante set nombre = ?, apellido = ?, edad = ?, nota = ? where id = ?; ";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setDouble(4, nota);
            ps.setInt(5, codigo);
            ps.executeUpdate();
            
        }catch(Exception e){System.out.println(e);}
    }
    
    public void eliminar(int codigo){
        String sql = "delete from estudiante where id = ?";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        
    }
    
    public static void main(String[] args) {
        crud cr = new crud();
        Scanner hd = new Scanner(System.in);
        Scanner st = new Scanner (System.in);
        Scanner db = new Scanner (System.in);
        do{
            System.out.println("=====MENU PRINCIPAL=====");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Actualizar datos");
            System.out.println("4. Eliminar");
            System.out.println("5. Salir");
            System.out.println("Eija uns opcion: ");
            int opcion = hd.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("======Crear estudiante======");
                    System.out.println("Nombre: ");
                    String nombre = st.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = st.nextLine();
                    System.out.println("Edad: ");
                    int edad = hd.nextInt();
                    System.out.println("Nota Promedio: ");
                    double nota = db.nextDouble();
                    cr.crear(nombre, apellido, edad, nota);
                    break;
                    
                case 2:
                    System.out.println("=====mostrar Estudiantes======");
                    cr.listar();
                    break;
                    
                case 3:
                    System.out.println("======Actualizar datos======");
                    System.out.println("Ingrese el codigo del estudiante que desea modificar: ");
                    int id = hd.nextInt();
                    System.out.println("Nuevo Nombre: ");
                    String nombrem = st.nextLine();
                    System.out.println("Nuevo apellido: ");
                    String apellidom = st.nextLine();
                    System.out.println("Nueva edad: ");
                    int edadm = hd.nextInt();
                    System.out.println("Nueva nota promedio: ");
                    double notprom = db.nextDouble();
                    cr.modificar(id, nombrem, apellidom, edadm , notprom);
                    System.out.println("ESTUDIANTE MODIFICADO");
                    break;
                    
                case 4:
                    System.out.println("=====Eliminar estudiante=====");
                    System.out.println("Ingrese codigo del estudiante a eliminar: ");
                    int codeli = hd.nextInt();
                    cr.eliminar(codeli);
                    System.out.println("ESTUDIANTE ELIMINADO");
                    break;
                    
                case 5:
                    System.exit(0);
                    
            }
            
        }while(true);
           
    }
}
