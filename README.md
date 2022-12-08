# PluralKit-Java

A PluralKit API Wrapper for Java

## Roadmap

## Stability
- [ ] Token Verification before Client Initialization
- [ ] Create Error Handling Class

### Accessiblity
- [x] Access with Token Authentication
- [ ] Access without Token Authentication (for certain endpoints)

### Systems
- [x] Get System
- [x] Update System
- [x] Get System Settings
- [x] Update System Settings
- [x] Get System Guild Settings
- [x] Update System Guild Settings
- [ ] Get System Autoproxy Settings
- [ ] Update System Autoproxy Settings

### Members
- [ ] Get System Members
- [ ] Create Member
- [ ] Get Member
- [ ] Update Member
- [ ] Delete Member
- [ ] Get Member Groups
- [ ] Add Member to Groups
- [ ] Remove Member from Groups
- [ ] Overwrite Member Groups
- [ ] Get Member Guild Settings
- [ ] Update Member Guild Settings

### Groups
- [ ] Get System Groups
- [ ] Create Group
- [ ] Get Group
- [ ] Update Group
- [ ] Delete Group
- [ ] Get Group Members
- [ ] Add Members to Group
- [ ] Remove Member from Group
- [ ] Overwrite Group Members

### Switches

- [ ] Get System Switches
- [ ] Get Current System Fronters
- [ ] Create Switch
- [ ] Get Switch
- [ ] Update Switch
- [ ] Update Switch Members
- [ ] Delete Switch

### Misc
- [ ] Get Proxied Message Information

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
        
        JSONObject json = system.getAsJSON(); // returns a JSONObject
        String description = system.getDescription(); // returns the description as a String
        
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