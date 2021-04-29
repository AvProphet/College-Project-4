package dominio;

import persistencia.OfertaDao;

import java.util.List;

public class Oferta {
    private int idOferta;
    private String tipoOferta;
    private int tantoPorciento;
    private int maximo;
    private OfertaDao ofDao;

    public Oferta() {
        this.ofDao = new OfertaDao();
    }

    public Oferta(int idOferta, String tipoOferta) {
        this.idOferta = idOferta;
        this.tipoOferta = tipoOferta;
        this.ofDao = new OfertaDao();
    }

    public Oferta(int idOferta, String tipoOferta, int tantoPorciento, int maximo) {
        this.idOferta = idOferta;
        this.tipoOferta = tipoOferta;
        this.tantoPorciento = tantoPorciento;
        this.maximo = maximo;
        this.ofDao = new OfertaDao();
    }

    public void escribirOfertas(List<Oferta> ofertas) {
        this.ofDao.escribirOfertas(ofertas);
    }

    public List<Oferta> leerOfertas() {
        return this.ofDao.leerOfertas();
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public int getTantoPorciento() {
        return tantoPorciento;
    }

    public void setTantoPorciento(int tantoPorciento) {
        this.tantoPorciento = tantoPorciento;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }
}
