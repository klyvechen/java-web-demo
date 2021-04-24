package rm.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm.project.component.R;
import rm.project.member.feign.CouponFeignService;
import rm.project.member.output.Member;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    CouponFeignService couponFeignService;

    @GetMapping("/coupon")
    public R test() {
        Member member = new Member();
        member.setName("張三");

        R coupons = couponFeignService.memberCoupons();

        return R.ok().put("member", member).put("coupon", coupons.get("coupon")).done();
     }
}
