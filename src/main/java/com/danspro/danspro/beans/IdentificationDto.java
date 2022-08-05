package com.danspro.danspro.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IdentificationDto {
    
        private String userName;
        public String getUserName() {
            return userName;
        }
        public void setUserName(String password) {
            this.userName = userName;
        }
        private String password;
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.userName = userName;
        }
    
}
