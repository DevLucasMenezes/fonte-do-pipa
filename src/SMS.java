public class SMS implements Notificacao {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("[SMS] Enviando torpedo SMS com a mensagem: " + mensagem);
    }
}