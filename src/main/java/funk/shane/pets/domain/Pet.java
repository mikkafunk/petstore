package funk.shane.pets.domain;

import org.joda.time.Instant;

import com.google.common.base.Objects;

public class Pet implements Animal {
   
    private String type;
    private String breed;
    private double weight;
    private Instant birthDate;
    
    public Pet() {}
    
    /**
     * Pet constructor
     * TODO - look into creating this as a Builder and expanding type and breed
     * also include breeder/supplier info for auditing 
     * 
     * @param type
     * @param breed
     * @param weight
     * @param birthDate
     */
    public Pet(String type, String breed, double weight, Instant birthDate) {
        this.type = type;
        this.breed = breed;
        this.weight = weight;
        this.birthDate = birthDate;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Instant getBirthDate() {
        return birthDate;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("type", type)
            .add("breed", breed)
            .add("weight", weight)
            .add("birthdate", birthDate)
            .toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        else if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        final Pet other = (Pet)o;
        return Objects.equal(type, other.type)
                && Objects.equal(breed, other.breed)
                && Objects.equal(weight, other.weight)
                && Objects.equal(birthDate, other.birthDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(type, breed, weight, birthDate);
    }
}
