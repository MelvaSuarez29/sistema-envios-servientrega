package Envios;

public class EnvioInternacional extends Envio{
    private double valorFobUsd;

    public EnvioInternacional(String guia, String destinatario, String ciudadDestino, double pesokg, double valorFobUsd) {
        super(guia, destinatario, ciudadDestino, pesokg);
        this.valorFobUsd = valorFobUsd;
    }
    public double getValorFobUsd() { return valorFobUsd; }
    public void setValorFobUsd(double valorFobUsd) {  this.valorFobUsd = valorFobUsd; }

    public boolean cumple4x4(){
        return getPesokg() <= 4.00 && valorFobUsd <= 4.00;
    }

    @Override
    public double calcularCosto() {
        return 6.00 * kilosFacturables() + 20.00;
    }

    @Override
    public String describir() {
        return super.describir()+
                " |Valor FOB: USD "+valorFobUsd;
    }
}
