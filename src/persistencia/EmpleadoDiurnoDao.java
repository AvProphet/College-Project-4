package persistencia;

import dominio.Empleado;
import dominio.EmpleadoDiurno;

import java.io.*;
import java.util.*;

public class EmpleadoDiurnoDao extends EmpeladoDao {

    public EmpleadoDiurnoDao() {
    }

    @Override
    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<Empleado>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("empleadosDiurnos.txt"));
            in.next();
            int contador = in.nextInt();
            // read
            for (int i = 0; i < contador; i++) {
                in.next();
                in.nextLine();
                String nombre = in.nextLine();
                in.next();
                int codigo = in.nextInt();
                in.next();
                String password = in.next();
                in.next();
                int nivel = in.nextInt();
                in.next();
                String turno = in.next();
                in.next();
                double retencion = Double.parseDouble(in.next());
                Empleado emp = new EmpleadoDiurno(codigo, nombre, password, turno, nivel, retencion);
                empleados.add(emp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error desconocido en empleadosDiurnDao");
        }
        return empleados;
    }

    @Override
    public void escribirEmpleados(List<Empleado> empleados) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("empleadosDiurnos.txt"));
            out.println("Empleados:");
            out.println(empleados.size());
            for (int i = 0; i < empleados.size(); i++) {
                EmpleadoDiurnoDao.printermethod(empleados, out, i); //metodo de escribir datos en el fichero
                out.println("Retencion:");
                out.println(((EmpleadoDiurno) empleados.get(i)).getRetencion());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("No file has found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
