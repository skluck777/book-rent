package sharedmobility;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="stockInfos", path="stockInfos")
public interface StockInfoRepository extends PagingAndSortingRepository<StockInfo, String>{


}
