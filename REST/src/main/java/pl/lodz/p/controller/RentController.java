package pl.lodz.p.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.dto.EndRentDTO;
import pl.lodz.p.dto.RentDTO;
import pl.lodz.p.dto.UuidDTO;
import pl.lodz.p.model.Rent;
import pl.lodz.p.service.implementation.RentService;

import javax.naming.Binding;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rent")
public class RentController {
    private RentService rentService;

    @PostMapping//not tested
    public ResponseEntity<Object> createRent(@Valid @RequestBody RentDTO rentDTO, BindingResult bindingResult) {
        try {
            Rent pom = rentService.createRent(rentDTO);
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(pom);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping//not tested
    public ResponseEntity<Object> getAllRents() {
        try {
            List<Rent> rents;
            try {
                rents = rentService.getAllRents();
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/{uuid}")//not tested
    public ResponseEntity<Object> getRent(@PathVariable("uuid") UuidDTO uuid) {
        try {
            Rent rent;
            try {
                rent = rentService.getRent(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rent found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rent);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/active")//not tested
    public ResponseEntity<Object> getActiveRents() {
        try {
            List<Rent> rents;
            try {
                rents = rentService.getActiveRents();
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/archived")//not tested
    public ResponseEntity<Object> getArchivedRents() {
        try {
            List<Rent> rents;
            try {
                rents = rentService.getArchivedRents();
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/end/{uuid}")//not tested
    public ResponseEntity<Object> endRent(@PathVariable("uuid") UuidDTO uuid, @Valid @RequestBody EndRentDTO endTimeDTO, BindingResult bindingResult) {
        try {
            try{
                rentService.endRent(uuid.uuid(), endTimeDTO.getEndTime());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors() + ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @GetMapping("/active/client/{uuid}")//not tested
    public ResponseEntity<Object> getClientActiveRents(@PathVariable("uuid") UuidDTO uuid){
        try {
            List<Rent> rents;
            try {
                rents = rentService.getClientActiveRents(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/active/vmachine/{uuid}")//not tested
    public ResponseEntity<Object> getVMachineActiveRent(@PathVariable("uuid") UuidDTO uuid){
        try {
            Rent rent;
            try {
                rent = rentService.getVMachineActiveRent(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rent);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/archived/client/{uuid}")//not tested
    public ResponseEntity<Object> getClientArchivedRents(@PathVariable("uuid") UuidDTO uuid){
        try {
            List<Rent> rents;
            try {
                rents = rentService.getClientArchivedRents(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/archived/vmachine/{uuid}")//not tested
    public ResponseEntity<Object> getVMachineArchivedRents(@PathVariable("uuid") UuidDTO uuid){
        try {
            List<Rent> rents;
            try {
                rents = rentService.getVMachineArchivedRents(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")//not tested
    public ResponseEntity<Object> deleteRent(@PathVariable("uuid") UuidDTO uuid) {
        try {
            try {
                rentService.removeRent(uuid.uuid());
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No rents found");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
