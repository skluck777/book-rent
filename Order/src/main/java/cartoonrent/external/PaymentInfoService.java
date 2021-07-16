
package cartoonrent.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="payment", url="http://${api.url.order}")
public interface PaymentInfoService {
    @RequestMapping(method= RequestMethod.POST, path="/payment")
    public boolean pay(@RequestBody PaymentInfo paymentInfo);

}

