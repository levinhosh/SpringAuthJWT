package com.bezkoder.springjwt.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  private User user;

  /////////////////////////////// log in ////////////////////////////////////////////////
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            userDetails.getFirstName(),
            userDetails.getSecondName(),
            userDetails.getLastName(),
            userDetails.getDesignation(),
            roles));
  }


  ////////////////////////// register new user //////////////////////////////////////////
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(),
            signUpRequest.getSecondName(), signUpRequest.getLastName(), signUpRequest.getDesignation());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }


  ////////////////////////////////// list all users /////////////////////////////////////
  @GetMapping("/allusers")
  public ResponseEntity<?> systemusers(){
    try {
      List<?> users = userRepository.findAll();
      return ResponseEntity.ok(users);
    }catch (Exception e){
      return ResponseEntity.ok(new MessageResponse("Error {}"+e));
    }
  }

  ///////////////////////////// delette user ///////////////////////////////////
  @DeleteMapping("/deleteUser/{empid}")
  public ResponseEntity<?> deleteEmployee(@PathVariable Long empid) {
    try {
      userRepository.deleteById(empid);
      return ResponseEntity.ok(new MessageResponse("User Deleted Successful"));
    } catch (Exception e) {
      return ResponseEntity.ok(new MessageResponse("Error {}" + e));
    }
  }

  ///////////////////////////////// update user /////////////////////////////////////////

    @PutMapping("/updateuser/{id}")
  public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest updateRequest, @PathVariable Long id) {

      try {


        User appUserFound = userRepository.findById(id).get();
        if (Objects.nonNull(updateRequest.getUsername()) && !"".equalsIgnoreCase(String.valueOf(updateRequest.getUsername()))){
            appUserFound.setUsername(updateRequest.getUsername());
        }
        if (Objects.nonNull(updateRequest.getRole()) && !"".equalsIgnoreCase(String.valueOf(updateRequest.getRole()))){

          Set<String> strRoles = updateRequest.getRole();

          Set<Role> roles = new HashSet<>();


          if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          } else {
            strRoles.forEach(role -> {
              switch (role) {
                case "admin":
                  Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(adminRole);

                  break;
                case "mod":
                  Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(modRole);

                  break;
                default:
                  Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(userRole);
              }
            });
            appUserFound.setRoles(roles);

          }
        }
        if (Objects.nonNull(updateRequest.getPassword()) && !"".equalsIgnoreCase(updateRequest.getPassword())){
            appUserFound.setPassword(encoder.encode(updateRequest.getPassword()));

        }
        if (Objects.nonNull(updateRequest.getDesignation()) && !"".equalsIgnoreCase(updateRequest.getDesignation())){
            appUserFound.setDesignation(updateRequest.getDesignation());
        }
        userRepository.save(appUserFound);
        return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
    }catch (Exception e){
        return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: RECORD NOT UPDATED !!!"+e));
      }


    }

}
