package org.petrax.controllers;
import org.petrax.data.EventRepository;
import org.petrax.data.UserRepository;
import org.petrax.models.PetProfile;
import org.petrax.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.petrax.service.PetProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import org.petrax.data.PetProfileRepository;




@RestController
//controller class responsible for handling HTTP requests and providing responses,
// particularly for RESTful APIs. This annotation combines the @Controller and
// @ResponseBody annotations, which means that every method in this class is implicitly
//annotated with @ResponseBody.
@RequestMapping("/api/petProfile")
public class PetProfileController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @Autowired
    private final PetProfileService petProfileService;
    // Use the service instead of the repository directly

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public PetProfileController(PetProfileService petProfileService, UserRepository userRepository) {
        this.petProfileService = petProfileService;
        this.userRepository = userRepository; // Initialize userRepository
    }

    @GetMapping
    public List<PetProfile> getAllPets() {
        return petProfileService.getAllPets();  // Utilize the service method here
    }
//retrieve all pet profiles using the service method


    @GetMapping("/{petId}")
    public ResponseEntity<PetProfile> getPetById(@PathVariable int petId) {
        Optional<PetProfile> pet = petProfileService.getPetById(petId);  // Utilize the service method here
        return pet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
//retrieve pet profile by ID using service method

    @PostMapping
    public ResponseEntity<PetProfile> addPet(@RequestBody PetProfile pet) {
        User user = userRepository.findById(pet.id).orElse(null);
        pet.setUser(user);

        PetProfile savedPet = petProfileService.addPet(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }
// Add a new pet profile using the service and associate it with a user.



    @PutMapping("/{petId}")
    public ResponseEntity<PetProfile> updatePet(@PathVariable int petId, @RequestBody PetProfile updatedPet) {
// Update an existing pet profile using the service.
        Optional<PetProfile> pet = petProfileService.updatePet(petId, updatedPet);  // Utilize the service method here
        return pet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByPetId(@PathVariable int petId) {
        petProfileService.deleteByPetId(petId); // Utilize the service method to delete by petId
    }
// Delete a pet profile by ID using the service.


}


