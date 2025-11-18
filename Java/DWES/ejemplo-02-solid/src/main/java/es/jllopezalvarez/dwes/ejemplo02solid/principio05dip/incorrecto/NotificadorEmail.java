package es.jllopezalvarez.dwes.ejemplo02solid.principio05dip.incorrecto;

/**
 * Módulo de bajo nivel: clase que manda mensajes por email
 */
public class NotificadorEmail {
    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}