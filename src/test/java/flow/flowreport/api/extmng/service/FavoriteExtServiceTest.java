package flow.flowreport.api.extmng.service;

import flow.flowreport.api.extmng.dto.FavoriteExtDTO;
import flow.flowreport.api.extmng.entity.FavoriteExt;
import flow.flowreport.api.extmng.repository.FavoriteExtRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FavoriteExtServiceTest {

    @InjectMocks
    private FavoriteExtService favoriteExtService;

    @Mock
    private FavoriteExtRepository favoriteExtRepository;

    @Test
    void getFavoriteExtInfo_고정확장자조회_성공() {
        //given
        List<FavoriteExt> favoriteExts = new ArrayList<>();
        favoriteExts.add(FavoriteExt.builder().faviExt("bat").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("cmd").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("com").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("cpl").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("exe").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("scr").faviExtUseYn("N").build());
        favoriteExts.add(FavoriteExt.builder().faviExt("js").faviExtUseYn("N").build());
        given(favoriteExtRepository.findAll()).willReturn(favoriteExts);

        //when
        List<FavoriteExtDTO> favoriteExtDTOs = favoriteExtService.getFavoriteExtInfo();

        //then
        Assertions.assertEquals(favoriteExtDTOs.size(), 7);
    }
}