/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pe.mensajeria.restController;

import com.utp.pe.mensajeria.entity.MensajeriaEntity;
import com.utp.pe.mensajeria.entity.SalasEntity;
import com.utp.pe.mensajeria.repository.MensajeriaRepository;
import com.utp.pe.mensajeria.repository.SalasRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensajes")
public class MensajeriaRestControlller {

    @Autowired
    private MensajeriaRepository repositorio;
    @Autowired
    private SalasRepository salaChatRepository;

    @PostMapping("/send/{salaId}")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> payload) {
        String message = (String) payload.get("message");
        Long salaId = Long.valueOf(payload.get("sala_id").toString());

        if (message == null || message.trim().isEmpty()) {
            return new ResponseEntity<>("El mensaje está vacío", HttpStatus.BAD_REQUEST);
        }

        SalasEntity sala = salaChatRepository.findById(salaId)
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));

        MensajeriaEntity entity = new MensajeriaEntity();
        entity.setMensajes(message);
        entity.setSala(sala);

        return new ResponseEntity<>(repositorio.save(entity), HttpStatus.OK);
    }

    @GetMapping("/messages/{salaId}")
    public List<MensajeriaEntity> getMessagesBySala(@PathVariable Long salaId) {
        SalasEntity sala = salaChatRepository.findById(salaId)
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));
        return repositorio.findBySala(sala);
    }
}
