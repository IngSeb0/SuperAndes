package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.parranderos.repositorio.BodegaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.edu.co.parranderos.modelo.Bodega;
import java.util.Collection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class BodegasController {
    @Autowired
    private BodegaRepository bodegaRepository;
    @GetMapping("/bodegas")
    public Collection <Bodega> bodegas()
    {
        return  bodegaRepository.obtenerTodasLasBodegas();
    }
       
    @PostMapping("/bodegas/new/save")
    public ResponseEntity<String> bodegGuardar(@RequestBody Bodega bodega) {
       try{
        bodegaRepository.insertarBodega( bodega.getNombre(), bodega.getTamaño(), bodega.getId());
        return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
       }
       catch (Exception e){
        return new ResponseEntity<>("Error al crear", HttpStatus.INTERNAL_SERVER_ERROR);

       }
       
       }
       @PostMapping("/bodegas/{id}/edit/save")
       public ResponseEntity<String> bodegaEditarGuardar(@PathVariable("id") Long id, @RequestBody Bodega bodega ){
          
           try{
           bodegaRepository.actualizarBodega( bodega.getId(), bodega.getNombre(), bodega.getTamaño());
           return new ResponseEntity<>("Bodega actualizada exitosamente", HttpStatus.OK);
       }catch(Exception e) {
        return new ResponseEntity<>("Error al actualizar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       }
       @PostMapping("/bodegas/{id}/delete")
       public ResponseEntity<String> bodegaEliminar(@PathVariable("id") Long id, @RequestBody Bodega bodega ){
        
        try{
            bodegaRepository.eliminarBodega(id);
        return new ResponseEntity<>("Bodega eliminada", HttpStatus.OK);}
        catch(Exception e) {
            return new ResponseEntity<>("Error al eliminar", HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
}
       
    
    
    

