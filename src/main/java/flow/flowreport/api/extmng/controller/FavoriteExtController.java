package flow.flowreport.api.extmng.controller;

import flow.flowreport.api.extmng.dto.FavoriteExtDTO;
import flow.flowreport.api.extmng.service.FavoriteExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favi-ext")
public class FavoriteExtController {
    private final FavoriteExtService favoriteExtService;

    @Autowired
    public FavoriteExtController(FavoriteExtService favoriteExtService) {
        this.favoriteExtService = favoriteExtService;
    }

    /**
     * 고정확장자 목록 조회
     * @return
     */
    @GetMapping("/list")
    public List<FavoriteExtDTO> getFavoriteExtInfo() {
        return favoriteExtService.getFavoriteExtInfo();
    }

    /**
     * 고정 확장자 수정 (upsert)
     * @param favoriteExts
     */
    @PutMapping("/list")
    public void modifyFavoriteExtInfo(@RequestBody List<FavoriteExtDTO> favoriteExts) {
        favoriteExtService.modifyFavoriteExtInfo(favoriteExts);
    }
}
