package sharedmobility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

 @RestController
 public class RentInfoController {
    @Autowired
    RentInfoRepository repository;

    // 결제 승인된 상태여야 렌트 가능. 유휴 장비에 접근하여 렌트 요청
    @PutMapping(value = "/rent/{id}")
    public boolean rent(@PathVariable String id) {

        RentInfo rentInfo = null;
        rentInfo = this.getRentById(id);
        boolean result = false; 

        // 결제 승인 상태면 렌트 진행
        if(rentInfo != null && "APPROVE".equals(rentInfo.getRentStatus())){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            String today =  sdf.format(timestamp);
    
            rentInfo.setRentDate(today);
            this.updateRent(Long.toString(rentInfo.getRentId()), "RENT");

            try {
                rentInfo = repository.save(rentInfo);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 반납
    @PutMapping(value = "/rent/return/{id}")
    public RentInfo returnOrder(@PathVariable String id) {
        return this.updateRent(id, "RETURN");
    }

    // 상태 변경
    @PatchMapping(value = "/rent/update/{id}")
    public RentInfo updateRent(@PathVariable String id, @PathVariable String status) {
        RentInfo rentInfo = null;
        List<RentInfo> rentList = repository.findByRentId(Long.parseLong(id));  // orderId 입력해야함!

        for (RentInfo o : rentList) {
            o.setRentStatus(status);

            rentInfo = repository.save(o);    // 저장
        }

        return rentInfo;
    }

    // 렌트 상태 확인
    @GetMapping(value = "/rent/{id}")
    public RentInfo getRentById(@PathVariable String id) {
        RentInfo rentInfo = null;
        List<RentInfo> rentList = repository.findByOrderId(Long.parseLong(id));  // orderId 입력해야함!

        for (RentInfo o : rentList) {
            rentInfo = o;
        }

        return rentInfo;
    }

    // 전체 렌트 리스트 확인
    @GetMapping(value = "/rent")
    public Iterable<RentInfo> getRentList() {

        Iterable<RentInfo> iter = repository.findAll();

        return iter;
    }
 }
