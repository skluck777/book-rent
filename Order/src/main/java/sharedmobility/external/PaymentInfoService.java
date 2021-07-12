
package sharedmobility.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="Payment", url="http://localhost:8083")
public interface PaymentInfoService {
    @RequestMapping(method= RequestMethod.POST, path="/payment")
    public boolean pay(@RequestBody PaymentInfo paymentInfo);

}

