package funk.shane.pets.domain;

import org.joda.time.Instant;

public interface Animal {
    String getType();
    String getBreed();
    double getWeight();
    Instant getBirthDate();
}
