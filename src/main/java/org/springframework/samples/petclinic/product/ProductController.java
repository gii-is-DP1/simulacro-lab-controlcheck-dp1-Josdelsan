package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService pService;

    @Autowired
    public ProductController(ProductService pService) {
        this.pService = pService;
    }

    private static final String CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";

    @GetMapping(value = "/create")
    public String initCreationForm(ModelMap model) {
        Product product = new Product();
        List<ProductType> productType = pService.getAllProductsTypes();
        model.put("product", product);
        model.put("productType", productType);
        return CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/create")
    public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<ProductType> productType = pService.getAllProductsTypes();
            model.put("product", product);
            model.put("productType", productType);
            return CREATE_OR_UPDATE_FORM;
        } else {
            pService.save(product);
            return "welcome";
        }
    }
}
