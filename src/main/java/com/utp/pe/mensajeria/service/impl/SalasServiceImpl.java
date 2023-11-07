/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pe.mensajeria.service.impl;

import com.utp.pe.mensajeria.entity.MensajeriaEntity;
import com.utp.pe.mensajeria.entity.SalasEntity;
import com.utp.pe.mensajeria.repository.SalasRepository;
import com.utp.pe.mensajeria.service.entity.SalasService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jungook
 */
@Service
public class SalasServiceImpl implements SalasService {

    @Autowired
    private SalasRepository repositorio;

    @Override
    public List<SalasEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<SalasEntity> findAllCustom() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SalasEntity add(SalasEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<SalasEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public SalasEntity update(SalasEntity t) {
        SalasEntity objSal = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t, objSal);
        return repositorio.save(objSal);
    }

    @Override
    public SalasEntity delete(SalasEntity t) {
        SalasEntity objSal = repositorio.getById(t.getCodigo());
        objSal.setEstado(false);
        return repositorio.save(objSal);
    }

    @Override
    public SalasEntity enabled(SalasEntity t) {
        SalasEntity objSal = repositorio.getById(t.getCodigo());
        objSal.setEstado(true);
        return repositorio.save(objSal);
    }

}
