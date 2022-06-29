package flow.flowreport.api.extmng.dto;

import flow.flowreport.api.extmng.entity.FavoriteExt;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteExtDTO {
    private String faviExt;
    private String faviExtUseYn;
    private String chgId;

    @Builder
    private FavoriteExtDTO(String faviExt, String faviExtUseYn, String chgId) {
        this.faviExt = faviExt;
        this.faviExtUseYn = faviExtUseYn;
        this.chgId = chgId;
    }

    public static FavoriteExtDTO toDTO(FavoriteExt favoriteExt) {
        FavoriteExtDTO favoriteExtDTO = FavoriteExtDTO.builder()
                .faviExt(favoriteExt.getFaviExt())
                .faviExtUseYn(favoriteExt.getFaviExtUseYn())
                .chgId(favoriteExt.getChgId())
                .build();
        return favoriteExtDTO;
    }
}
