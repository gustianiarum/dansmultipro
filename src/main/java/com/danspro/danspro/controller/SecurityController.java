package com.danspro.danspro.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.danspro.danspro.assembler.IdentificationAssembler;
import com.danspro.danspro.beans.AuthReq;
import com.danspro.danspro.beans.AuthResp;
import com.danspro.danspro.beans.IdentificationDto;
import com.danspro.danspro.configure.DefaultResponse;
import com.danspro.danspro.repository.IdentificationRepository;
import com.danspro.danspro.service.MyUserDetailService;
import com.danspro.danspro.util.JwtUtil;

import org.json.JSONObject;

@RestController
public class SecurityController {

    @Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailService userDetailsService;

    @Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private IdentificationRepository repository;
    
    @Autowired
    private IdentificationAssembler assembler;



    @RequestMapping ("/hello")
        public String hello(){
            return "Helloww";
        }
    
    @GetMapping("/{code}")
    public DefaultResponse get(@PathVariable String userName) {
        IdentificationDto identificationDto = assembler.fromEntity(repository.findById(userName).get());
        return DefaultResponse.ok(identificationDto);
    }

    @RequestMapping ("/getData")
        public String getData(){
            try{
            URL url = new URL("http://dev3.dansmultipro.co.id/api/recruitment/positions.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
     	        response.append(inputLine);
            }
             in.close();
            //print in String
                System.out.println(response.toString());
    //  //Read JSON response and print
    //  JSONObject myResponse = new JsonObjectSerializer(response.toString());
    //  System.out.println("result after Reading JSON Response");
    //  System.out.println("statusCode- "+myResponse.getString("statusCode"));
    //  System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
    //  System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
    //  System.out.println("countryCode- "+myResponse.getString("countryCode"));
    //  System.out.println("countryName- "+myResponse.getString("countryName"));
    //  System.out.println("regionName- "+myResponse.getString("regionName"));
    //  System.out.println("cityName- "+myResponse.getString("cityName"));
    //  System.out.println("zipCode- "+myResponse.getString("zipCode"));
    //  System.out.println("latitude- "+myResponse.getString("latitude"));
    //  System.out.println("longitude- "+myResponse.getString("longitude"));
    //  System.out.println("timeZone- "+myResponse.getString("timeZone")); 

         return response.toString();
    } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
     }
    

    @GetMapping("/getData/{id}")
    public String getEmployeesByIdWithVariableName(@PathVariable("id") String id) {
        try{
            URL url = new URL("http://dev3.dansmultipro.co.id/api/recruitment/positions/"+id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
     	        response.append(inputLine);
            }
             in.close();
            //print in String
                System.out.println(response.toString());
    //  //Read JSON response and print
    //  JSONObject myResponse = new JsonObjectSerializer(response.toString());
    //  System.out.println("result after Reading JSON Response");
    //  System.out.println("statusCode- "+myResponse.getString("statusCode"));
    //  System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
    //  System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
    //  System.out.println("countryCode- "+myResponse.getString("countryCode"));
    //  System.out.println("countryName- "+myResponse.getString("countryName"));
    //  System.out.println("regionName- "+myResponse.getString("regionName"));
    //  System.out.println("cityName- "+myResponse.getString("cityName"));
    //  System.out.println("zipCode- "+myResponse.getString("zipCode"));
    //  System.out.println("latitude- "+myResponse.getString("latitude"));
    //  System.out.println("longitude- "+myResponse.getString("longitude"));
    //  System.out.println("timeZone- "+myResponse.getString("timeZone")); 

         return response.toString();
    } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthReq authenticationRequest) throws Exception {
    
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
                );
            }
            catch (BadCredentialsException e) {
                throw new Exception("Incorrect username or password", e);
            }
    
    
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
    
            final String jwt = jwtTokenUtil.generateToken(userDetails);
    
            return ResponseEntity.ok(new AuthResp(jwt));
    }
    

}
