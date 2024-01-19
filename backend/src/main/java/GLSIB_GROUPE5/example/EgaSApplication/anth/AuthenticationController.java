package GLSIB_GROUPE5.example.EgaSApplication.anth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping(value = "/registerUser")
    public ResponseEntity<AuthenticationResponse> registerUer(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

}