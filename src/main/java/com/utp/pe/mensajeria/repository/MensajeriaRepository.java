/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pe.mensajeria.repository;

import com.utp.pe.mensajeria.entity.MensajeriaEntity;
import com.utp.pe.mensajeria.entity.SalasEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jungook
 */
@Repository
public interface MensajeriaRepository extends JpaRepository<MensajeriaEntity, Long> {

    @Query("SELECT m FROM MensajeriaEntity m WHERE m.sala = :sala")
    List<MensajeriaEntity> findBySala(@Param("sala") SalasEntity sala);

}
