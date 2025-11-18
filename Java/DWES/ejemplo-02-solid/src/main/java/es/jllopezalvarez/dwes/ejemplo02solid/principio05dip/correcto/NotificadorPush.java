package es.jllopezalvarez.dwes.ejemplo02solid.principio05dip.correcto;

// Nueva clase de bajo nivel: Envío de notificaciones push
public class NotificadorPush implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando notificación push: " + mensaje);
    }
}
