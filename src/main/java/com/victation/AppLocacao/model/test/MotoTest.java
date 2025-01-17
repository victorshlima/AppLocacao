package com.victation.AppLocacao.model.test;

import com.victation.AppLocacao.model.domain.Moto;
import com.victation.AppLocacao.model.domain.Usuario;
import com.victation.AppLocacao.model.repository.UsuarioRepository;
import com.victation.AppLocacao.service.MotoService;
import com.victation.AppLocacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@Component
@Order(7)
public class MotoTest implements ApplicationRunner {

    @Autowired
    private MotoService motoService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        String dir = "//home//wid_vlima//dev//git_study//infnet//APP-Locacao//dev//";
        String arq = "automoveis.txt";
        String fileName = dir + arq;
        System.out.println(fileName);

        Usuario usuario = usuarioRepository.findById(1).get();
        usuarioService.incluir(usuario);

        try {
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader leitura = new BufferedReader(fileReader);

                String linha = null;
                while ((linha = leitura.readLine()) != null) {
                    String[] campos = linha.split(";");

                    if ("M".equals(campos[0])){

                        try {
                            Moto moto = new Moto(
                                    Integer.valueOf(campos[1]),
                                    Integer.valueOf(campos[2]),
                                    campos[4]
                            );
                            moto.setUsuario(usuario);
                            motoService.incluir(moto, usuario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
                System.out.println(leitura.readLine());
                fileReader.close();
                leitura.close();
            } catch (FileNotFoundException e) {
                System.out.println("[ERRO] O arquivo não existe!!!");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("[ERRO] erro ao fechar o reader");
            }
        } catch (Exception e) {
        } finally {
            System.out.println("terminou");
        }

    }


}
