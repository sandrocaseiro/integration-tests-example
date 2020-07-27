package dev.sandrocaseiro.springbootitExample.repositories;

import dev.sandrocaseiro.springbootitExample.models.domain.EUsuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UsuarioRepository extends BaseRepository<EUsuario, Integer> {
//    @Modifying
//    @Query("update Usuario set balance = :balance, updateDate = SYSDATE where id = :id")
//    int updateBalance(int id, BigDecimal balance);

    @Query("select case when count(1) > 0 then true else false end from Usuario u where u.email = :email")
    boolean existeUsuarioComEmail(String email);

//    @Query("select u from Usuario u inner join fetch u.group")
//    List<EUsuario> findAll();
}
