package com.bankflex.accountservice.adapter.out.repository;

import com.bankflex.accountservice.domain.model.User;
import com.bankflex.accountservice.domain.model.dto.outbound.UserOutbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select new br.com.l2mtravelexconsultpaymentapi.domain.dto.outbound.UserOutbound(" +
            "u.userId," +
            "u.name," +
            "u.username,"+
            "u.email," +
            "u.cpfCnpj," +
            "u.password)" +
            " FROM User u WHERE " +
            "(:identifier is null or u.name like %:identifier% or u.email like %:identifier%)" +
            " order by u.name asc")
    Page<UserOutbound> searchRegisteredUsers(Pageable pageable, @Param(value = "identifier") String identifier);
}
