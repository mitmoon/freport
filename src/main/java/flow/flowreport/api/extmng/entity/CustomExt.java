package flow.flowreport.api.extmng.entity;

import flow.flowreport.api.extmng.dto.CustomExtDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cust_ext")
public class CustomExt {
    @Id
    @Column(length = 200, nullable = false)
    private String custExt;

    @Column(columnDefinition = "varchar(100) default 'SYSTEM'")
    private String chgId;

    public static CustomExt toEntity(CustomExtDTO customExtDTO) {
        CustomExt customExt = CustomExt.builder()
                .custExt(customExtDTO.getCustExt())
                .chgId(customExtDTO.getChgId())
                .build();
        return customExt;
    }
}