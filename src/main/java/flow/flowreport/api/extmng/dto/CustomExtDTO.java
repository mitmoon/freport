package flow.flowreport.api.extmng.dto;

import flow.flowreport.api.extmng.entity.CustomExt;
import flow.flowreport.util.ExtCheckUtil;
import flow.flowreport.util.XssCheckUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CustomExtDTO {
    @Size(max = 20, message = "20글자까지만 입력 가능합니다.")
    @NotBlank(message = "커스텀 확장자명은 필수입력입니다.")
    private String custExt;

    private String chgId;

    @Builder
    private CustomExtDTO(String custExt, String chgId) {
        this.custExt = custExt;
        this.chgId = chgId;
    }

    public static CustomExtDTO toDTO(CustomExt customExt) {
        CustomExtDTO customExtDTO = CustomExtDTO.builder()
                .custExt(customExt.getCustExt())
                .chgId(customExt.getChgId())
                .build();
        return customExtDTO;
    }

    public void validate() {
        XssCheckUtil.checkIllegalKeyword(new String[]{this.getCustExt()});
        ExtCheckUtil.checkExistInFavoriteExt(this.getCustExt());
    }
}
