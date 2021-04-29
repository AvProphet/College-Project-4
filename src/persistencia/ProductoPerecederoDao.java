package persistencia;

import dominio.Producto;
import dominio.ProductoPerecedero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoPerecederoDao extends ProductoDao implements Cloneable{

    public ProductoPerecederoDao() {
    }

    @Override
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<Producto>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("productosPerecederos.txt"));
            in.next();
            int contador = in.nextInt();
            // read
            for (int i = 0; i < contador; i++) {
                in.next();
                in.nextLine();
                int codigo = in.nextInt();
                in.next();
                String nombre = in.next();
                in.next();
                double precio = in.nextDouble();
                in.next();
                int unidades = in.nextInt();
                in.next();
                int vidaProducto = in.nextInt();
                Producto prod = new ProductoPerecedero(codigo, nombre, precio, unidades, vidaProducto);
                productos.add(prod);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error desconocido en ProdPerDao");
        }
        return productos;
    }

    @Override
    public void escribirProductos(List<Producto> productos) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("productosPerecederos.txt"));
            out.println("Productos:");
            out.println(productos.size());
            for (int i = 0; i < productos.size(); i++) {
                ProductoPerecederoDao.printermethod(productos, out, i); //metodo de escribir datos en el fichero
                out.println("vidaProducto:");
                out.println(((ProductoPerecedero)productos.get(i)).getVidaProducto());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("No file has found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
