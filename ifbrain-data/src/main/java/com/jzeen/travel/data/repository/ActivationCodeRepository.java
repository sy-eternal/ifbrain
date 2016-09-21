package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.ActivationCode;

import java.util.List;

public interface ActivationCodeRepository extends AdvancedJpaRepository<ActivationCode, Integer> {
    public ActivationCode findByCode(String code);

    /**
     * 用来查手机号
     *
     * @param type
     * @return
     */
    public ActivationCode findByType(String type);

    /**
     * 通过短信标识查找 ActivationCode
     * @param identifier 短信标识
     * @return
     */
    List<ActivationCode> findByIdentifier(String identifier);


    /**
     * 通过短信验证码类型、手机号码查询短信验证码
     * @param type 类型
     * @param phone 手机号码
     * @return
     */
    List<ActivationCode> findByTypeAndPhone(String type, String phone);


    /**
     * 通过短信验证码类型、手机号码查询短信验证码
     * @param type 类型
     * @param phone 手机号码
     * @param code 验证码
     * @return
     */
    List<ActivationCode> findByTypeAndPhoneAndCode(String type, String phone, String code);
}
