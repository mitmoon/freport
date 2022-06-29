package flow.flowreport.api.extmng.service;

import flow.flowreport.api.exception.ApiException;
import flow.flowreport.api.extmng.dto.CustomExtDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class CustomExtServiceTest {

    @Autowired
    CustomExtService customExtService;

    @Test
    void addCustomExt_확장자명_중복등록시_오류() {
        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            CustomExtDTO customExtDTO1 = CustomExtDTO.builder().custExt("duplext").chgId("admin").build();
            customExtService.addCustomExt(customExtDTO1);
            CustomExtDTO customExtDTO2 = CustomExtDTO.builder().custExt("duplext").chgId("admin").build();
            customExtService.addCustomExt(customExtDTO2);
        });
    }

    @Test
    void addCustomExt_확장자_200개초과등록시_오류() {
        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            IntStream intStream = IntStream.range(1, 202);
            intStream.forEach(newExt ->
                    {
                        CustomExtDTO customExtDTO = CustomExtDTO.builder().custExt(String.valueOf(newExt)).chgId("admin").build();
                        System.out.println(newExt);
                        customExtService.addCustomExt(customExtDTO);
                    }
            );
        });
    }
}