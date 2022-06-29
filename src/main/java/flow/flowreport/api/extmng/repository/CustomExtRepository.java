package flow.flowreport.api.extmng.repository;

import flow.flowreport.api.extmng.entity.CustomExt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtRepository extends JpaRepository<CustomExt, Integer> {
    CustomExt findByCustExt(String custExt);
}
