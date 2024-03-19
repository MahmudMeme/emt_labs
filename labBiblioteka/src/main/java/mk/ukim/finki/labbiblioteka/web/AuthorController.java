package mk.ukim.finki.labbiblioteka.web;

import mk.ukim.finki.labbiblioteka.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/authors"})
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private String getAllAuthors(Model model) {
        model.addAttribute("bodyContent", "authorsList");
        model.addAttribute("authors", authorService.listAuthors());
        return "master-template";
    }
}
