# PluralKit-Java

A PluralKit API Wrapper for Java

## Installing
Soon

## Examples

### Configure the Client

```java
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.PKClientBuilder;

public class Main {
    public static void main(@NotNull String[] args) {
        PKClient client = new PKClientBuilder().create("token").build();
    }
}
```

### Sample Code

```java
import org.mesa.pkwrapper.PKClient;
import org.mesa.pkwrapper.PKClientBuilder;
import org.mesa.pkwrapper.managers.PKSystemManager;
import org.mesa.pkwrapper.models.PKSystem;

public class Main {
    public static void main(String[] args) {
        PKClient client = new PKClientBuilder().create("token").build();

        // Get a System by ID
        PKSystem system = client.getSystem("systm");

        // Get System specific information
        
        system.getAsJSON(); // returns a JSONObject
        system.getDescription(); // returns the description as a String
        
        // Edit the System
        PKSystemManager manager = system.getManager();
        
        manager.setName("New name !")
               .setPronouns("they/them");
        
        PKSystem updatedSystem = manager.update();
    }
}
```

## Docs
Soon