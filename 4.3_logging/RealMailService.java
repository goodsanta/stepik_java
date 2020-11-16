/*
Класс, в котором скрыта логика настоящей почты
*/
public class RealMailService implements MailService {
    @Override
    public Sendable processMail (Sendable mail) {
        return mail;
    }
}
