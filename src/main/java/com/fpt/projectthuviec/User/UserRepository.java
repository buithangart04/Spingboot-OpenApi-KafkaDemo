package com.fpt.projectthuviec.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByCompanyId(long comId);
    // deactive user when company is deactive
    @Query(value = "update users set active = false where  company_id = :comId ",nativeQuery = true)
    void DeactiveUserByCompany(@Param("comId") long comId);
}
