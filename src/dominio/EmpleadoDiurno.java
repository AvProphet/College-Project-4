package dominio;

import persistencia.*;

import java.util.List;

public class EmpleadoDiurno extends Empleado {

    private double retencion;
    private final EmpleadoDiurnoDao empdao;

    public EmpleadoDiurno(int codigoAcceso, String nombreUsuario, String password, String turno, int nivel,
                          double retencion) {
        super(codigoAcceso, nombreUsuario, password, turno, nivel);
        this.retencion = retencion;
        this.empdao = new EmpleadoDiurnoDao();
    }

    @Override
    public List<Empleado> leerEmpleados() {
        return this.empdao.leerEmpleados();
    }

    @Override
    public void escribirEmpleados(List<Empleado> empleados) {
        this.empdao.escribirEmpleados(empleados);
    }

    @Override
    public double productivityCounter(double totalPrice) { //calculamos productividad
        if (nivel == 1) {
            productividad++;
        } else if (totalPrice >= 300 && nivel == 2) {
            productividad += nivel;
        }  else {
            productividad = nivel - nivel * retencion / 100;
        }
        return productividad;
    }

    public EmpleadoDiurno() {
        this.empdao = new EmpleadoDiurnoDao();
    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }

    @Override
    public String toString() {
        return "EmpleadoDiurno{" +
                "codigoAcceso=" + codigoAcceso +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", turno='" + turno + '\'' +
                ", nivel=" + nivel +
                ", productividad=" + productividad +
                ", retencion=" + retencion +
                ", empdao=" + empdao +
                '}';
    }
}
