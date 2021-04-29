package persistencia;

import dominio.Oferta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OfertaDao {

    public OfertaDao() {
    }

    public List<Oferta> leerOfertas() {
        List<Oferta> ofertas = new ArrayList<Oferta>();
        Scanner in;
        try {
            in = new Scanner(new FileReader("ofertas.txt"));
            in.next();
            int contador = in.nextInt();
            for (int i = 0; i < contador; i++) {
                in.next();
                int idOferta = in.nextInt();
                in.next();
                in.nextLine();
                String tipoOferta = in.next();
                if (tipoOferta.equals("porcentaje")) {
                    in.next();
                    int tantoPorciento = in.nextInt();
                    in.next();
                    in.nextLine();
                    int maximo = in.nextInt();
                    Oferta offer = new Oferta(idOferta, tipoOferta, tantoPorciento, maximo);
                    ofertas.add(offer);
                } else {
                    Oferta offer1 = new Oferta(idOferta, tipoOferta);
                    ofertas.add(offer1);
                }
            }
            // read
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error desconocido en ofertasDao");
        }
        return ofertas;
    }

    public void escribirOfertas(List<Oferta> ofertas) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("ofertas.txt"));
            out.println("Ofertas:");
            out.println(ofertas.size());
            for (int i = 0; i < ofertas.size(); i++) {
                out.println("idOferta");
                out.println(ofertas.get(i).getIdOferta());
                out.println("tipoOferta:");
                out.println(ofertas.get(i).getTipoOferta());
                if (ofertas.get(i).getTipoOferta().equals("porcentaje")) {
                    out.println("tantoPorcientos");
                    out.println(ofertas.get(i).getTantoPorciento());
                    out.println("maximo");
                    out.println(ofertas.get(i).getMaximo());
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
