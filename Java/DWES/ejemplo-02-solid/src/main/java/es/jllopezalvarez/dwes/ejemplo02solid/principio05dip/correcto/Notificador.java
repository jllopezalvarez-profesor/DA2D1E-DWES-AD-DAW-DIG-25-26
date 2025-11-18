package es.jllopezalvarez.dwes.ejemplo02solid.principio05dip.correcto;

/**
 * Abstracción: cualquier notificador debe implementar este comportamiento
 */

public interface Notificador {
    void enviar(String mensaje);
}