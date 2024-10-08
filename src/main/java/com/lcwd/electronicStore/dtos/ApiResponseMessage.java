package com.lcwd.electronicStore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {
    private  String message;
    private  boolean success;
    private HttpStatus status;
}
//Api response class is used becouse of we Take String Respose in deleteUser in UserController to make it custom message
//we use this APIResponce class