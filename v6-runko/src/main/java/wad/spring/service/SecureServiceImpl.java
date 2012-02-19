package wad.spring.service;

import org.springframework.stereotype.Service;

@Service
public class SecureServiceImpl implements SecureService {

    @Override
    public void executeOnlyIfAuthenticatedAsLecturer() {
        System.out.println("The guy must be an admin! Note that this was configured in the interface.");
    }

    @Override
    public void executeOnlyIfAuthenticated() {
        System.out.println("The guy must be authenticated! Note that this was configured in the interface.");
    }

    @Override
    public void executeFreely() {
        System.out.println("The guy must be! Note that we did not do anything.");
    }
    
}
