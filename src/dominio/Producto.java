package dominio;

import java.util.List;

public abstract class Producto implements Cloneable {
    protected int codigo;
    protected String nombre;
    protected double precio;
    protected int unidades;

    public Producto(int codigo, String nombre, double precio, int unidades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
    }

    abstract public List<Producto> leerProductos();

    abstract public void escribirProductos(List<Producto> productos);

    public Producto() {
    }

    @Override
    public Producto clone() throws CloneNotSupportedException {
        return (Producto) super.clone();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "Producto [" +

               "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ']';
    }
}
