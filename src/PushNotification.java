public class PushNotification implements Notificacao {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("[PUSH] Enviando notificação Push com a mensagem: " + mensagem);
    }
}