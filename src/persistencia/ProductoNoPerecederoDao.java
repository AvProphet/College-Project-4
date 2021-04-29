package persistencia;

import dominio.Producto;
import dominio.ProductoNoPerecedero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoNoPerecederoDao extends ProductoDao{

    public ProductoNoPerecederoDao() {
    }

    @Override
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<Producto>();
        Scanner in;
        try {
            in = new Scanner(new FileReader("productosNoPerecederos.txt"));
            in.next();
            int contador = in.nextInt();
            // read
            for (int i = 0; i < contador; i++) {
                in.next();
                int codigo = in.nextInt();
                in.next();
                in.nextLine();
                String nombre = in.nextLine();
                in.next();
                double precio = in.nextDouble();
                in.next();
                int unidades = in.nextInt();
                in.next();
                int idOferta = in.nextInt();
                Producto prod = new ProductoNoPerecedero(codigo, nombre, precio, unidades, idOferta);
                productos.add(prod);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error desconocido en ProdNoPerDao");
        }
        return productos;
    }


    @Override
    public void escribirProductos(List<Producto> productos) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("productosNoPerecederos.txt"));
            out.println("Productos:");
            out.println(productos.size());
            for (int i = 0; i < productos.size(); i++) {
                ProductoNoPerecederoDao.printermethod(productos, out, i); //metodo de escribir datos en el fichero
                out.println("idOferta:");
                if (((ProductoNoPerecedero) productos.get(i)).getIdOferta() == 0) {
                    out.println("000");
                } else {
                    out.println(((ProductoNoPerecedero) productos.get(i)).getIdOferta());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("No file has found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
