package dominio;

import persistencia.ProductoPerecederoDao;

import java.util.List;

public class ProductoPerecedero extends Producto{

    private int vidaProducto;
    private final ProductoPerecederoDao prodDao;

    public ProductoPerecedero(int codigo, String nombre, double precio, int unidades, int vidaProducto) {
        super(codigo, nombre, precio, unidades);
        this.vidaProducto = vidaProducto;
        this.prodDao = new ProductoPerecederoDao();
    }

    public ProductoPerecedero() {
        this.prodDao = new ProductoPerecederoDao();
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
    public ProductoPerecedero clone() throws CloneNotSupportedException {
        return (ProductoPerecedero) super.clone();
    }

    public double priceCountPerecederos() { //Calculamos precio de perecederos
        double newPrice = precio;
        if (vidaProducto == 1) {
            newPrice = precio*0.25;
            return newPrice;
        } else if (vidaProducto == 2) {
            newPrice = precio*0.66;
            return newPrice;
        } else if (vidaProducto == 3) {
            newPrice = precio*0.5;
            return newPrice;
        }
        return newPrice;
    }

    public ProductoPerecedero(ProductoPerecederoDao prodDao) {
        this.prodDao = new ProductoPerecederoDao();
    }

    public int getVidaProducto() {
        return vidaProducto;
    }

    public void setVidaProducto(int vidaProducto) {
        this.vidaProducto = vidaProducto;
    }

    @Override
    public String toString() {
        return String.format("[" +
                "codigo=" + codigo +
                ", nombre=" + nombre + '\'' +
                ", precio=%.2f]",precio);
    }
}
