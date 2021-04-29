package persistencia;

import dominio.Empleado;

import java.io.PrintWriter;
import java.util.List;

public abstract class EmpeladoDao {

    abstract public List<Empleado> leerEmpleados();
    abstract public void escribirEmpleados (List<Empleado> empleados);

    static void printermethod(List<Empleado> empleados, PrintWriter out, int i) { //metodo de escribir datos en el fichero
        out.println("Nombre:");
        out.println(empleados.get(i).getNombreUsuario());
        out.println("Codigo:");
        out.println(empleados.get(i).getCodigoAcceso());
        out.println("Password:");
        out.println(empleados.get(i).getPassword());
        out.println("Nivel:");
        out.println(empleados.get(i).getNivel());
        out.println("Turno:");
        out.println(empleados.get(i).getTurno());
    }
}
