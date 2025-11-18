package es.jllopezalvarez.dwes.ejemplo02solid.principio03lsp.correcto;

public class AveVoladora extends Ave {
    public void volar() {
        System.out.println("Esta ave está volando");
    }

    @Override
    void mover() {
        volar();
    }
}