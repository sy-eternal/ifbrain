package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Member;

public interface MemberRepository extends AdvancedJpaRepository<Member, Integer>
{
    /**
     * 根据邮箱和密码查询用户信息
     * 
     * @param email
     *            邮箱
     * @param password
     *            密码
     * @return 根据邮箱和密码返回用户信息
     */
    Member findByEmailAndPassword(String email, String password);

    /**
     * @param password
     *            原密码
     * @return
     */
    Member findByPassword(String password);
}
