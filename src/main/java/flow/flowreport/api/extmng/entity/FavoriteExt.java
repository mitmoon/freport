package flow.flowreport.api.extmng.entity;

import flow.flowreport.api.extmng.dto.FavoriteExtDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_favi_ext")
public class FavoriteExt {
    @Id
    @Column(length = 200, nullable = false)
    private String faviExt;

    @Column(length = 1, nullable = false, columnDefinition = "char(1) default 'N'")
    private String faviExtUseYn;

    @Column(columnDefinition = "varchar(100) default 'SYSTEM'")
    private String chgId;

    public static FavoriteExt toEntity(FavoriteExtDTO favoriteExtDTO) {
        FavoriteExt favoriteExt = FavoriteExt.builder()
                .faviExt(favoriteExtDTO.getFaviExt())
                .faviExtUseYn(favoriteExtDTO.getFaviExtUseYn())
                .chgId(favoriteExtDTO.getChgId())
                .build();
        return favoriteExt;
    }
}
