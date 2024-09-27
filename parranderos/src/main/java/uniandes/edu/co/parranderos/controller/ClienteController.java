package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.parranderos.modelo.Cliente;
import uniandes.edu.co.parranderos.repositorio.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    @GetMapping("/cliente")
    public Collection<Cliente> obtenerClientes() {
        return clienteRepository.obtenerTodosLosClientes();
    }

    // Insertar un nuevo cliente
    @PostMapping("/cliente/new/save")
    public ResponseEntity<String> insertarCliente(@RequestBody Cliente cliente) {
        try {
            clienteRepository.insertarCliente(cliente.getCedula(), cliente.getNombreCliente());
            return new ResponseEntity<>("Cliente creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un cliente
    @PostMapping("/cliente/{cedula}/edit/save")
    public ResponseEntity<String> actualizarCliente(@PathVariable("cedula") Long cedula, @RequestBody Cliente cliente) {
        try {
            clienteRepository.actualizarCliente(cedula, cliente.getNombreCliente());
            return new ResponseEntity<>("Cliente actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un cliente
    @PostMapping("/cliente/{cedula}/delete")
    public ResponseEntity<String> eliminarCliente(@PathVariable("cedula") Long cedula) {
        try {
            clienteRepository.eliminarCliente(cedula);
            return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
 