package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class sounds {

    private static Clip menuSoundClip;

    public static void playMenuSound(String soundFilePath) {
        try {
            // Carregando o arquivo de som
            File arquivoSom = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoSom);

            // Obtendo um Clip
            menuSoundClip = AudioSystem.getClip();

            // Abrindo o fluxo de áudio e carregando os dados do arquivo
            menuSoundClip.open(audioInputStream);

            // Reproduzindo o som
            menuSoundClip.start();

            // Aguardando o som terminar de ser reproduzido antes de encerrar o programa
            while (menuSoundClip.isRunning()) {
                Thread.sleep(10);
            }

            // Fechando o Clip e o fluxo de áudio
            menuSoundClip.close();
            audioInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
