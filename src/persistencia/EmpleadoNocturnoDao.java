package persistencia;

import dominio.Empleado;
import dominio.EmpleadoNocturno;

import java.io.*;
import java.util.*;

public class EmpleadoNocturnoDao extends EmpeladoDao {

    public EmpleadoNocturnoDao() {
    }

    @Override
    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<Empleado>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("empleadosNocturnos.txt"));
            in.next();
            int contador = in.nextInt();
            // read
            for (int i = 0; i < contador; i++) {
                in.next();
                in.nextLine();
                String nombre=in.nextLine();
                in.next();
                int codigo=in.nextInt();
                in.next();
                String password=in.next();
                in.next();
                int nivel=in.nextInt();
                in.next();
                String turno=in.next();
                in.next();
                double plusNocturnidad = Double.parseDouble(in.next());
                Empleado emp = new EmpleadoNocturno(codigo, nombre, password, turno, nivel, plusNocturnidad);
                empleados.add(emp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error desconocido en empleadosNoctDao");
        }
        return empleados;
    }

    @Override
    public void escribirEmpleados(List<Empleado> empleados) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("empleadosNocturnos.txt"));
            out.println("Empleados:");
            out.println(empleados.size());
            for (int i = 0; i < empleados.size(); i++) {
                EmpleadoDiurnoDao.printermethod(empleados, out, i); //metodo de escribir datos en el fichero
                out.println("PlusProductividad:");
                out.println(((EmpleadoNocturno)empleados.get(i)).getPlusNocturnidad());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("No file has found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
