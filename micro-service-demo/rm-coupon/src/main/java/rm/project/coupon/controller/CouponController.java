package rm.project.coupon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm.project.component.R;
import rm.project.coupon.output.Coupon;

import java.util.Arrays;


@RefreshScope
@RestController
@RequestMapping("coupon")
public class CouponController {

    @GetMapping("/coupons")
    public R memberCoupons() {
        Coupon coupon = new Coupon();
        coupon.setName("滿一百減10");
        return R.ok().put("coupon", Arrays.asList(coupon)).done();
    }

    @Value("${coupon.user.name}")
    private String couponUserName;

    @Value("${coupon.user.age}")
    private int couponUserAge;

    @GetMapping("/couponUser")
    public R couponUser() {
        return R.ok().put("username", couponUserName).put("userage", couponUserAge).done();
    }
}
