package flow.flowreport.api.extmng.service;

import flow.flowreport.api.extmng.dto.FavoriteExtDTO;
import flow.flowreport.api.extmng.entity.FavoriteExt;
import flow.flowreport.api.extmng.repository.FavoriteExtRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FavoriteExtService {
    private final FavoriteExtRepository favoriteExtRepository;

    @Autowired
    public FavoriteExtService(FavoriteExtRepository favoriteExtRepository) {
        this.favoriteExtRepository = favoriteExtRepository;
    }

    public List<FavoriteExtDTO> getFavoriteExtInfo() {
        List<FavoriteExt> favoriteExts = favoriteExtRepository.findAll();

        return favoriteExts.stream()
                .map(favoriteExt -> FavoriteExtDTO.toDTO(favoriteExt))
                .collect(Collectors.toList());
    }

    @Transactional
    public void modifyFavoriteExtInfo(List<FavoriteExtDTO> favoriteExtDTOs) {
        List<FavoriteExt> favoriteExts = favoriteExtDTOs.stream()
                .map(favoriteExtDTO -> FavoriteExt.toEntity(favoriteExtDTO))
                        .collect(Collectors.toList());

        favoriteExtRepository.saveAll(favoriteExts);
    }
}
