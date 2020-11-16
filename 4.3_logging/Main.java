import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    /*
    * Класс, моделирующий ненадежного работника почты,<br>
    * который вместо того, чтобы передать почтовый объект непосредственно в сервис почты,<br>
    * последовательно передает этот объект набору третьих лиц, а затем, <br>
    * в конце концов, передает получившийся объект непосредственно экземпляру <code>RealMailService</code>
     */
        public static class UntrustworthyMailWorker implements MailService {
            public MailService[] mailServicesInClass;

            RealMailService realMailService = new RealMailService();

            public UntrustworthyMailWorker (MailService[] mailServicesInConstruct) {
                mailServicesInClass = mailServicesInConstruct;
            }

            public RealMailService getRealMailService () {
                return this.realMailService;
            }

            @Override
            public Sendable processMail (Sendable mail) {
                for (int i = 0; i < mailServicesInClass.length-1; i++) {
                    //результат вызова processMail первого элемента массива
                    //передается на вход processMail второго элемента, и т. д.
                    mailServicesInClass[i + 1].processMail(mailServicesInClass[i].processMail(mail));
                }
                return realMailService.processMail(mailServicesInClass[mailServicesInClass.length-1].processMail(mail));
            }
        }

        /*
        * Шпион, который логгирует о всей почтовой переписке, которая проходит через его руки
         */
        public static class Spy implements MailService {
            public Logger LOGGER;

            public Spy (Logger LOGGER) {
                this.LOGGER = LOGGER;
            }

            //new logging level for spy class
            public static class SpyLoggerLevel extends Level {
                public SpyLoggerLevel (String name, int value) {
                    super(name, value);
                }
            }

            @Override
            public Sendable processMail (Sendable mail) {
                //шпион следит только за объектами класса MailMessage
                if (mail instanceof MailMessage) {
                    MailMessage mailMessage = (MailMessage) mail;
                    if (mailMessage.getFrom().equals(AUSTIN_POWERS) || mailMessage.getTo().equals(AUSTIN_POWERS)) {
                        LOGGER.log(new SpyLoggerLevel("WARN", 1000),
                                "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                                new Object[] {mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
                    } else {
                        LOGGER.log(new SpyLoggerLevel("INFO", 900),
                                "Usual correspondence: from {0} to {1}",
                                new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
                    }
                }
                return mail;
            }
        }

        /*
        * Вор, который ворует самые ценные посылки и игнорирует все остальное.
         */
        public static class Thief implements MailService {
            public int minPackageCost;
            public int stolenValue = 0;

            public Thief(int minPackageCost) {
                this.minPackageCost = minPackageCost;
            }

            //суммарная стоимость украденных посылок
            public int getStolenValue() {
                return stolenValue;
            }

            @Override
            public Sendable processMail(Sendable mail) {
                //вор крадет только посылки
                if (mail instanceof MailPackage) {
                    MailPackage mailPackage = (MailPackage) mail;
                    if (mailPackage.getContent().getPrice() >= minPackageCost) {
                        //вместо посылки, которая пришла вору, он отдает новую,
                        //такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".
                        MailPackage fakePackage = new MailPackage(mail.getFrom(), mail.getTo(),
                                new Package("stones instead of " + mailPackage.getContent().getContent(), 0));
                        //суммируем стоимость украденной посылки
                        stolenValue += mailPackage.getContent().getPrice();
                        return fakePackage;
                    }
                }
                return mail;
            }

        }

    /**
     * Исключение для запрещенных посылок.
      */
    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {

        }
    }

    /**
     * Исключение для украденных посылок.
     */
    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {

        }
    }

    /*
    * Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения, <br>
    * если была обнаружена подобная посылка.
     */
    public static class Inspector implements MailService {
//        public Inspector() {
//
//        }

        @Override
        public Sendable processMail (Sendable mail) {
            //инспектор следит за посылками
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                //если обнаружена посылка с запрещенным содержимым, бросить исключение
                if (mailPackage.getContent().getContent().contains(WEAPONS) || mailPackage.getContent().getContent().contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                //если обнаружена украденная послыка, бросить исключение
                if (mailPackage.getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static void main(String[] args) {
        UntrustworthyMailWorker mailWorker = new UntrustworthyMailWorker(
                new MailService[] {new Spy(Logger.getLogger(Spy.class.getName())),
                        new Thief(40),
                new Inspector()});
        //test spy
        mailWorker.processMail(new MailMessage(AUSTIN_POWERS,"1","simple message"));
        //test thief
//        mailWorker.processMail(new MailPackage(AUSTIN_POWERS,"1",new Package("simple package", 50)));
        //test inspector
        mailWorker.processMail(new MailPackage(AUSTIN_POWERS,"1",new Package(BANNED_SUBSTANCE,10)));
    }
}
