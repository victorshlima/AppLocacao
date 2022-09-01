package com.victation.AppLocacao.controller;

import com.victation.AppLocacao.model.domain.Cliente;
import com.victation.AppLocacao.model.domain.Locacao;
import com.victation.AppLocacao.model.test.AppImpressao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LocatarioController {

    private static Map<String, Cliente> mapaCliente = new HashMap<String, Cliente>();
    private static Integer id =1;

    public static void incluir(Cliente cliente){
        mapaCliente.put(cliente.getEmail(), cliente);
        AppImpressao.relatorio("\n Locacao " + cliente.getNome() + " incuido com sucesso", cliente  );
    }
    public static Collection<Cliente> obterLista(){
        return mapaCliente.values();
    }

    @GetMapping("/locatario/lista")
    public String getindex (HttpServletRequest request){
        return "/locatario/lista";
    }




}
