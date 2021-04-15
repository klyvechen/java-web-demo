package rm.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 會員物件
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Entity
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 姓名　*/
    private String name;

    /** 密碼 */
    private String password;

    /** 註冊Email　*/
    private String email;

    /** 註冊手機　*/
    private String phone;

    public User() {}
}
