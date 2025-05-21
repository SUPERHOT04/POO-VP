import datetime

class Mensagem:
    def __init__(self, mensagem):
        self.mensagem = mensagem
        self.data_envio = datetime.datetime.now()  # data atual

    def exibir(self):
        pass

    def get_tipo_mensagem(self):
        return self.__class__.__name__

class Texto(Mensagem):
    def __init__(self, mensagem):
        super().__init__(mensagem)

    def exibir(self):
        print(f"[Texto] Mensagem: {self.mensagem} | Data: {self.data_envio}")


class Video(Mensagem):
    def __init__(self, mensagem, arquivo, formato, duracao):
        super().__init__(mensagem)
        self.arquivo = arquivo
        self.formato = formato
        self.duracao = duracao

    def exibir(self):
        print(f"[Vídeo] Mensagem: {self.mensagem} | Arquivo: {self.arquivo} "
              f"| Formato: {self.formato} | Duração: {self.duracao}s | Data: {self.data_envio}")

class Foto(Mensagem):
    def __init__(self, mensagem, arquivo, formato):
        super().__init__(mensagem)
        self.arquivo = arquivo
        self.formato = formato

    def exibir(self):
        print(f"[Foto] Mensagem: {self.mensagem} | Arquivo: {self.arquivo} "
              f"| Formato: {self.formato} | Data: {self.data_envio}")

class Arquivo(Mensagem):
    def __init__(self, mensagem, arquivo, formato):
        super().__init__(mensagem)
        self.arquivo = arquivo
        self.formato = formato

    def exibir(self):
        print(f"[Arquivo] Mensagem: {self.mensagem} | Arquivo: {self.arquivo} "
              f"| Formato: {self.formato} | Data: {self.data_envio}")

class CanalDeComunicacao:
    def enviar_mensagem(self, mensagem, destino):
        pass


class WhatsApp(CanalDeComunicacao):
    def enviar_mensagem(self, mensagem, numero_telefone):
        print(f"Enviando mensagem via WhatsApp para o telefone: {numero_telefone}")
        mensagem.exibir()


class Telegram(CanalDeComunicacao):
    def enviar_mensagem(self, mensagem, destino):
        print(f"Enviando mensagem via Telegram para: {destino}")
        mensagem.exibir()


class Facebook(CanalDeComunicacao):
    def enviar_mensagem(self, mensagem, usuario):
        print(f"Enviando mensagem via Facebook para o usuário: {usuario}")
        mensagem.exibir()


class Instagram(CanalDeComunicacao):
    def enviar_mensagem(self, mensagem, usuario):
        print(f"Enviando mensagem via Instagram para o usuário: {usuario}")
        mensagem.exibir()


class CanalFactory:
    canais = {
        "WhatsApp": WhatsApp(),
        "Telegram": Telegram(),
        "Facebook": Facebook(),
        "Instagram": Instagram()
    }

    @staticmethod
    def obter_canal(tipo_canal):
        return CanalFactory.canais.get(tipo_canal)



def main():
    texto = Texto("Olá, tudo bem?")
    video = Video("Veja isso!", "video.mp4", "mp4", 120)
    foto = Foto("Confira a imagem", "foto.jpg", "jpg")
    arquivo = Arquivo("Leia o documento", "doc.pdf", "pdf")

    enviar_por_canal("WhatsApp", texto, "+551199999999")
    enviar_por_canal("Telegram", video, "usuario_telegram")
    enviar_por_canal("Facebook", foto, "usuario_facebook")
    enviar_por_canal("Instagram", arquivo, "usuario_instagram")

def enviar_por_canal(canal, mensagem, destino):
    canal_de_comunicacao = CanalFactory.obter_canal(canal)
    if canal_de_comunicacao:
        canal_de_comunicacao.enviar_mensagem(mensagem, destino)
    else:
        print(f"Canal não encontrado: {canal}")


if __name__ == "__main__":
    main()
