package dominio;

import java.util.List;

public abstract class Empleado {
    protected int codigoAcceso;
    protected String nombreUsuario;
    protected String password;
    protected String turno;
    protected int nivel;
    protected double productividad = 0;

    public Empleado(int codigoAcceso, String nombreUsuario, String password, String turno, int nivel) {
        this.codigoAcceso = codigoAcceso;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.turno = turno;
        this.nivel = nivel;
    }

    public Empleado() {
    }

    abstract public List<Empleado> leerEmpleados();

    abstract public void escribirEmpleados(List<Empleado> empleados);

    abstract public double productivityCounter(double totalPrice); //calculamos productividad

    public int getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(int codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getProductividad() {
        return productividad;
    }

    public void setProductividad(double productividad) {
        this.productividad = productividad;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "codigoAcceso=" + codigoAcceso +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", turno='" + turno + '\'' +
                ", nivel=" + nivel +
                ", productividad=" + productividad +
                '}';
    }
}
