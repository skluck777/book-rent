package sharedmobility;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="paymentInfos", path="paymentInfos")
public interface PaymentInfoRepository extends PagingAndSortingRepository<PaymentInfo, Long >{
    List<PaymentInfo> findByPayId(Long payId);
}
