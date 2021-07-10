package sharedmobility;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="rentInfos", path="rentInfos")
public interface RentInfoRepository extends PagingAndSortingRepository<RentInfo, String>{


}
