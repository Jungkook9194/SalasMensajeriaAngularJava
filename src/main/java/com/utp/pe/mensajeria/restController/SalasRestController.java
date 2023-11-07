/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pe.mensajeria.restController;

import com.utp.pe.mensajeria.entity.SalasEntity;
import com.utp.pe.mensajeria.repository.SalasRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jungook
 */
@RestController
@RequestMapping("/salas")
public class SalasRestController {

    @Autowired
    private SalasRepository salaChatRepository;

    @PostMapping("/add")
    public ResponseEntity<SalasEntity> crearSala(@RequestPart("imagen") MultipartFile imagen,
            @RequestPart("nombre") String nombre,
            @RequestPart("descripcion") String descripcion) {
        try {
            // Procesar y almacenar la imagen
            byte[] imagenBytes = imagen.getBytes();
            String nombreImagen = imagen.getOriginalFilename();

            // Ruta donde guardar la imagen
            String rutaDestino = "C:\\Users\\Jungook\\Desktop\\mensajeriaAngular\\src\\assets\\images\\";

            // Crea un File con la ruta de destino y el nombre de la imagen
            File archivoDestino = new File(rutaDestino + nombreImagen);

            // Guarda la imagen en la carpeta destino
            try (FileOutputStream fos = new FileOutputStream(archivoDestino)) {
                fos.write(imagenBytes);
            }

            String imagenUrl = nombreImagen;

            SalasEntity nuevaSala = new SalasEntity();
            nuevaSala.setNombre(nombre);
            nuevaSala.setDescripcion(descripcion);
            nuevaSala.setImagen(imagenUrl);

            nuevaSala = salaChatRepository.save(nuevaSala);
            return new ResponseEntity<>(nuevaSala, HttpStatus.CREATED);
        } catch (IOException e) {
            // Manejo de errores si ocurre un problema al procesar la imagen
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todas")
    public List<SalasEntity> obtenerTodasLasSalas() {
        return salaChatRepository.findAll();
    }

    @GetMapping("/join/{codigo}")
    public ResponseEntity<String> unirseASalaPorCodigo(@PathVariable Long codigo) {
        SalasEntity sala = salaChatRepository.findByCodigo(codigo);
        if (sala != null) {
            return new ResponseEntity<>("Te has unido a la sala: " + sala.getNombre(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sala no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/details/{codigo}")
    public ResponseEntity<SalasEntity> getRoomDetails(@PathVariable Long codigo) {
        try {
            SalasEntity sala = salaChatRepository.findByCodigo(codigo);
            if (sala != null) {
                return new ResponseEntity<>(sala, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
