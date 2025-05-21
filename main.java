import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract class Mensagem {
    protected String mensagem;
    protected Date dataEnvio;

    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
        this.dataEnvio = new Date(); // data atual
    }

    public abstract void exibir();

    public String getTipoMensagem() {
        return this.getClass().getSimpleName();
    }
}

class Texto extends Mensagem {
    public Texto(String mensagem) {
        super(mensagem);
    }

    @Override
    public void exibir() {
        System.out.println("[Texto] Mensagem: " + mensagem + " | Data: " + dataEnvio);
    }
}

class Video extends Mensagem {
    private String arquivo;
    private String formato;
    private int duracao; // segundos

    public Video(String mensagem, String arquivo, String formato, int duracao) {
        super(mensagem);
        this.arquivo = arquivo;
        this.formato = formato;
        this.duracao = duracao;
    }

    @Override
    public void exibir() {
        System.out.println("[Vídeo] Mensagem: " + mensagem + " | Arquivo: " + arquivo +
                           " | Formato: " + formato + " | Duração: " + duracao + "s" +
                           " | Data: " + dataEnvio);
    }
}

class Foto extends Mensagem {
    private String arquivo;
    private String formato;

    public Foto(String mensagem, String arquivo, String formato) {
        super(mensagem);
        this.arquivo = arquivo;
        this.formato = formato;
    }

    @Override
    public void exibir() {
        System.out.println("[Foto] Mensagem: " + mensagem + " | Arquivo: " + arquivo +
                           " | Formato: " + formato + " | Data: " + dataEnvio);
    }
}

class Arquivo extends Mensagem {
    private String arquivo;
    private String formato;

    public Arquivo(String mensagem, String arquivo, String formato) {
        super(mensagem);
        this.arquivo = arquivo;
        this.formato = formato;
    }

    @Override
    public void exibir() {
        System.out.println("[Arquivo] Mensagem: " + mensagem + " | Arquivo: " + arquivo +
                           " | Formato: " + formato + " | Data: " + dataEnvio);
    }
}

interface CanalDeComunicacao {
    void enviarMensagem(Mensagem mensagem, String destino);
}

class WhatsApp implements CanalDeComunicacao {
    public void enviarMensagem(Mensagem mensagem, String numeroTelefone) {
        System.out.println("Enviando mensagem via WhatsApp para o telefone: " + numeroTelefone);
        mensagem.exibir();
    }
}

class Telegram implements CanalDeComunicacao {
    public void enviarMensagem(Mensagem mensagem, String destino) {
        System.out.println("Enviando mensagem via Telegram para: " + destino);
        mensagem.exibir();
    }
}

class Facebook implements CanalDeComunicacao {
    public void enviarMensagem(Mensagem mensagem, String usuario) {
        System.out.println("Enviando mensagem via Facebook para o usuário: " + usuario);
        mensagem.exibir();
    }
}

class Instagram implements CanalDeComunicacao {
    public void enviarMensagem(Mensagem mensagem, String usuario) {
        System.out.println("Enviando mensagem via Instagram para o usuário: " + usuario);
        mensagem.exibir();
    }
}

class CanalFactory {
    private static Map<String, CanalDeComunicacao> canais = new HashMap<>();

    static {
        canais.put("WhatsApp", new WhatsApp());
        canais.put("Telegram", new Telegram());
        canais.put("Facebook", new Facebook());
        canais.put("Instagram", new Instagram());
    }

    public static CanalDeComunicacao obterCanal(String tipoCanal) {
        return canais.get(tipoCanal);
    }
}

public class Main {
    public static void main(String[] args) {
        Mensagem texto = new Texto("Olá, tudo bem?");
        Mensagem video = new Video("Veja isso!", "video.mp4", "mp4", 120);
        Mensagem foto = new Foto("Confira a imagem", "foto.jpg", "jpg");
        Mensagem arquivo = new Arquivo("Leia o documento", "doc.pdf", "pdf");

        enviarPorCanal("WhatsApp", texto, "+551199999999");
        enviarPorCanal("Telegram", video, "usuario_telegram");
        enviarPorCanal("Facebook", foto, "usuario_facebook");
        enviarPorCanal("Instagram", arquivo, "usuario_instagram");
    }

    private static void enviarPorCanal(String canal, Mensagem mensagem, String destino) {
        CanalDeComunicacao canalDeComunicacao = CanalFactory.obterCanal(canal);
        if (canalDeComunicacao != null) {
            canalDeComunicacao.enviarMensagem(mensagem, destino);
        } else {
            System.out.println("Canal não encontrado: " + canal);
        }
    }
}
