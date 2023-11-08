package org.petrax.models.dto;

import org.petrax.models.PetProfile;
import org.petrax.models.PetType;

import javax.validation.constraints.NotNull;

//DTO is a class that wraps or extends the PetProfile domain model with adtl properties (e.g., PetType)
public class PetProfileDTO {

    @NotNull
    private PetProfile petProfile;

    @NotNull
    private PetType petType;

    public PetProfileDTO(){}

    public PetProfile getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(PetProfile petProfile) {this.petProfile = petProfile;}

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {this.petType = petType;}
}
