package flow.flowreport.api.extmng.service;

import flow.flowreport.api.exception.ApiException;
import flow.flowreport.api.extmng.dto.CustomExtDTO;
import flow.flowreport.api.extmng.entity.CustomExt;
import flow.flowreport.api.extmng.repository.CustomExtRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomExtService {
    private final CustomExtRepository customExtRepository;

    @Autowired
    public CustomExtService(CustomExtRepository customExtRepository) {
        this.customExtRepository = customExtRepository;
    }

    public List<CustomExtDTO> getCustomExts() {
        List<CustomExt> customExts = customExtRepository.findAll();

        return customExts.stream()
                .map(customExt -> CustomExtDTO.toDTO(customExt))
                .collect(Collectors.toList());
    }

    public long getCustomExtsCount() {
        return customExtRepository.count();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addCustomExt(CustomExtDTO customExtDTO) {
        CustomExt customExt = CustomExt.toEntity(customExtDTO);

        // 기등록여부 체크
        if(!ObjectUtils.isEmpty(customExtRepository.findByCustExt(customExt.getCustExt())))
            throw new ApiException("이미 등록된 확장자 입니다.");

        // 갯수 200개 넘는지 체크
        if(customExtRepository.count() >= 200)
            throw new ApiException("등록 가능한 갯수(200개)를 초과했습니다.");

        customExtRepository.save(customExt);
    }

    @Transactional
    public void removeCustomExt(CustomExtDTO customExtDTO) {
        CustomExt customExt = CustomExt.toEntity(customExtDTO);
        customExtRepository.delete(customExt);
    }
}
