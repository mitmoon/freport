package flow.flowreport.api.extmng.controller;

import flow.flowreport.api.extmng.dto.CustomExtDTO;
import flow.flowreport.api.extmng.service.CustomExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cust-ext")
public class CustomExtController {
    private final CustomExtService customExtService;

    @Autowired
    private CustomExtController(CustomExtService customExtService) {
        this.customExtService = customExtService;
    }

    /**
     * 커스텀 확장자 목록 조회
     */
    @GetMapping("/list")
    public List<CustomExtDTO> getCustomExts() {
        return customExtService.getCustomExts();
    }

    /**
     * 커스텀 확장자 추가
     */
    @PostMapping()
    public void addCustomExt(@RequestBody @Valid CustomExtDTO customExtDTO) {
        customExtDTO.validate();
        customExtService.addCustomExt(customExtDTO);
    }

    /**
     * 커스텀 확장자 삭제
     */
    @DeleteMapping()
    public void removeCustomExt(@RequestBody @Valid CustomExtDTO customExtDTO) {
        customExtService.removeCustomExt(customExtDTO);
    }
}
