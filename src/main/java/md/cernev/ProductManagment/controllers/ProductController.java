package md.cernev.ProductManagment.controllers;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.service.ProductService;
import md.cernev.ProductManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @GetMapping("/products/{login}")
//    @PreAuthorize("#login == principal.username")
    public String listProduct(@PathVariable("login") String login, Model model) {
        Optional<User> optionalUser;
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.getLogin().equals(login)) {
            optionalUser = Optional.of(new User());
            model.addAttribute("error", "Access denied!" + " <a href=\"/products/" + principal.getLogin() + "\" class=\"btn btn-success\">Go back</a>");
        } else {
            optionalUser = userService.getUser(login);
            model.addAttribute("products", productService.listProducts());
        }
        if (!optionalUser.isPresent()) {
            model.addAttribute("error", "No such user!");
        } else {
            model.addAttribute("user", optionalUser.get());
        }
        return "personalProducts";
    }

    @GetMapping("/buy/{userId}/{productId}")
    public String buyProduct(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId, Model model) {
        boolean buy = productService.buy(userId, productId);
        return "redirect:/products/" + userService.getUser(userId).get().getLogin();
    }


    @GetMapping("/product")
    public String addProduct(Model model) {
        model.addAttribute("productForm", new Product());
        return "product";
    }

    @PostMapping("/product")
    public String createProduct(@ModelAttribute("productForm") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/";
        }
        if (productService.isExist(product)) {
            model.addAttribute("nameError", "Product with such name already exists.");
            return "/product";
        }
        if (product.getPrice() < 0 || product.getPrice() > 1000) {
            model.addAttribute("priceError", "Invalid price (should be more than 0)");
            return "/product";
        }
        if (product.getStock() < 0) {
            model.addAttribute("stockError", "Invalid stock (should be more than 0)");
            return "/product";
        }
        productService.saveProduct(product);

        return "/product";

    }
}
