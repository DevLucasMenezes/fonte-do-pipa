public class SMSFactory extends NotificacaoFactory {
    @Override
    public Notificacao criarNotificacao() {
        return new SMS();
    }
}