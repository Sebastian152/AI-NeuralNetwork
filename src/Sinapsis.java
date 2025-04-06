
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastián G.
 */
class Sinapsis implements Serializable {
    private static final long serialVersionUID = 7597241645279827278L;
    public Neurona inicio, fin;
    public double peso;
    public double deltaWeight = 0.0;

    public Sinapsis(Neurona inicio, Neurona fin, double peso) {
        this.inicio = inicio;
        this.fin = fin;
        this.peso = peso;
    }
}
