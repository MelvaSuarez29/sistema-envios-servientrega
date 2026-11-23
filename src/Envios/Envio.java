package Envios;

public class Envio {
    private String guia;
    private String destinatario;
    private String ciudadDestino;
    private double pesokg;

    public Envio(String guia, String destinatario, String ciudadDestino, double pesokg) {
        this.guia = guia;
        this.destinatario = destinatario;
        this.ciudadDestino = ciudadDestino;
        setPesokg(pesokg);
    }

    public String getGuia() {return guia;}
    public String getDestinatario() {return destinatario;}
    public String getCiudadDestino() {return ciudadDestino;}
    public double getPesokg() {return pesokg;}

    public void setGuia(String guia) {this.guia = guia;}
    public void setDestinatario(String destinatario) {this.destinatario = destinatario;}
    public void setCiudadDestino(String ciudadDestino) {this.ciudadDestino = ciudadDestino;}
    public void setPesokg(double pesokg) {
        if (pesokg > 0) {
            this.pesokg = pesokg;
        }
    }

    public int kilosFacturables(){
        int parteEntera = (int) pesokg;
        if(pesokg> parteEntera){
            return parteEntera +1;
        }
        return parteEntera;
    }
    public double calcularCosto(){
        return 2.00 * kilosFacturables();
    }
    public String describir(){
        return "Guia: "+guia
                +" | Destinatario: "+destinatario
                +" | Ciudad Destino: "+ciudadDestino
                +" | Pesokg: "+pesokg+" kg";
    }
}
