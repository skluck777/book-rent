package sharedmobility;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="paymentInfos", path="paymentInfos")
public interface PaymentInfoRepository extends PagingAndSortingRepository<PaymentInfo, String>{


}
