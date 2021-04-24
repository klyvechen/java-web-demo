package rm.project.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import rm.project.component.R;

@FeignClient("rm-coupon")
public interface CouponFeignService {

    @GetMapping("coupon/coupons")
    R memberCoupons();
}
