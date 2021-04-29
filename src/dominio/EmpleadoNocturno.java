package dominio;

import persistencia.*;

import java.util.List;

public class EmpleadoNocturno extends Empleado {

    private double plusNocturnidad;
    private final EmpleadoNocturnoDao empdao;

    public EmpleadoNocturno(int codigoAcceso, String nombreUsuario, String password, String turno, int nivel,
                            double plusNocturnidad) {
        super(codigoAcceso, nombreUsuario, password, turno, nivel);
        this.plusNocturnidad = plusNocturnidad;
        this.empdao = new EmpleadoNocturnoDao();
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
        productividad = nivel + totalPrice * (plusNocturnidad / 100);
        if (totalPrice >= 200 && nivel == 1) {
            productividad++;
        }
        return productividad;
    }

    public EmpleadoNocturno() {
        this.empdao = new EmpleadoNocturnoDao();
    }

    public double getPlusNocturnidad() {
        return plusNocturnidad;
    }

    public void setPlusNocturnidad(double plusNocturnidad) {
        this.plusNocturnidad = plusNocturnidad;
    }

    @Override
    public String toString() {
        return "EmpleadoNocturno{" +
                "codigoAcceso=" + codigoAcceso +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", turno='" + turno + '\'' +
                ", nivel=" + nivel +
                ", productividad=" + productividad +
                ", plusNocturnidad=" + plusNocturnidad +
                ", empdao=" + empdao +
                '}';
    }
}
