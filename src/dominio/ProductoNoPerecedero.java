package dominio;

import persistencia.ProductoNoPerecederoDao;

import java.util.List;

public class ProductoNoPerecedero extends Producto implements Cloneable {

    private int idOferta;
    private final ProductoNoPerecederoDao prodDao;

    public ProductoNoPerecedero(int codigo, String nombre, double precio, int unidades, int idOferta) {
        super(codigo, nombre, precio, unidades);
        this.idOferta = idOferta;
        this.prodDao = new ProductoNoPerecederoDao();
    }

    public ProductoNoPerecedero() {
        this.prodDao = new ProductoNoPerecederoDao();
    }

    @Override
    public List<Producto> leerProductos() {
        return this.prodDao.leerProductos();
    }

    @Override
    public void escribirProductos(List<Producto> productos) {
        this.prodDao.escribirProductos(productos);
    }

    @Override
    public ProductoNoPerecedero clone() throws CloneNotSupportedException {
        return (ProductoNoPerecedero) super.clone();
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public String toString() {
        return String.format("[" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                ", precio=%.2f]", precio);
    }
}
