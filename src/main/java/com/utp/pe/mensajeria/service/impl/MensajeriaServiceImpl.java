/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pe.mensajeria.service.impl;

import com.utp.pe.mensajeria.entity.MensajeriaEntity;
import com.utp.pe.mensajeria.repository.MensajeriaRepository;
import com.utp.pe.mensajeria.service.entity.MensajeriaService;
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
public class MensajeriaServiceImpl implements MensajeriaService {

    @Autowired
    private MensajeriaRepository repositorio;

    @Override
    public List<MensajeriaEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<MensajeriaEntity> findAllCustom() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MensajeriaEntity add(MensajeriaEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<MensajeriaEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public MensajeriaEntity update(MensajeriaEntity t) {
        MensajeriaEntity objMgs = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t, objMgs);
        return repositorio.save(objMgs);
    }

    @Override
    public MensajeriaEntity delete(MensajeriaEntity t) {
        MensajeriaEntity objMgs = repositorio.getById(t.getCodigo());
        objMgs.setEstado(false);
        return repositorio.save(objMgs);
    }

    @Override
    public MensajeriaEntity enabled(MensajeriaEntity t) {
        MensajeriaEntity objMgs = repositorio.getById(t.getCodigo());
        objMgs.setEstado(true);
        return repositorio.save(objMgs);
    }

}
