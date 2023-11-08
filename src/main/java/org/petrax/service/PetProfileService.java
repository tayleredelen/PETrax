package org.petrax.service;
import org.petrax.data.PetProfileRepository;
import org.petrax.models.PetProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Service classes encapsulate interactions with data access layer (PetProfileRepository)
//allowing reuse of logic in application
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository) {
        this.petProfileRepository = petProfileRepository;
    }
// Initialize the service with the PetProfileRepository.


    public List<PetProfile> getAllPets() {
        return (List<PetProfile>) petProfileRepository.findAll();
    }
// Retrieve all pet profiles from the repository.

    public Optional<PetProfile> getPetById(int petId) {
        return petProfileRepository.findById(petId);
    }
// Retrieve a pet profile by ID from the repository.

    public PetProfile addPet(PetProfile pet) {
        return petProfileRepository.save(pet);
    }
// Add a new pet profile to the repository.

    public Optional<PetProfile> updatePet(int petId, PetProfile updatedPet) {
// Update an existing pet profile in the repository.
        Optional<PetProfile> petProfileOptional = petProfileRepository.findById(petId);
        if (petProfileOptional.isPresent()) {
            updatedPet.setPetId(petId);
            return Optional.of(petProfileRepository.save(updatedPet));
        } else {
            return Optional.empty();
        }
    }


    public Optional<Object> deleteByPetId(int petId) {
// Delete a pet profile by ID from the repository.
        Optional<PetProfile> petProfileOptional = petProfileRepository.findById(petId);
        if (petProfileOptional.isPresent()) {
            petProfileRepository.delete(petProfileOptional.get());
        } else {
            return Optional.empty();
        }
        return null;
    }



}

