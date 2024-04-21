package org.example.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebLog {
    private Date startTime;
    private String basePath;
    private String method;
    private String description;
//    private String username;
     private Integer spendTime;

//    private String uri;
//    private String url;

//    private String ip;
//    private Object parameter;
//    private Object result;




}
