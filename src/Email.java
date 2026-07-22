public class Email implements Notificacao {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("[E-MAIL] Enviando e-mail com a mensagem: " + mensagem);
    }
}