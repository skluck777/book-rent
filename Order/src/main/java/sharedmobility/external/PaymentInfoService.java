
package sharedmobility.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Payment", url="http://Payment:8080")
public interface PaymentInfoService {
    @RequestMapping(method= RequestMethod.GET, path="/paymentInfos")
    public void payment(@RequestBody PaymentInfo paymentInfo);

}

