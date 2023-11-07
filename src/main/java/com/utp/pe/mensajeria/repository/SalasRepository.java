/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.pe.mensajeria.repository;

import com.utp.pe.mensajeria.entity.SalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jungook
 */
@Repository
public interface SalasRepository extends JpaRepository<SalasEntity, Long> {

    SalasEntity findByCodigo(Long codigo);

}
