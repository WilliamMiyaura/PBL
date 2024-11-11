package br.edu.cefsa.macacarefa.controller;
/**
 *
 * @author willi
 */
import br.edu.cefsa.macacarefa.model.Ape;
import br.edu.cefsa.macacarefa.repository.ApeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ape")

public class ApeController {
    @Autowired
    private ApeRepository apeRepository;

    @GetMapping
    public String listarApes(Model model) {
        List<Ape> apes = apeRepository.findAll();
        model.addAttribute("ape", apes);
        return "ape";
    }

    @PostMapping("/adicionar")
    public String adicionarApe(Ape ape) {
        apeRepository.save(ape);
        return "redirect:/ape";
    }

    @PostMapping("/editar")
public String editarApe(Ape ape) {
    Ape apeExistente = apeRepository.findById(ape.getId()).orElseThrow(() -> new RuntimeException("Ape não encontrado"));
    
    // Caso a senha seja nula, mantenha a senha anterior
    if (ape.getPassword() == null) {
        ape.setPassword(apeExistente.getPassword());
    }
    

    apeRepository.save(ape);
    return "redirect:/ape";  // Redireciona para a lista de Apes após a edição
}


    
   @GetMapping("/editarApe/{email}")
public String editarApe(@PathVariable String email, Model model) {
    Ape ape = apeRepository.findByEmail(email);
                           
    model.addAttribute("ape", ape);
    return "editarApe";  
}



   
}
