package persistencia;

import dominio.Producto;

import java.io.PrintWriter;
import java.util.List;

public abstract class ProductoDao {

    abstract public List<Producto> leerProductos();
    abstract public void escribirProductos(List<Producto> productos);

    static void printermethod(List<Producto> productos, PrintWriter out, int i) { //metodo de escribir datos en el fichero
        out.println("Codigo:");
        out.println(productos.get(i).getCodigo());
        out.println("Nombre:");
        out.println(productos.get(i).getNombre());
        out.println("Precio:");
        out.printf("%.2f\n",productos.get(i).getPrecio());
        out.println("Unidades:");
        out.println(productos.get(i).getUnidades());
    }
}
