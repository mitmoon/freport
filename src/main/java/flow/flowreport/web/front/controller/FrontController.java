package flow.flowreport.web.front.controller;

import flow.flowreport.api.extmng.dto.CustomExtDTO;
import flow.flowreport.api.extmng.dto.FavoriteExtDTO;
import flow.flowreport.api.extmng.service.CustomExtService;
import flow.flowreport.api.extmng.service.FavoriteExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontController {
    private CustomExtService customExtService;
    private FavoriteExtService favoriteExtService;

    @Autowired
    private FrontController(CustomExtService customExtService, FavoriteExtService favoriteExtService) {
        this.customExtService = customExtService;
        this.favoriteExtService = favoriteExtService;
    }

    @GetMapping({"", "/"})
    public String homePage(Model model) {
        List<FavoriteExtDTO> favoriteExts = favoriteExtService.getFavoriteExtInfo();
        List<CustomExtDTO> customExts = customExtService.getCustomExts();
        long customExtCount = customExtService.getCustomExtsCount();

        model.addAttribute("favoriteExts", favoriteExts);
        model.addAttribute("customExts", customExts);
        model.addAttribute("customExtCount", customExtCount);

        return "/front/home";
    }
}
